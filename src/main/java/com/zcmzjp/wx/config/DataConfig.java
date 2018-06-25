package com.zcmzjp.wx.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataConfig
{
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.url}")
    private String url;

    @Bean
    public ServletRegistrationBean druidServlet()
    {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), new String[] { "/druid/*" });
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        return servletRegistrationBean;
    }

    @Bean
    public DataSource dataSource()
            throws SQLException
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(this.url);

        dataSource.setConnectionProperties("config.decrypt=false");
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
        dataSource.setDbType("mysql");
        dataSource.setFilters("stat");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMinEvictableIdleTimeMillis(10000L);
        dataSource.setMaxActive(30);
        dataSource.setMaxWait(15000L);
        dataSource.setValidationQuery("SELECT 'x'");
        try
        {
            dataSource.setFilters("stat, wall");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public DruidStatInterceptor druidStatInterceptor()
    {
        return new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut()
    {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPatterns(new String[] { "com.zcmzjp.com.zcmzjp.wx.service.*" });
        return jdkRegexpMethodPointcut;
    }

    @Bean
    public Advisor druidAdvisor()
    {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut());
        defaultPointcutAdvisor.setAdvice(druidStatInterceptor());
        return defaultPointcutAdvisor;
    }

    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource)
    {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
