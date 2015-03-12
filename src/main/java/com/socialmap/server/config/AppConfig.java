package com.socialmap.server.config;

import com.socialmap.server.service.UserService;
import com.socialmap.server.utils.App;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by yy on 3/4/15.
 */
@Configuration
@ComponentScan("com.socialmap.server")
@Import({MvcConfig.class, SecurityConfig.class})
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.username"),
                env.getProperty("jdbc.password"));
        ds.setDriverClassName(env.getProperty("jdbc.driver"));
        return ds;
    }

    @Bean
    @Autowired
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("com.socialmap.server.model");
        Properties props = new Properties();
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.globally_quoted_identifiers", "true");
        builder.addProperties(props);
        return builder.buildSessionFactory();
    }

    @Bean
    @Autowired
    public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint point = new DigestAuthenticationEntryPoint();
        App.realm = env.getProperty("digest.realm");
        point.setRealmName(env.getProperty("digest.realm"));
        point.setKey(env.getProperty("digest.key"));
        return point;
    }

    @Bean
    @Autowired
    public DigestAuthenticationFilter digestFilter(DigestAuthenticationEntryPoint point, UserService userService) {
        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setUserDetailsService(userService);
        filter.setAuthenticationEntryPoint(point);
        filter.setPasswordAlreadyEncoded(true);
        return filter;
    }

    @Bean
    @Autowired
    public HibernateTemplate ht(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }
}
