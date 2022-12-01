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
@MapperScan(basePackages = {"com.duotest.repos.one"},sqlSessionTemplateRef = "sampleSqlSessionTemplate")
public class DataSourceConfig {
    @Bean("oneDataSourceProps")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSourceProperties appDatabaseProperty() {
        return new DataSourceProperties();
    }
    @Bean(name = "sampleDataSource")
    public DataSource sampleDataSource(@Qualifier("oneDataSourceProps") DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder
                .create()
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .url(dataSourceProperties.getUrl())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }

    @Bean(name = "sampleSqlSessionFactory")
    @Primary
    public SqlSessionFactory sampleSqlSessionFactory(@Qualifier("sampleDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /* При настройке нескольких источников данных здесь необходимо настроить конфигурацию mybatis */
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        bean.setTypeAliasesPackage("com.beans");
       // bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mapperconfig/mybatis-config.xml"));
        return bean.getObject();
    }

    @Bean(name = "sampleTransactionManager")
    @Primary
    public DataSourceTransactionManager sampleTransactionManager(@Qualifier("sampleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sampleSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sampleSqlSessionTemplate(@Qualifier("sampleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "sampleJdbcTemplate")
    public JdbcTemplate sampleJdbcTemplate(@Qualifier("sampleDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}