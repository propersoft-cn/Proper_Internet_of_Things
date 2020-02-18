DONE 
整合SpringDataJPA 整合Mysql数据库  整合Redis 整合认证授权JWT
整合全局异常处理 整合自定义异常 开发用户角色权限 整个Swagger
整合websocket

TODO 
 扩展JPA方法 封装flowable常用方法（集成流程编辑器）
整合I18N国际化   
整合日志配置 整合liquibase数据库管理

* 持久层
> 默认的事务传播机制为每个持久对象一个事务即一个业务层增删改两个持久对象 第一个成功第二个失败时，第一个持久层数据会提交。
>> 如果期望一起成功一起回滚须在方法上添加@Transactional(rollbackOn = Exception.class) 可捕捉特定异常

-------------------------------------------------
* Convert.convert(List.class, demoEntityList); 无法进行entity与VO之间转化 
> 解决方案调用 MyBeanUtils.convert

* 集成flowable的时候遇到流程相关的表没法创建
> mysql的连接字符串中添加上nullCatalogMeansCurrent=true，将schema默认设置为当前连接的schema。

* 整合认证授权JWT 
> 构建了两个注解 UserloginToken 在方法上加上此注解会被拦截器识别进行权限验证 
> PassToken 在方法上加上此注解会不需要认证（暂时什么都不加也可达到此效果） 

* 框架必须必须依赖项放入根pom，模块依赖根据所需进行添加