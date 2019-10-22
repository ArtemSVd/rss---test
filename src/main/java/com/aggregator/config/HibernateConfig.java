package com.aggregator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурационный класс.
 * Предназначен для создания подключения hibernate к базе данных.
 * Данные для конфигурации получены из файла application.properties
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    /**
     * jdbc драйвер, заданной базы данных.
     */
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER;
    /**
     * URL - адрес базы данных.
     */
    @Value("${spring.datasource.url}")
    private String URL;
    /**
     * Имя пользователя базы данных.
     */
    @Value("${spring.datasource.username}")
    private String USERNAME;
    /**
     * Свойство, которое определяет будут ли показываться в
     * консоли SQL - запросы.
     */
    @Value("${spring.jpa.show-sql}")
    private String SHOW_SQL;
    /**
     * Диалект конкретной базы данных.
     */
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String DIALECT;
    /**
     * Свойство задает то, как hibernate должен обслуживать таблицы
     *  обновлять (update) или создавать (create).
     */
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String DDL;
    /**
     * Пакеты, в которых hibernate будет искать сущности.
     */
    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;

    /**
     * Метод предназначен для создания подключения к базе данных.
     * @return bean, реализуемый в spring boot конфигурации
     */
    @Bean
    public DataSource dataSrc() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);

        return dataSource;
    }

    /**
     * Метод предназначен для создания SessionFactory,
     * объект этого класса позволяет создавать сессии
     * для работы с базой данных.
     * @return объект LocalSessionFactoryBean
     * сконфигурированный с помощью файла application.properties
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSrc());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);

        Properties hibernateProp = new Properties();

        hibernateProp.put("hibernate.dialect", DIALECT);
        hibernateProp.put("hibernate.show_sql", SHOW_SQL);
        hibernateProp.put("hibernate.hbm2ddl.auto", DDL);

        sessionFactory.setHibernateProperties(hibernateProp);

        return sessionFactory;
    }

}
