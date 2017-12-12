# AOP
先了解AOP的相关术语:

1.通知(Advice):

通知定义了切面是什么以及何时使用。描述了切面要完成的工作和何时需要执行这个工作。

2.连接点(Joinpoint):

程序能够应用通知的一个“时机”，这些“时机”就是连接点，例如方法被调用时、异常被抛出时等等。

3.切入点(Pointcut)

通知定义了切面要发生的“故事”和时间，那么切入点就定义了“故事”发生的地点，例如某个类或方法的名称，Spring中允许我们方便的用正则表达式来指定

4.切面(Aspect)

通知和切入点共同组成了切面：时间、地点和要发生的“故事”

5.引入(Introduction)

引入允许我们向现有的类添加新的方法和属性(Spring提供了一个方法注入的功能）

6.目标(Target)

即被通知的对象，如果没有AOP,那么它的逻辑将要交叉别的事务逻辑，有了AOP之后它可以只关注自己要做的事（AOP让他做爱做的事）

7.代理(proxy)

应用通知的对象，详细内容参见设计模式里面的代理模式

8.织入(Weaving)

把切面应用到目标对象来创建新的代理对象的过程，织入一般发生在如下几个时机: 
(1)编译时：当一个类文件被编译时进行织入，这需要特殊的编译器才可以做的到，例如AspectJ的织入编译器 
(2)类加载时：使用特殊的ClassLoader在目标类被加载到程序之前增强类的字节代码 
(3)运行时：切面在运行的某个时刻被织入,SpringAOP就是以这种方式织入切面的，原理应该是使用了JDK的动态代理技术

经典的基于代理的AOP:

Spring支持五种类型的通知：

- @Before(前) org.apringframework.aop.MethodBeforeAdvice
- @After-returning(返回后) org.springframework.aop.AfterReturningAdvice
- @After-throwing(抛出后) org.springframework.aop.ThrowsAdvice
- @Arround(周围) org.aopaliance.intercept.MethodInterceptor
- @Introduction(引入) org.springframework.aop.IntroductionInterceptor

# springboot中使用AOP

在springboot 中使用aop 主要有以下步骤： 
* 导入依赖 
* 创建注解 
* 编写切面 
* 使用注解

### 添加依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

### 创建注解
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogRecord {
    String key() default "";
}
```

### 编写切面
```java
@Aspect
@Component
public class LogInterceptor {

    //定义一个切入面，也就是一个切入点的集合
    @Pointcut("execution(public * com.rocky.boot.service..*.add(..))")
    public void myMethod() {
    }

    ;

    //定义一个注解方式的
    @Pointcut("@annotation(com.rocky.boot.aop.LogRecord)")
    public void annotationPointCut() {
    }

    ;

    // 方法执行之前执行，也就是在spring管理的对象的方法前执行前执行此方法
    // execution 切入点语法
//    @Before("execution(public void com.rocky.boot.dao.impl.UserDAOImpl.save(com.rocky.boot.model.User ))")
    @Before("annotationPointCut() && args(user,..)")
    public void beforeMethod(JoinPoint point, User user) {
        //切入点是加注释的那个方法
        System.out.println("Method : " + point.getSignature().getName());
        //方法参数集合
        Object[] s = point.getArgs();
        System.out.println("Method args : " + s[0].toString());
        System.out.println("start begin .........." + "user :" + user.getUsername());
    }

    //    @AfterReturning("execution(public void com.rocky.boot.dao..*.*(..))")
    //AfterReturning 是目标方法执行且返回后执行， rvt 方法的返回值，
    @AfterReturning(value = "annotationPointCut()", returning = "rvt")
    public void afterMethod(Object rvt) {
        //此处获取返回值为null，因为 @Around 的 advice 执行方法后没有把方法的返回值 返回，所以在AfterReturning 中获取的返回值为null
        System.out.println("start AfterReturning ....... and return : " + rvt);
    }

    //@annotation(log) 获取注解中的参数  @LogRecord(key="add is user")
    @Around("annotationPointCut()&& @annotation(log)")
    public void aroundMethod(ProceedingJoinPoint pjp, LogRecord log) throws Throwable {
        System.out.println("method around start ... LogRecord  key : " + log.key());
        //获取目标对象的返回值,打印返回值，但是我不return ，AfterReturning 拿不到 返回值，哈哈哈
        Integer i = (Integer) pjp.proceed();

        System.out.println("method around end ... and method return :" + i);
    }

}
```

### 使用注解
```java
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @LogRecord(key="add is user")
    public Integer add(User user) {
        userDAO.save(user);
        return 40;
    }
}
```

# 应用一：使用AOP统一处理Web请求日志
### 实现Web层的日志切面
实现AOP的切面主要有以下几个要素：

- 使用@Aspect注解将一个java类定义为切面类
- 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
- 根据需要在切入点不同位置的切入内容
    - 使用@Before在切入点开始处切入内容
    - 使用@After在切入点结尾处切入内容
    - 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
    - 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
    - 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
    
### 优化：AOP切面中的同步问题
引入ThreadLocal对象

### 优化：AOP切面的优先级
定义每个切面的优先级，我们需要@Order(i)注解来标识切面的优先级。i的值越小，优先级越高。

> [Spring Boot中使用AOP统一处理Web请求日志](http://blog.didispace.com/springbootaoplog/)