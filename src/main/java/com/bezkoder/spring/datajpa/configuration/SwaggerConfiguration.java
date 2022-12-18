package com.bezkoder.spring.datajpa.configuration;

import com.bezkoder.spring.datajpa.configuration.swagger.SwaggerDevelopmentSettings;
import com.bezkoder.spring.datajpa.configuration.swagger.SwaggerProductionSettings;
import com.bezkoder.spring.datajpa.configuration.swagger.SwaggerSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfiguration {

    @Value("${springfox.documentation.swagger-ui.enabled}")
    private boolean enabled;

    @Bean
    public Docket createRestApi() {
        SwaggerSettings swaggerSettings;
        if(this.enabled){
            swaggerSettings = new SwaggerDevelopmentSettings();
        }else{
            swaggerSettings = new SwaggerProductionSettings();
        }
        return swaggerSettings.settingSwaggerRules();
    }

    private ApiInfo apiInfo() {
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
