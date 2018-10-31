package com.kaifuu.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.kaifuu.demo.mapper"}, sqlSessionTemplateRef = "demoSqlSessionTemplate")
public class DataSourceConfig {

    @Primary
    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "demo.datasource")
    @Qualifier("demoDataSource")
    public DataSource demoeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory demoSqlSessionFactory(@Qualifier(value = "demoDataSource") DataSource demoDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(demoDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setTypeAliasesPackage("com.kaifuu.demo.model");
            Resource[] resources = resolver.getResources("mapper/*.xml");
            bean.setMapperLocations(resources);
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean("demoSqlSessionTemplate")
    public SqlSessionTemplate demoSqlSessionTemplate(SqlSessionFactory demoSqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(demoSqlSessionFactory);
        return template;
    }
}
