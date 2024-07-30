package com.api_gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {


    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/security/oauth/**").permitAll()
                .pathMatchers(HttpMethod.POST,
                        "/infotank/tank",
                        "/liquidacion/movimiento/crearmovimiento/{cargueId}").hasAnyRole("ADMIN", "USER")
                .pathMatchers(HttpMethod.PUT,
                        "/infotank/tank/update/{id}",
                        "/liquidacion/movimiento/calcularLiquidacion/{id}/",
                        "/liquidacion/movimiento/editarLiquidacion/{id}/").hasAnyRole("ADMIN", "USER")
                .pathMatchers(HttpMethod.DELETE,
                        "/infotank/tank/delete/{id}",
                        "/liquidacion/movimiento/eliminarMovimiento/{id}/").hasAnyRole("ADMIN", "USER")
                .pathMatchers(HttpMethod.GET,
                        "/analisis/quality/byId/{id}",
                        "/infotank/tank",
                        "/infotank/tank/byId/{id},",
                        "/liquidacion/cargue/cargueById/{cargueId}",
                        "/liquidacion/movimiento/movimientos",
                        "/liquidacion/movimiento/movimientoById/{id}",
                        "/usuarios/usuarios","/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
                .pathMatchers(HttpMethod.GET,
                        "/liquidacion/cargue/cargues").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and().addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();
    }

}