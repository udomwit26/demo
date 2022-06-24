package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebMvc
public class WebConfig implements ApplicationContextAware, WebMvcConfigurer {
    @Autowired
    private ThymeleafProperties properties;
    @Autowired
    private SpringTemplateEngine templateEngine;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/resources/scripts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
    }

    @Bean
    public ThymeleafViewResolver javascriptThymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setContentType("application/javascript");
        resolver.setViewNames(new String[]{"*.js"});
        resolver.setCache(this.properties.isCache());
        return resolver;
    }

    @Bean
    public SpringResourceTemplateResolver javascriptTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setOrder(1);
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".js");
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setCheckExistence(true);
        resolver.setCacheable(this.properties.isCache());
        return resolver;
    }

    @Bean
    public ThymeleafViewResolver htmlThymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setContentType("text/html");
        resolver.setViewNames(new String[]{"*.html"});
        resolver.setCache(this.properties.isCache());
        return resolver;
    }

    @Bean
    public SpringResourceTemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setOrder(0);
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setCheckExistence(true);
        resolver.setCacheable(this.properties.isCache());
        return resolver;
    }
}

