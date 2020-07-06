package com.esgi.microservices.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route( r-> r.path("/api/v1/commands**")
//                        .filters(f->f.setResponseHeader("/api/v1/(?<remains>.*)","/${remains}")
//                                .preserveHostHeader()
//                        )
//                        .uri("http://localhost:8899")
//                )
////                .route(r -> r.path("/api/v1/**")
////                        .filters(f -> f.rewritePath("/api/v1/projects/(?<remains>.*)", "/${remains}")                                )
////                        .uri("lb://microservices")
////                        .id("microservices"))
//                .build();
//    }
}
