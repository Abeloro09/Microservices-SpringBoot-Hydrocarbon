package com.api_calculation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing Liquidacion entities.
 *
 * <p>Note: No endpoints are exposed for this entity because no Liquidacion should be created
 * without having first created a Movimiento. Liquidaciones are created as part of creating a Movimiento.</p>
 */
@RestController
@RequestMapping("/liquidacion")
public class LiquidacionController {

    // No endpoints are provided for this entity because a Liquidacion cannot be created without
    // first creating a Movimiento. Liquidaciones are created when a Movimiento is created.

}
