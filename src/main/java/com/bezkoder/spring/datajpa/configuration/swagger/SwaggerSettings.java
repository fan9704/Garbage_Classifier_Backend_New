package com.bezkoder.spring.datajpa.configuration.swagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

public abstract class SwaggerSettings {
    public abstract Docket settingSwaggerRules();

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Garbage Classifier Backend")
                .description("Api Operation Socket")
                .version("0.0.1")
                .license("MIT LICENSE 2.0")
                .licenseUrl("https://github.com/fan9704")
                .contact(new Contact("FKT", "https://github.com/fan9704", "cxz123499@gmail.com"))
                .build();
    }
}
