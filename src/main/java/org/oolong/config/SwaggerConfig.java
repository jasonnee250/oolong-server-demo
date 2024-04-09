//package org.oolong.config;
//
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @Author: J.N
// * @Date 2023/9/30 23:34
// * @Version 1.0
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    public Docket createApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.oolong.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("oolong api test")
//                .description("oolong api")
//                .contact("J.N")
//                .version("1.0")
//                .build();
//    }
//
//}
