package com.rocky.boot.codegeneration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author rocky
 * Description: 利用MybatisPlus插件自动生成代码
 * Created in 2019/3/30
 * 详细配置参考：https://mp.baomidou.com/config/generator-config.html
 */
public class MyBatisPlusCodeGeneration {

    /**
     * TODO 这部分自己定义修改
     */
    private final static String AUTHOR = "rocky";
    /**
     * 包名
     */
    private final static String PACKAGE_NAME = "com.rocky.boot";
    /**
     * 服务接口名称命名 eg:UserService or IUserService
     */
    private final static Boolean SERVICE_INTERFACE_NAME_START_WITH_I = true;
    private final static String DATASOURCE_URL = "jdbc:mysql://192.168.0.86:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
    private final static String DATASOURCE_USERNAME = "root";
    private final static String DATASOURCE_PASSWORD = "";
    private final static String DATASOURCE_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private final static String[] TABLE_NAMES = new String[]{"user"};

    public static void main(String[] args) {
        System.out.println("start...");
        generateByTables(TABLE_NAMES);
        System.out.println("completed...");
    }

    /**
     * @param tableNames 表名
     */
    private static void generateByTables(String... tableNames) {
        // 全局策略配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)
                .setAuthor(AUTHOR)
                .setOutputDir("D:\\MyBatisPlusCodeGeneration")
                .setFileOverride(true)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        if (!SERVICE_INTERFACE_NAME_START_WITH_I) {
            globalConfig.setServiceName("%sService");
        }

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(DATASOURCE_URL)
                .setUsername(DATASOURCE_USERNAME)
                .setPassword(DATASOURCE_PASSWORD)
                .setDriverName(DATASOURCE_DRIVER_CLASS_NAME);

        // 包名配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_NAME)
                .setController("controller")
                .setEntity("model")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mybatis.mapper");

        // 数据库表配置
        StrategyConfig strategyConfig = new StrategyConfig();
        /**
         * isCapitalMode:是否大写命名
         * entityLombokModel:是否为lombok模型
         * naming:数据库表映射到实体的命名策略
         * include：需要包含的表名，允许正则表达式（与exclude二选一配置）
         */
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityColumnConstant(true)
                .setInclude(tableNames);

        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .execute();
    }
}
