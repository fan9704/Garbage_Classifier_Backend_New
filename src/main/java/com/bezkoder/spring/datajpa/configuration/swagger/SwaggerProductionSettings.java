package com.bezkoder.spring.datajpa.configuration.swagger;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerProductionSettings extends SwaggerSettings {
    @Override
    public Docket settingSwaggerRules() {
        new Docket(
                DocumentationType.OAS_30)
                .enable(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
        return null;
    }
}
