package com.duotest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.duotest.repos.two"},sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class DataSourceConfigTwo {
    @Bean("TwoDataSourceProps")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSourceProperties appDatabaseProperty() {
        return new DataSourceProperties();
    }
    @Bean(name = "twoDataSource")
    public DataSource sampleDataSource(@Qualifier("TwoDataSourceProps") DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder
                .create()
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .url(dataSourceProperties.getUrl())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }

    @Bean(name = "twoSqlSessionFactory")
    @Primary
    public SqlSessionFactory sampleSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /* При настройке нескольких источников данных здесь необходимо настроить конфигурацию mybatis */
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        bean.setTypeAliasesPackage("com.beans");
        // bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mapperconfig/mybatis-config.xml"));
        return bean.getObject();
    }

    @Bean(name = "twoTransactionManager")
    @Primary
    public DataSourceTransactionManager sampleTransactionManager(@Qualifier("twoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "twoSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sampleSqlSessionTemplate(@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "twoJdbcTemplate")
    public JdbcTemplate sampleJdbcTemplate(@Qualifier("twoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}