package com.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Конфигурационный класс предназначен для настройки SpringMVC.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.aggregator")
public class WebConfig implements WebMvcConfigurer {
    /**
     * Метод задает пути для поиска представлений и их формат.
     * @return объект, определяющий какое представление необходимо отобразить
     * для определенного запроса
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver
                = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
