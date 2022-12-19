package com.bezkoder.spring.datajpa.configuration.swagger;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerDevelopmentSettings extends SwaggerSettings {
    @Override
    public Docket settingSwaggerRules() {
        return new Docket(
                DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/error.*").negate())
                .paths(PathSelectors.regex("/actuator.*").negate())
                .build();
    }
}
