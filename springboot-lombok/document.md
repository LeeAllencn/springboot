# @Getter/@Setter
如果想让lombok生效我们还需要针对idea工具进行插件的安装，下面我们按照顺序打开Idea配置File > Settings > Plugins > Browse repositories... > 输入lombok  
注意：如果你的属性Getter/Setter需要做特殊处理，那么直接使用原始方法实现即可，Lombok检查到存在自定义的方法后不会再做生成处理。

# @ToString

# @AllArgsConstructor
包含所有参数的构造器

# @NoArgsConstructor
无参构造器

# @Data
使用@Data注解就可以涵盖@ToString、@Getter、@Setter方法

# @Slf4j
在类上添加@Slf4j，效果等价于：  
```
private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ClassName.class);
```