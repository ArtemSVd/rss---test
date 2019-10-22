package com.aggregator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation
        .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config
        .annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation
        .web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation
        .web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

/**
 * Конфигурационный класс. Предназначен для конфигурации Spring Security.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.aggregator")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Обхект для подключения к базе данных.
     * Данная зависимость взята из HibernateConfig.
     */
    @Autowired
    @Qualifier("dataSrc")
    private DataSource dataSource;

    /**
     * Метод настраивает доступ к адресам, включает loginPage и logout.
     * @param http объект HttpSecurity, произвдоит настройку безопасности
     * @throws Exception выбрасывается при вызове csrf
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/register", "/reset", "/", "/category",
                            "/news", "/prev", "/next")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/currentUser")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    /**
     * Метод для конфигурирования менеджера авторизации.
     * @param auth - менеджер авторизация
     * @throws Exception выбрасывается при
     * проблемах с созданием запросов к базе
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username as principal, " +
                        "password as credentials, " +
                        "'true' as enabled from usr where username=?")
                .authoritiesByUsernameQuery(
                        "select u.username, 'USER' as roles from usr u " +
                                " where u.username=?");
    }

}
