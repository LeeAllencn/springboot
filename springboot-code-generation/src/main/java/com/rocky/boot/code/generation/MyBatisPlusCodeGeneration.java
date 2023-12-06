package com.rocky.boot.code.generation;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author rocky
 * Description: 利用MybatisPlus插件自动生成代码
 * Created in 2019/3/30
 * 详细配置参考：https://mp.baomidou.com/config/generator-config.html
 */
public class MyBatisPlusCodeGeneration {

    // TODO ======自定义修改开始
    /**
     * 作者
     */
    private final static String AUTHOR = "rocky";
    /**
     * 包名
     */
    private final static String PACKAGE_NAME = "com.rocky.boot.code.generation";

    /**
     * 文件输出目录
     * private final static String OUTPUT_DIR = System.getProperty("user.dir") + "/springboot-code-generation/src/main/java";
     */
    private final static String OUTPUT_DIR = "C:\\Users\\Administrator\\Desktop";

    /**
     * 数据表前缀
     */
    private final static String TABLE_PREFIX = "";

    /**
     * 服务接口名称命名 eg:UserService or IUserService
     */
    private final static Boolean SERVICE_INTERFACE_NAME_START_WITH_I = true;

    /**
     * 数据源信息
     */
    private final static String DATASOURCE_URL = "jdbc:mysql://localhost:3306/db_springboot?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&nullCatalogMeansCurrent=true&serverTimezone=GMT%2b8";
    private final static String DATASOURCE_USERNAME = "root";
    private final static String DATASOURCE_PASSWORD = "";
    private final static String DATASOURCE_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String[] TABLE_NAMES = new String[]{"user"};

    /**
     * 用作数据类型转换
     */
    private final static String KEY_DATE = "date";
    private final static String KEY_TIME = "time";

    // TODO ======自定义修改结束

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
                .setOutputDir(OUTPUT_DIR)
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
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if (fieldType.toLowerCase().contains(KEY_DATE) || fieldType.toLowerCase().contains(KEY_TIME)) {
                            return DbColumnType.DATE;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
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
        // isCapitalMode:是否大写命名
        // entityLombokModel:是否为lombok模型
        // naming:数据库表映射到实体的命名策略
        // include：需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(true)
//                .setEntityColumnConstant(true)
                .setTablePrefix(TABLE_PREFIX)
                .setInclude(tableNames);

        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .execute();
    }
}
