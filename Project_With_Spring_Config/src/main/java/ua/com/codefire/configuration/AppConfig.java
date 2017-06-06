package ua.com.codefire.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.com.codefire.dao.entity.StubEntity;
import ua.com.codefire.parser.CSVReader;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * Created by gleb on 06.06.17.
 */
@Configuration
@ComponentScan("ua.com.codefire")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject());
        return hibernateTemplate;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setAnnotatedClasses(StubEntity.class);
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    private DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMinimumIdle(5);
        ds.setMaximumPoolSize(10);
        ds.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        ds.addDataSourceProperty("url", environment.getProperty("db.url"));
        ds.addDataSourceProperty("user", environment.getProperty("db.user"));
        ds.addDataSourceProperty("password", environment.getProperty("db.password"));
        return ds;
    }

    private Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "false");
        return properties;
    }

    @Bean
    @Lazy
    public List<CSVRecord> recordsList() {
        return new CSVReader().getRecords();
    }
}
