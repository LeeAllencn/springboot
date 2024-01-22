package com.rocky.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 *
 * @author Rocky
 * @date 2017-08-14
 */

@Configuration
@MapperScan(basePackages = "com.rocky.boot.dao")
public class DataSourceConfiguration {

    @Resource
    private Environment env;

    @Bean
    public DataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
        druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
        druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
        druidDataSource.setFilters("config");
        Properties properties = new Properties();
        properties.put("config.decrypt",env.getProperty("druid.config.decrypt"));
        properties.put("config.decrypt.key",env.getProperty("druid.config.decrypt.key"));
        druidDataSource.setConnectProperties(properties);
        return druidDataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        //指定数据源(这个必须有，否则报错)
        fb.setDataSource(ds);
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        //指定基包
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        //指定xml文件位置
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Objects.requireNonNull(env.getProperty("mybatis.mapperLocations"))));

        // 配置分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        // 分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("pageSizeZero", "true");
        interceptor.setProperties(properties);
        fb.setPlugins(interceptor);
        return fb.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

