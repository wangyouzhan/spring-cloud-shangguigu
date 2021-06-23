package org.example.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routeLocatorBuilder = builder.routes();
        routeLocatorBuilder.route("path_route_atguigu", r->r.path("/guonei").uri("http://news.baidu.com/"));
        return routeLocatorBuilder.build();
    }


    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder)
    {

        return null;
    }





}
