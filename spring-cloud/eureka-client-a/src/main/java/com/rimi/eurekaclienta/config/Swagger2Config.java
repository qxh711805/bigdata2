package com.rimi.eurekaclienta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createSwagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("business-api")
                .apiInfo(new ApiInfoBuilder().title("在线API").description("在线API说明").version("1.0").contact(new Contact("作者名称","地址","邮箱")).build())
                .select().apis(RequestHandlerSelectors.basePackage("com.rimi.eurekaclienta.controller"))
                .build();
    }
}
