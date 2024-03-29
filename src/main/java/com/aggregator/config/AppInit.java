package com.aggregator.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support
        .AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Конфигурационный класс предназначен для настройки диспетчера сервлетов.
 */
public class AppInit
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(final ServletContext servletContext)
            throws ServletException {

        FilterRegistration.Dynamic
                filterRegistration = servletContext.addFilter(
                        "characterEncodingFilter",
                new CharacterEncodingFilter("UTF-8", true, true));

        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        filterRegistration =
                servletContext.addFilter(
                        "hiddenHttpMethodFilter",
                        new HiddenHttpMethodFilter());

        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        super.onStartup(servletContext);
    }
}
