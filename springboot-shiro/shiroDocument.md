# Apache Shiro

## 简介

Apache Shiro是一个功能强大、灵活的，开源的安全框架。它可以干净利落地处理身份验证、授权、企业会话管理和加密。

## Shiro能做什么？

- 验证用户身份
- 用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
- 在非 web 或 EJB 容器的环境下可以任意使用Session API
- 可以响应认证、访问控制，或者 Session 生命周期中发生的事件
- 可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
- 支持单点登录(SSO)功能
- 支持提供“Remember Me”服务，获取用户关联信息而无需登录
  …


##  特性

![ShiroFeatures.png](http://s1.wailian.download/2017/12/04/ShiroFeatures.png)

- **Authentication（认证）：**用户身份识别，通常被称为用户“登录”
- **Authorization（授权）：**访问控制。比如某个用户是否具有某个操作的使用权限。
- **Session Management（会话管理）：**特定于用户的会话管理,甚至在非web 或 EJB 应用程序。
- **Cryptography（加密）：**在对数据源使用加密算法加密的同时，保证易于使用。

还有其他的功能来支持和加强这些不同应用环境下安全领域的关注点。特别是对以下的功能支持：

- Web支持：Shiro 提供的 web 支持 api ，可以很轻松的保护 web 应用程序的安全。
- 缓存：缓存是 Apache Shiro 保证安全操作快速、高效的重要手段。
- 并发：Apache Shiro 支持多线程应用程序的并发特性。
- 测试：支持单元测试和集成测试，确保代码和预想的一样安全。
- “Run As”：这个功能允许用户假设另一个用户的身份(在许可的前提下)。
- “Remember Me”：跨 session 记录用户的身份，只有在强制需要时才需要登录。

## 高级概述

在概念层，Shiro 架构包含三个主要的理念：Subject,SecurityManager和 Realm。

![ShiroBasicArchitecture.png](http://s1.wailian.download/2017/12/04/ShiroBasicArchitecture.png)

- Subject：当前用户，Subject 可以是一个人，但也可以是第三方服务、守护进程帐户、时钟守护任务或者其它–当前和软件交互的任何事件。
- SecurityManager：管理所有Subject，SecurityManager 是 Shiro 架构的核心，配合内部安全组件共同组成安全伞。
- Realms：用于进行权限信息的验证，我们自己实现。Realm 本质上是一个特定的安全 DAO：它封装与数据源连接的细节，得到Shiro 所需的相关的数据。在配置 Shiro 的时候，你必须指定至少一个Realm 来实现认证（authentication）和/或授权（authorization）。

我们需要实现Realms的Authentication 和 Authorization。其中 Authentication 是用来验证用户身份，Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。

## shiro完整架构图

![shiro.png](http://s1.wailian.download/2017/12/04/shiro.png)



# RBAC

RBAC 是基于角色的访问控制（Role-Based Access Control ）在 RBAC 中，权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。这就极大地简化了权限的管理。这样管理都是层级相互依赖的，权限赋予给角色，而把角色又赋予用户，这样的权限设计很清楚，管理起来很方便。

则会对应5张表：用户表、角色表、权限表、用户角色表、角色权限表

# Shiro配置

## pom包依赖

```
<dependency>
  <groupId>org.apache.shiro</groupId>
  <artifactId>shiro-spring</artifactId>
  <version>${shiro.version}</version>
</dependency>
```

##  ShiroConfig类配置

```java
@Configuration
public class ShiroConfig {
  	
    /**
  	 * 要素一：自定义Realm
  	 */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

	/**
	 * 要素二：定义SecurityManager
	 */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
  
    /**
     * 要素三：定义Shiro过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
      
        // 设置未认证时的跳转url，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 要素四：开启shiro aop注解支持.使用代理方式，所以需要开启代码支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
```

Filter Chain定义说明：

- 1、一个URL可以配置多个Filter，使用逗号分隔
- 2、当设置多个过滤器时，全部验证通过，才视为通过
- 3、部分过滤器可指定参数，如perms，roles

Shiro内置的FilterChain

| Filter Name | Class                                    |
| ----------- | ---------------------------------------- |
| anon        | org.apache.shiro.web.filter.authc.AnonymousFilter |
| authc       | org.apache.shiro.web.filter.authc.FormAuthenticationFilter |
| authcBasic  | org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter |
| perms       | org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter |
| port        | org.apache.shiro.web.filter.authz.PortFilter |
| rest        | org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter |
| roles       | org.apache.shiro.web.filter.authz.RolesAuthorizationFilter |
| ssl         | org.apache.shiro.web.filter.authz.SslFilter |
| user        | org.apache.shiro.web.filter.authc.UserFilter |

- anon:所有url都都可以匿名访问
- authc: 需要认证才能进行访问
- user:配置记住我或认证通过可以访问

## 登录认证



通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO. Shiro的认证过程最终会交由Realm执行，这时会调用Realm的`getAuthenticationInfo(token)`方法。

该方法主要执行以下操作:

- 1、检查提交的进行认证的令牌信息
- 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
- 3、对用户信息进行匹配验证。
- 4、验证通过将返回一个封装了用户信息的`AuthenticationInfo`实例。
- 5、验证失败则抛出`AuthenticationException`异常信息。

而在我们的应用程序中要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo()，重写获取用户信息的方法。

doGetAuthenticationInfo()的重写:

```java
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException {
    //获取用户的输入的账号.
    String username = (String)token.getPrincipal();
    //通过username从数据库中查找 User对象，如果找到，没找到.
    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
    UserInfo userInfo = userInfoService.findByUsername(username);
    if(userInfo == null){
        return null;
    }
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
            userInfo, //用户名
            userInfo.getPassword(), //密码
            ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
            getName()  //realm name
    );
    return authenticationInfo;
}
```

## 权限管理

shiro的权限授权是通过继承`AuthorizingRealm`抽象类，重载`doGetAuthorizationInfo();`当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行，所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。在这个方法中主要是使用类：`SimpleAuthorizationInfo`进行角色的添加和权限的添加。

```java
@Override
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
  	SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
  	UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
    for(SysRole role:userInfo.getRoleList()){
        authorizationInfo.addRole(role.getRole());
        for(SysPermission p:role.getPermissions()){
          	authorizationInfo.addStringPermission(p.getPermission());
        }
    }
    return authorizationInfo;
}
```

当然也可以添加set集合：roles是从数据库查询的当前用户的角色，stringPermissions是从数据库查询的当前用户对应的权限

```
authorizationInfo.setRoles(roles);
authorizationInfo.setStringPermissions(stringPermissions);

```

就是说如果在shiro配置文件中添加了`filterChainDefinitionMap.put(“/add”, “perms[权限添加]”);`就说明访问/add这个链接必须要有“权限添加”这个权限才可以访问，如果在shiro配置文件中添加了`filterChainDefinitionMap.put(“/add”, “roles[100002]，perms[权限添加]”);`就说明访问`/add`这个链接必须要有“权限添加”这个权限和具有“100002”这个角色才可以访问。

## 登录实现

登录过程其实只是处理异常的相关信息，具体的登录验证交给shiro来处理

```java
// 此方法是请求login页面，而不是登录操作api；登录操作交给了shiro处理
@RequestMapping(value = "/login")
public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
  	// 登录失败从request中获取shiro处理的异常信息。
  	// shiroLoginFailure:就是shiro异常类的全类名.
    String exception = (String) request.getAttribute("shiroLoginFailure");
    System.out.println("exception=" + exception);
    String msg = "";
    if (exception != null) {
        if (UnknownAccountException.class.getName().equals(exception)) {
            System.out.println("UnknownAccountException -- > 账号不存在：");
            msg = "UnknownAccountException -- > 账号不存在：";
        } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
            System.out.println("IncorrectCredentialsException -- > 密码不正确：");
            msg = "IncorrectCredentialsException -- > 密码不正确：";
        } else if ("kaptchaValidateFailed".equals(exception)) {
            System.out.println("kaptchaValidateFailed -- > 验证码错误");
            msg = "kaptchaValidateFailed -- > 验证码错误";
        } else {
            msg = "else >> "+exception;
            System.out.println("else -- >" + exception);
        }
    }
    map.put("msg", msg);
    return "/login";
}
```

**上述登录实现是在ShiroConfig类中配置了shiroFilterFactoryBean.setLoginUrl("/login");因此将登录过程完全交给shiro处理，若想自定义登录api，则需该配置注释掉，并实现：**

```java
@RequestMapping(value = "/login", method = RequestMethod.POST)
@ResponseBody
public void login(@RequestParam("username")String username, @RequestParam("password")String password) {
	//当前Subject
    Subject currentUser = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(
      username, password.toCharArray());
    // token.setRememberMe(true);
    try {
  		currentUser.login(token);	// 执行到这时就会跳转到shiro的认证方法doGetAuthenticationInfo中去
        //if no exception, that's it, we're done!
	} catch ( UnknownAccountException uae ) {
    	//username wasn't in the system, show them an error message?
	} catch ( IncorrectCredentialsException ice ) {
    	//password didn't match, try again?
	} catch ( LockedAccountException lae ) {
    	//account for that username is locked - can't login.  Show them a message?
	}
    // ... more types exceptions to check if you want ...
	} catch ( AuthenticationException ae ) {
    	//unexpected condition - error?
	}
}
```

# 注入缓存

- 问题：不断的访问http://localhost:8085/userInfo/userAdd ，控制台不断出现权限配置-->MyShiroRealm.doGetAuthorizationInfo()
- 需求：这说明我们不断的访问权限信息，但是实际中我们的权限信息是不怎么会改变的，所以我们希望是第一次访问，然后进行缓存处理


## pom包依赖

```
<!-- https://mvnrepository.com/artifact/org.apache.shiro/shiro-ehcache -->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-ehcache</artifactId>
    <version>1.3.2</version>
</dependency>
<!--
包含支持UI模版（Velocity，FreeMarker，JasperReports），
邮件服务，
脚本服务(JRuby)，
缓存Cache（EHCache），
任务计划Scheduling（uartz）。
-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
</dependency>
```

## 缓存配置

```java
/**
 * shiro缓存管理器;
 * 需要注入对应的其它的实体类中：
 * 1、安全管理器：securityManager
 * 可见securityManager是整个shiro的核心；
 * @return
 */
@Bean
public EhCacheManager ehCacheManager(){
    System.out.println("ShiroConfiguration.getEhCacheManager()");
    EhCacheManager cacheManager = new EhCacheManager();
    cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
    returncacheManager;
}
/**
 * 将缓存对象注入到SecurityManager中
 */
@Bean
public SecurityManager securityManager(){
    DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
    //设置realm.
    securityManager.setRealm(myShiroRealm());

    //注入缓存管理器;
    securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;

    returnsecurityManager;
}
```

##  添加缓存配置文件ehcache-shiro.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache name="es">
 
    <diskStore path="java.io.tmpdir"/>
   
    <!--
       name:缓存名称。
       maxElementsInMemory:缓存最大数目
       maxElementsOnDisk：硬盘最大缓存个数。 
       eternal:对象是否永久有效，一但设置了，timeout将不起作用。 
       overflowToDisk:是否保存到磁盘，当系统当机时
       timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
       timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
       diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false. 
       diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。 
       diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
       memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。 
        clearOnFlush：内存数量最大时是否清除。
         memoryStoreEvictionPolicy:
            Ehcache的三种清空策略;
            FIFO，first in first out，这个是大家最熟的，先进先出。
            LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
            LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
    -->
     <defaultCache
            maxElementsInMemory="10000"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            overflowToDisk="false"
            diskPersistent="false"
            diskExpiryThreadIntervalSeconds="120"
            />
           
           
    <!-- 登录记录缓存锁定10分钟 -->
    <cache name="passwordRetryCache"
           maxEntriesLocalHeap="2000"
           eternal="false"
           timeToIdleSeconds="3600"
           timeToLiveSeconds="0"
           overflowToDisk="false"
           statistics="true">
    </cache>
   
</ehcache>
```

# 记住密码

```java
/**
 * cookie对象;
 * @return
 */
@Bean
public SimpleCookie rememberMeCookie(){
    System.out.println("ShiroConfiguration.rememberMeCookie()");
    //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    //<!-- 记住我cookie生效时间30天 ,单位秒;-->
    simpleCookie.setMaxAge(259200);
    return simpleCookie;
}

/**
 * cookie管理对象;
 * @return
 */
@Bean
public CookieRememberMeManager rememberMeManager(){
    System.out.println("ShiroConfiguration.rememberMeManager()");
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(rememberMeCookie());
    return cookieRememberMeManager;
}

// 将rememberMeManager注入到SecurityManager中
@Bean
public SecurityManager securityManager(){
    DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
    //设置realm.
    securityManager.setRealm(myShiroRealm());

    //注入缓存管理器;
    securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;

    //注入记住我管理器;
    securityManager.setRememberMeManager(rememberMeManager());

    return securityManager;
}


@Bean
public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
    System.out.println("ShiroConfiguration.shirFilter()");
    ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

    // 必须设置 SecurityManager 
    shiroFilterFactoryBean.setSecurityManager(securityManager);



    //拦截器.
    Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

    //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
    filterChainDefinitionMap.put("/logout", "logout");


    //配置记住我或认证通过可以访问的地址
    filterChainDefinitionMap.put("/index", "user");
    filterChainDefinitionMap.put("/", "user");


    //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
    //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
    filterChainDefinitionMap.put("/**", "authc");

    // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    //未授权界面;
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
}
```

主要是加入了：

//配置记住我或认证通过可以访问的地址

filterChainDefinitionMap.put("/index", "user");

filterChainDefinitionMap.put("/", "user");

 

修改登录界面加入rememberMe复选框：

在login.html中加入：

<P><input type="checkbox" name="rememberMe" />记住我</P>

这时候运行程序，登录之后跳转到http://localhost:8085/index页面，然后我们关闭浏览器重新打开，然后直接访问/index还是可以访问的，说明我们写的记住密码已经生效了，如果访问http://localhost:8085/userInfo/userAdd的话还是需要重新登录的。









