## **常于同好争高下，不与傻子论长短** 
DONE 
整合SpringDataJPA 整合Mysql数据库  整合Redis 整合认证授权JWT
整合全局异常处理 整合自定义异常 开发用户角色权限 整个Swagger
整合websocket 整合日志配置 整合单元测试junit（存在事务问题）

TODO 
扩展JPA方法 封装flowable常用方法 集成流程编辑器
整合I18N国际化 整合liquibase数据库管理

* 持久层
> 默认的事务传播机制为每个持久对象一个事务即一个业务层增删改两个持久对象 第一个成功第二个失败时，第一个持久层数据会提交。
>> 如果期望一起成功一起回滚须在方法上添加@Transactional(rollbackOn = Exception.class) 可捕捉特定异常

* Convert.convert(List.class, demoEntityList); 无法进行entity与VO之间转化 
> 解决方案调用 MyBeanUtils.convert

* 集成flowable的时候遇到流程相关的表没法创建
> mysql的连接字符串中添加上nullCatalogMeansCurrent=true，将schema默认设置为当前连接的schema。

* 整合认证授权JWT 
> 构建了两个注解 UserloginToken 在方法上加上此注解会被拦截器识别进行权限验证 
> PassToken 在方法上加上此注解会不需要认证（暂时什么都不加也可达到此效果） 

* 框架必须依赖项放入根pom，模块依赖根据所需进行添加
> 具体细节参考user demo模块

* 增加过滤器链修复前后端分离请求的跨域问题
> 具体实现其实是通过过滤器在response中追加header. 在类CorsFilter中

* 整合单元测试junit 所有模块必须添加单元测试，否则不可merge代码。
>没有单元测试的代码不是一个好代码，望各位对自己写的代码负责，怀着敬畏之心
>如何增加自己的单元测试： 参考demo模块的例子
>>单元测试的数据库默认为H2数据库，每次测试都会生成新的H2数据库，避免垃圾数据导致测试失败
>>如果想要修改 请修改test模块下的配置文件。

* 工具类库采用开源项目hutool https://www.hutool.cn/
> 此工具类库包罗万象，日常开发已经足够，具体用法请结合官方文档和源码单元测试。