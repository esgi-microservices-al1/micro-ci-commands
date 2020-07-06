package com.esgi.microservices.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/**")
                        .filters(f -> f.rewritePath("/api/v1/(?<remains>.*)", "/${remains}")
                                .preserveHostHeader()
                        )
                        .uri("lb://micro-ci-commands")
                        .id("micro-ci-commands")
                ).build();
    }
}
