package com.example.basic.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.Constants;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenApiConfig {
//    @Bean
//    public GroupedOpenApi storeOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("board")
//                .pathsToMatch("/board/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi userOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("users")
//                .packagesToScan("com.example.basic.local.test").addOpenApiCustomiser(serverOpenApiCustomiser1()) //test.org.springdoc.api.app68.api.user
//                .addOperationCustomizer(operationCustomizer())
//                .build();
//    }
//
//    public OpenApiCustomiser serverOpenApiCustomiser1() {
//        Server server = new Server().url("http://toto.v1.com").description("myserver1");
//        List<Server> servers = new ArrayList<>();
//        servers.add(server);
//        return openApi -> openApi.setServers(servers);
//    }
//
//    public OpenApiCustomiser serverOpenApiCustomiser2() {
//        Server server = new Server().url("http://toto.v2.com").description("myserver2");
//        List<Server> servers = new ArrayList<>();
//        servers.add(server);
//        return openApi -> openApi.setServers(servers);
//    }
//
//    OperationCustomizer operationCustomizer() {
//        return (Operation operation, HandlerMethod handlerMethod) -> {
//            CustomizedOperation annotation = handlerMethod.getMethodAnnotation(CustomizedOperation.class);
//            if (annotation != null) {
//                operation.description(StringUtils.defaultIfBlank(operation.getDescription(), Constants.DEFAULT_DESCRIPTION) + ", " + annotation.addition());
//            }
//            return operation;
//        };
//    }
//
//    @Bean
//    public GroupedOpenApi petOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("hello")
//                .pathsToMatch("/hello/**").addOpenApiCustomiser(serverOpenApiCustomiser2())
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi groupOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("groups test")
//                .pathsToMatch("/v1/**").pathsToExclude("/v1/users")
//                .packagesToScan("com.example.basic.local.test", "com.example.basic.local.test.board")
//                .build();
//    }
//
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("bearer-key",
//                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
////                        .addSecuritySchemes("basicScheme",
////                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
//                .info(new Info()
//                        .title("Petstore API")
//                        .version("v0")
//                        .description("This is a sample server Petstore server.  You can find out more about     Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.")
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info().title("Demo API").version(appVersion)
                .description("Spring Boot 를 이용한 test api")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("timi121").url("https://blog.timi121.me/").email("timi121@test.com"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
