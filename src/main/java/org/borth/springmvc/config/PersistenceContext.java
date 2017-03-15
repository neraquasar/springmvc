package org.borth.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by kd on 13.03.2017.
 */
@Configuration
@EnableJpaRepositories("org.borth.springmvc")
@EnableTransactionManagement
public class PersistenceContext
{
    private final Environment environment;

    @Autowired
    public PersistenceContext(Environment environment)
    {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource mainDataSource = new DriverManagerDataSource();
        mainDataSource.setDriverClassName(environment.getProperty("database.driver.name"));
        mainDataSource.setUrl(environment.getProperty("database.url"));
        mainDataSource.setUsername(environment.getProperty("database.account"));
        mainDataSource.setPassword(environment.getProperty("database.password"));
        return mainDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(environment.getProperty("database.generateDdl", Boolean.class));
        vendorAdapter.setDatabasePlatform(environment.getProperty("database.dialect"));
        vendorAdapter.setShowSql(environment.getProperty("database.showSql", Boolean.class));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.borth.springmvc");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager()
    {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        txManager.setJpaDialect(new HibernateJpaDialect());
        return txManager;
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory()
    {
        HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
        factory.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());
        return factory;
    }
}
