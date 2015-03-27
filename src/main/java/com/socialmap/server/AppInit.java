package com.socialmap.server;

import com.socialmap.server.config.AppConfig;
import com.socialmap.server.utils.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.EnumSet;
import java.util.Properties;

/**
 * Created by yy on 3/4/15.
 */
public class AppInit implements WebApplicationInitializer {
    private static final String SECURITY_FILTER_NAME = AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
    private static final String DISPATCHER_SERVLET_NAME = AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME;
    private static Logger log = LogManager.getLogger();

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        dropCreateDatabase();

        // 建立Spring的Application Context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        App.context = context;

        // 将创建的context放到ContextLoaderListener中，让其管理context的生命周期
        container.addListener(new ContextLoaderListener(context));

        // 添加Spring MVC必备Servlet：DispatcherServlet
        DispatcherServlet dispatcher = new DispatcherServlet(context);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic mvc =
                container.addServlet(DISPATCHER_SERVLET_NAME, dispatcher);
        mvc.setLoadOnStartup(1);
        mvc.addMapping("/*");

        // 在context中查找一个名字是filterChainProxy的Bean（这个Bean是Spring-Security的），然后通过这个
        // Bean来创建Spring框架的DelegatingFilterProxy（本身是一个代理，将过滤的任务委托给filterChainProxy，
        // filterChainProxy再把任务分配给Spring-Security的一系列filter）
        DelegatingFilterProxy proxy = new DelegatingFilterProxy(SECURITY_FILTER_NAME, context);
        FilterRegistration.Dynamic sec = container.addFilter(SECURITY_FILTER_NAME, proxy);
        sec.setAsyncSupported(true);
        sec.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

        // 添加自动转化UTF-8编码的Filter
        CharacterEncodingFilter encoding = new CharacterEncodingFilter();
        encoding.setEncoding("UTF-8");
        container.addFilter("characterEncodingFilter", encoding)
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

        OpenSessionInViewFilter session = new OpenSessionInViewFilter();
        container.addFilter("openSessionInViewFilter", session)
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
    }

    private void dropCreateDatabase() {
        try {
            String driver;
            String url;
            String user;
            String pass;
            Properties props = new Properties();
            props.load(getClass().getResourceAsStream("/application.properties"));
            driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.username");
            pass = props.getProperty("jdbc.password");

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            stmt.execute("drop database tbs");
            stmt.execute("create database tbs");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
