package fr.epita.test.service;

import fr.epita.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
public class JPATestContextConfiguration {
    @Bean(name = "data-source")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setPassword("test");
        dataSource.setUsername("sa");
        return dataSource;
    }
    @Bean(name = "session-factory")
    public LocalSessionFactoryBean factoryBean(@Qualifier("data-source") DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        String packages = "fr.epita.model";
        sessionFactoryBean.setPackagesToScan(packages);
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }
    @Bean(name = "transaction-manager")
    public HibernateTransactionManager transactionManager(@Qualifier("session-factory") SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
    @Bean(name = "address-jpadao")
    public AddressDAO getAddressJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new AddressDAO(sessionFactory);
    }
    @Bean(name = "contact-jpadao")
    public ContactDAO getContactJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new ContactDAO(sessionFactory);
    }
    @Bean(name = "movie-jpadao")
    public MovieDAO getMovieJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new MovieDAO(sessionFactory);
    }
    @Bean(name = "role-jpadao")
    public RoleDAO getRoleJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new RoleDAO(sessionFactory);
    }
    @Bean(name = "seenmovie-jpadao")
    public SeenMovieDAO getSeenMovieJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new SeenMovieDAO(sessionFactory);
    }
    @Bean(name = "user-jpadao")
    public UserDAO getUserJPADAO(@Qualifier("session-factory") SessionFactory sessionFactory) {
        return new UserDAO(sessionFactory);
    }
}
