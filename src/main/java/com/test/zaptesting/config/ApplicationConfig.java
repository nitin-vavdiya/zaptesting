package com.test.zaptesting.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smartsensesolutions.java.commons.specification.SpecificationUtil;
import org.apache.commons.text.StringEscapeUtils;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private final SwaggerUiConfigProperties properties;
    private final String resourceBundlePath;

    @Autowired
    public ApplicationConfig(@Value("${resource.bundle.path:classpath:i18n/language}") String resourceBundlePath, SwaggerUiConfigProperties properties) {
        this.resourceBundlePath = resourceBundlePath;
        this.properties = properties;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Bean
    public SpecificationUtil specificationUtil() {
        return new SpecificationUtil<>();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String redirectUri = properties.getPath();
        registry.addRedirectViewController("/", redirectUri);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename(resourceBundlePath);
        bean.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return bean;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean beanValidatorFactory = new LocalValidatorFactoryBean();
        beanValidatorFactory.setValidationMessageSource(messageSource());
        return beanValidatorFactory;
    }
}
