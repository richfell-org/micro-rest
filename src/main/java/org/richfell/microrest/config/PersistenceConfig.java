package org.richfell.microrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring configuration for JPA using HSQLDB and Hibernate.
 * 
 * @author Richard Fellinger rich@richfell.org
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig
{
    /**
     * <code>DriverManagerDataSource</code> bean for our HSQLDB
     * database.
     * 
     * @return the database information
     */
    @Bean(name="microrestDataSource")
    public DriverManagerDataSource driverMgrDataSourceBean()
    {
        DriverManagerDataSource bean = new DriverManagerDataSource();
        bean.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        bean.setUrl("jdbc:hsqldb:mem:microrestdb");
        bean.setUsername("mra");
        bean.setPassword("mrau$3r");
        return bean;
    }

    /**
     * 
     * @return 
     */
    @Bean(name="microrestEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean()
    {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("org.richfell.microrest");
        bean.setPersistenceUnitName("microrest-persistence");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        //bean.setJpaProperties(jpaProperties);
        bean.setDataSource(driverMgrDataSourceBean());
        return bean;
    }

    /**
     * The persistence transaction manager bean.
     * 
     * @return the transaction manager bean
     */
    @Bean(name="transactionManager")
    public JpaTransactionManager transactionManagerBean()
    {
        JpaTransactionManager bean = new JpaTransactionManager();
        bean.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
        return bean;
    }
}
