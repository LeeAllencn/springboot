# OkHttp3
> [OKHttp官网](http://square.github.io/okhttp/)

> [OkHttp3使用指南](http://www.qingpingshan.com/rjbc/az/110232.html)

> [Bearer Token](http://www.haomou.net/2014/08/13/2014_bare_token/)

# 接口定义规范
### 1. 在controller层统一返回格式，使用ResultBean，有分页则使用PageResultBean（待完成），如：
```
@GetMapping("/all")
public ResultBean<Collection<Config>> getAll() {
    return new ResultBean<Collection<Config>>(configService.getAll());
}
@PostMapping("/add")
public ResultBean<Long> add(@RequestBody Config config) {
    return new ResultBean<Long>(configService.add(config));
}
```
### 2. 考虑失败的情况（即不要使用void，不返回任何数据）

### 3. 不要出现与业务无关的输入参数
- 当前用户信息都不应该出现在参数里面，应该从当前会话中获取。如：userId等

### 4. 不要出现复杂的输入参数
- 一般情况下，不允许出现例如json字符串这样的参数，这种参数可读性极差。应该定义对应的bean。如：
```
// 参数出现json格式，可读性不好，代码也难看
@PostMapping("/update")
public Map<String, Object> update(long id, String jsonStr) {
}
```

### 5. 返回应该返回的数据
- 新增（POST）接口一般情况下应该返回新对象的id标识

# controller层规范
1. 所有函数返回统一的ResultBean/PageResultBean格式
2. ResultBean/PageResultBean是controller专用的，不允许往后传！
3. Controller做参数格式的转换，不允许把json，map这类对象传到services去，也不允许services返回json、map。
4. 参数中一般情况不允许出现Request，Response这些对象
5. 不需要打印日志，日志在AOP里面会打印，而且建议大部分日志在Services这层打印。
6. AOP代码，主要就是打印日志和捕获异常，异常要区分已知异常和未知异常，其中未知的异常是我们重点关注的，可以做一些邮件通知啥的，已知异常可以再细分一下，可以不同的异常返回不同的返回码
**注：** 一般情况下！写过代码都知道，map，json这种格式灵活，但是可读性差，如果放业务数据，每次阅读起来都比较困难。定义一个bean看着工作量多了，但代码清晰多了。

### 为什么要返回统一的一个ResultBean？
- 为了统一格式
- 为了应用AOP
- 为了包装异常信息

### 先有统一的接口定义规范，然后有AOP实现。先有思想再有技术。

# 日志规范
### 要求：
- 能找到哪个机器
- 能找到用户做了什么

### 开发人员的日志要求：
- 修改（包括新增）操作必须打印日志
- 条件分支必须打印条件值，重要参数必须打印
```
// optype决定代码走向，需要打印日志
logger.info("edit user, opType:" + opType);

if (opType == CREATE) {
  // 新增操作
} else if (opType == UPDATE) {
  // 修改操作
} else {
  // 错误的类型，抛出异常
  throw new IllegalArgumentException("unknown optype:" + opType);
}
```
- 数据量大的时候需要打印数据量

### 培养打印日志的习惯
- 不要依赖debug，多依赖日志
- 代码开发测试完成之后不要急着提交，先跑一遍看看日志是否看得懂

# 异常处理
- 绝大部分场景，不允许捕获异常，不要乱加空判断。只有明显不需要关心的异常，如关闭资源的时候的io异常，可以捕获然后什么都不干，其他时候，不允许捕获异常，都抛出去，到controller处理。空判断大部分时候不需要，你如果写了空判断，你就必须测试为空和不为空二种场景，要么就不要写空判断。
- web请求上的异常，不允许开发人员捕获，直接抛到前台，会有controller处理
- 开发组长定义好异常，异常继承RuntimeException。
- 不允许开发人员捕获异常。（异常上对开发人员就这点要求！异常都抛出到controller上用AOP处理）
- 后台（如队列等）异常一定要有通知机制，要第一时间知道异常。
- 少加空判断，加了空判断就要测试为空的场景！

# 参数校验和国际化规范
> [编码习惯之参数校验和国际化规范](http://blog.didispace.com/cxy-wsm-zml-6/)

# 配置规范
> [编码习惯之配置规范](http://blog.didispace.com/cxy-wsm-zml-7/)

# 函数编写规范
1. 不要出现和业务无关的参数
2. 避免使用Map，Json这些复杂的数据对象作为参数和结果
3. 有明确的输入输出和方法名
4. 把可能变化的地方封装成函数
5. 编写能测试的函数
- 不要出现乱七八糟的参数，如参数里面有request，response就不好测试
- 把函数写小一点
- 要有单独测试每一个函数的习惯

# 常用架包
### org.apache.commons.lang3
```
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.4</version>
</dependency>
```
常用类：RandomUtils、StringUtils等

# 使用Collections.sort()对List\<Object\>进行排序

```
实体类
自定义比较器，实现接口Comparator
Collections.sort(list, comparator)实现排序
```