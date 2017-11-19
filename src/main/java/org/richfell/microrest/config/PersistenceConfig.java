package org.richfell.microrest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
     * <code>DriverManagerDataSource</code> bean for our HSQLDB database.  Properties
     * in application.properties which are prefixed with "app.datasource" are used to
     * set additional properties of the bean.
     * 
     * @return the database data source bean
     */
    @Bean(name="microrestDataSource")
    @ConfigurationProperties(prefix="app.datasource")
    public DriverManagerDataSource driverMgrDataSourceBean()
    {
        DriverManagerDataSource bean = new DriverManagerDataSource();
        return bean;
    }

    /**
     * The entity manager factory bean.  It is configured to use the persistence unit
     * for the HSQLDB targeted by the <code>DataSource</code> bean and to use Hibernate
     * as the JPA provider.
     * 
     * @return the entity manager factory bean
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
