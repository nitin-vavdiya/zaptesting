package com.test.zaptesting.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Collections;

@Configuration
public class OpenAPIConfig {


    /**
     * Open api open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info();
        info.setTitle("Sample API to demonstrate API testing using ZAP");
        info.setDescription("Sample API to demonstrate API testing using ZAP");
        info.termsOfService("https://example.com");
        info.setVersion("0.0.1");

        Contact contact = new Contact();
        contact.name("ZAP API scaning");
        contact.email("nitinvavdiya@gmail.com");
        contact.url("https://example.com");
        info.contact(contact);

        License license = new License();
        license.name("Apache 2.0");
        license.url("http://apache.org");
        info.license(license);

        OpenAPI openAPI = new OpenAPI();
        openAPI = enableSecurity(openAPI);
        return openAPI.info(info);
    }

    /**
     * Open api definition grouped open api.
     *
     * @return the grouped open api
     */
    @Bean
    public GroupedOpenApi openApiDefinition() {
        return GroupedOpenApi.builder()
                .group("docs")
                .pathsToMatch("/**")
                .displayName("Docs")
                .build();
    }

    private OpenAPI enableSecurity(OpenAPI openAPI) {
        Components components = new Components();

        //Auth using access_token
        String accessTokenAuth = "Authenticate using API key";
        components.addSecuritySchemes(accessTokenAuth,
                new SecurityScheme().name(accessTokenAuth)
                        .description("Enter API key")
                        .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name(HttpHeaders.AUTHORIZATION));
        return openAPI.components(components)
                .addSecurityItem(new SecurityRequirement()
                        .addList(accessTokenAuth, Collections.emptyList()));
    }
}
