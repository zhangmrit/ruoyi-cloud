package com.ruoyi.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SwaggerProvider
 * @PackageName com.ruoyi.gateway.config
 * @Description 
 * @Author daiz
 * @Date 2019/8/16 10:04
 * @Version 1.0
 */
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider
{
    public static final String      API_URI = "/v2/api-docs";

    private final RouteLocator      routeLocator;

    private final GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get()
    {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        // 取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        // 结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        // 打开下面注释可以自动扫描接入gateway的服务，为了演示，只扫描system
        // gatewayProperties.getRoutes().stream().filter(routeDefinition ->
        // routes.contains(routeDefinition.getId()))
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routeDefinition.getId().equals("ruoyi-system"))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources
                                .add(swaggerResource(routeDefinition.getId(), predicateDefinition.getArgs()
                                        .get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location)
    {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}