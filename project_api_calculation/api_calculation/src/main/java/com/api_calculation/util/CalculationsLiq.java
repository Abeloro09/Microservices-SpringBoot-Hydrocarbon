package com.api_calculation.util;

import lombok.Data;

/**
 * @author Abelardo Orozco
 * Clase que cuenta con los metodos para calculos de acuerdo a estandard American Petroleum Institute
 */
@Data
public class CalculationsLiq {

    // Instance variables
    private String ABD; // Type of crude (A, B, or D)
    private double gauge; // Measurement of the liquid level
    private double tov; // Total Observed Volume
    private double waterGauge; // Measurement of the water level
    private double waterTov; // Total observed volume of water
    private double kFra1; // Constant used in the Fixed Roof Adjustment calculation
    private double kFra2; // Another Constant used in the Fixed Roof Adjustment calculation
    private double tLam; // Lamination temperature
    private double tempL; // Liquid temperature
    private double tAmb; // Ambient temperature
    private double api60; // API gravity at 60 degrees Fahrenheit
    private double bsw; // Base Sediment and Water

    // Constructor to initialize the instance variables
    public CalculationsLiq(String ABD, double gauge, double tov, double waterGauge, double waterTov, double kFra1, double kFra2, double tLam, double tempL, double tAmb, double api60, double bsw) {
        this.ABD = ABD;
        this.gauge = gauge;
        this.tov = tov;
        this.waterGauge = waterGauge;
        this.waterTov = waterTov;
        this.kFra1 = kFra1;
        this.kFra2 = kFra2;
        this.tLam = tLam;
        this.tempL = tempL;
        this.tAmb = tAmb;
        this.api60 = api60;
        this.bsw = bsw;
    }

    /**
     *Method to calculate the Correction for the effect of Temperature on the Liquid (CTL)
     */

    public double ctl() {
        // Local variables
        double CTL, k0, kl, k2, d60, RD, d, Temp, tC90, T, a1, a2, a3, a4, a5, a6, a7, a8, DTT, TC68, TF68, A, B, dr, a60, DTr;

        // Initialize constants and intermediate values
        k0 = 341.0957;
        kl = 0;
        k2 = 0;
        RD = 141.5 / (api60 + 131.5);
        d = RD * 999.016;
        Temp = tempL;
        d60 = d;
        tC90 = (Temp - 32) / 1.8;
        T = tC90 / 630;

        // Coefficients for calculating DTT
        a1 = -0.148759;
        a2 = -0.267408;
        a3 = 1.08076;
        a4 = 1.269056;
        a5 = -4.089591;
        a6 = -1.871251;
        a7 = 7.438081;
        a8 = -3.536296;
        DTT = (a1 + (a2 + (a3 + (a4 + (a5 + (a6 + (a7 + a8 * T) * T) * T) * T) * T) * T) * T) * T;
        TC68 = tC90 - DTT;
        TF68 = 1.8 * TC68 + 32;

        // Select constants k0, kl, and k2 based on the type of crude (ABD)
        if (ABD.equals("A")) {
            k0 = 341.0957;
            kl = 0;
            k2 = 0;
        } else if (ABD.equals("D")) {
            k0 = 0;
            kl = 0.34878;
            k2 = 0;
        } else if (ABD.equals("B") && d60 < 770.352) {
            k0 = 192.4571;
            kl = 0.2438;
            k2 = 0;
        } else if (ABD.equals("B") && d60 < 787.5195) {
            k0 = 1489.067;
            kl = 0;
            k2 = -0.0018684;
        } else if (ABD.equals("B") && d60 < 838.3127) {
            k0 = 330.301;
            kl = 0;
            k2 = 0;
        } else if (ABD.equals("B") && d60 < 1163.5) {
            k0 = 103.872;
            kl = 0.2701;
            k2 = 0;
        }

        // Calculate CTL using the selected constants
        A = 0.01374979547 / 2 * ((k0 / d60 + kl) / d60 + k2);
        B = (2 * k0 + kl * d60) / (k0 + (kl + k2 * d60) * d60);
        dr = d60 * (1 + (Math.exp(A * (1 + 0.8 * A)) - 1) / (1 + A * (1 + 1.6 * A) * B));
        a60 = (k0 / dr + kl) / dr + k2;
        DTr = TF68 - 60.0068749;
        CTL = Math.exp(-a60 * DTr * (1 + 0.8 * a60 * (DTr + 0.01374979547)));
        CTL = (double)Math.round(CTL * 100000d) / 100000;
        return CTL;
    }

    /**
     * Method to calculate the observed API gravity (API observed)
      * @return API observed
     */
    public double apiObs() {
        // Local variables
        double RD, d, Temp, DF, API, d60, tC90, T, a1, a2, a3, a4, a5, a6, a7, a8, DTT, TC68, TF68, k0, k1, k2, j0, Da, A, B, dr, a60, DTr, CTLB, Fp, CPL2, CTPL2, Pres, DeObs, APIOBS, RD1;

        // Initialize constants and intermediate values
        k0 = 341.0957;
        k1 = 0;
        k2 = 0;
        j0 = 2;
        RD = 141.5 / (api60 + 131.5);
        d = RD * 999.016;
        Temp = tempL;
        d60 = d;
        Pres = 0;
        tC90 = (Temp - 32) / 1.8;
        T = tC90 / 630;

        // Coefficients for calculating DTT
        a1 = -0.148759;
        a2 = -0.267408;
        a3 = 1.08076;
        a4 = 1.269056;
        a5 = -4.089591;
        a6 = -1.871251;
        a7 = 7.438081;
        a8 = -3.536296;
        DTT = (a1 + (a2 + (a3 + (a4 + (a5 + (a6 + (a7 + a8 * T) * T) * T) * T) * T) * T) * T) * T;
        TC68 = tC90 - DTT;
        TF68 = 1.8 * TC68 + 32;

        // Select constants k0, k1, and k2 based on the type of crude (ABD)
        if (ABD.equals("A")) {
            k0 = 341.0957;
            k1 = 0;
            k2 = 0;
        } else if (ABD.equals("D")) {
            k0 = 0;
            k1 = 0.34878;
            k2 = 0;
        } else if (ABD.equals("B") && d60 < 770.352) {
            k0 = 192.4571;
            k1 = 0.2438;
            k2 = 0;
        } else if (ABD.equals("B") && d60 < 787.5195) {
            k0 = 1489.067;
            k1 = 0;
            k2 = -0.0018684;
        } else if (ABD.equals("B") && d60 < 838.3127) {
            k0 = 330.301;
            k1 = 0;
            k2 = 0;
        } else if (ABD.equals("B") && d60 < 1163.5) {
            k0 = 103.872;
            k1 = 0.2701;
            k2 = 0;
        }

        // Select constant j0 based on the type of crude (ABD)
        if (ABD.equals("A")) {
            j0 = 2;
        } else if (ABD.equals("D")) {
            j0 = 1;
        } else if (ABD.equals("B") && d60 < 770.352) {
            j0 = 1.5;
        } else if (ABD.equals("B") && d60 < 787.5195) {
            j0 = 8.5;
        } else if (ABD.equals("B") && d60 < 838.3127) {
            j0 = 2;
        } else if (ABD.equals("B") && d60 < 1163.5) {
            j0 = 1.3;
        }

        // Calculate observed API gravity
        Da = j0;
        A = 0.01374979547 / 2 * ((k0 / d60 + k1) / d60 + k2);
        B = (Da * k0 + k1 * d60) / (k0 + (k1 + k2 * d60) * d60);
        dr = d60 * (1 + (Math.exp(A * (1 + 0.8 * A)) - 1) / (1 + A * (1 + 1.6 * A) * B));
        a60 = (k0 / dr + k1) / dr + k2;
        DTr = TF68 - 60.0068749;
        CTLB = Math.exp((-a60 * DTr * (1 + 0.8 * a60 * (DTr + 0.01374979547))));
        Fp = 0;
        CPL2 = 1 / (1 - Fp * Pres * 0.00010);
        CTPL2 = CTLB * CPL2;
        CTPL2 = CTPL2;
        DeObs = d60 * CTPL2;
        APIOBS = (141.5 / (DeObs / 999.016)) - 131.5;
        APIOBS = (double)Math.round(APIOBS * 10d) / 10;
        return APIOBS;
    }

    /**
     * Method to calculate the Correction for the effect of Temperature on the Shell (CTSH)
     * @return (CTSH)
     */
    public double ctsh() {
        // Local variables
        double deltaT, ctsh, tS;

        // Calculate the shell temperature and delta temperature
        tS = (tAmb + 7 * tempL) / 8;
        tS = (double) Math.round(tS * 1d) / 1;
        deltaT = tS - tLam;

        // Calculate CTSH using the delta temperature
        ctsh = 1 + 0.0000062 * 2 * (deltaT) + Math.pow(0.0000062, 2) * Math.pow(deltaT, 2);
        ctsh = (double) Math.round(ctsh * 100000d) / 100000;
        return ctsh;
    }

    /**
     * Method to calculate the floating roof adjust
     * @return FRA
     */
    public double fra() {
        // Local variable
        double fra;

        // Calculate fraction using kFra1, kFra2, and observed API gravity
        fra = (kFra1 - apiObs()) * kFra2;
        fra = (double) Math.round(fra * 100d) / 100;
        return fra;
    }

    /**
     * Method to calculate Gross Observed Volume
      * @return (GOV)
     */
    public double gov() {
        // Local variable
        double gov;

        // Calculate GOV using total observed volume, water volume, CTSH, and fraction
        gov = (tov - waterTov) * ctsh() + fra();
        return gov;
    }

    /**
     * Method to calculate Gross Standard Volume (GSV)
     * @return (GSV)
     */
    public double gsv() {
        // Local variable
        double gsv;

        // Calculate GSV using GOV and CTL
        gsv = (double) Math.round(gov() * ctl() * 100d) / 100;
        return gsv;
    }

    /**
     * Method to calculate Net Standard Volume (NSV)
     * @return (NSV)
     */
    public double nsv() {
        // Local variable
        double nsv;

        // Calculate NSV using GOV, CTL, and Base Sediment and Water (BSW)
        nsv = (double) Math.round((gov() * ctl() * (1 - bsw / 100)) * 100d) / 100;
        return nsv;
    }

    /**
     * Method to print the calculated volumes, control log
     */
    public void print() {
        System.out.println("tov = " + tov);
        System.out.println("gov = " + gov());
        System.out.println("gsv = " + gsv());
        System.out.println("nsv = " + nsv());
    }
}