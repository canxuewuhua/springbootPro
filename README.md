# springbootPro

# **一、aop中执行顺序**
# @Pointcut定义切点
                              ③
                    @Before   → @Method
           ①          ↑②   ↙④
     AOP -------→  @Around
                       ↓⑤
                    @After
                       ↓⑥
                   @AfterReturn
                  
                  
# 反射机制允许程序在运行时取得任何一个已知名称的class的内部信息，包括包括其modifiers(修饰符)，fields(属性)，methods(方法)等
# 并可于运行时改变fields内容或调用methods
# 我们可以更灵活的编写代码，代码可以在运行时装配，无需在组件之间进行源代码链接，降低代码的耦合度；还有动态代理的实现等等
# 但是需要注意的是反射使用不当会造成很高的资源消耗
# getName()：获得类的完整名字
# getFields()：获得类的public类型的属性。
# getDeclaredFields()：获得类的所有属性。包括private 声明的和继承类
# getMethods()：获得类的public类型的方法。
# getDeclaredMethods()：获得类的所有方法。包括private 声明的和继承类
# getMethod(String name, Class[] parameterTypes)：获得类的特定方法，name参数指定方法的名字，parameterTypes 参数指定方法的参数类型。
# getConstructors()：获得类的public类型的构造方法。
# getConstructor(Class[] parameterTypes)：获得类的特定构造方法，parameterTypes 参数指定构造方法的参数类型
# newInstance()：通过类的不带参数的构造方法创建这个类的一个对象


# **二、swagger的使用**

# 首先引入两个依赖 springfox-swagger2 和 springfox-swagger-ui
# @Api 作用在类上，说明该类的作用 ，其中tags和description会显示在swagger的管理页面上
# @ApiOperation：注解来给API增加方法说明， 其中notes和value也会显示在swagger的管理页面上
# @OnlineApi这个注解是写的自定义的注解（新模块是开发人员写的，用于只有添加了ApiOperation注解的method才在API中显示）
#需要引入SwaggerConfig类

# swagger的访问路径为 http://localhost:8045/swagger-ui.html#

# **三、ResultDTO的使用**

# 依赖ResultUtils类，依赖CodeMsg类
# 返回ResultUtils.success()或者是ResultUtils.fail

# 三、redis的使用
#    redis数据库在本地虚拟机上启动
#    redis安装在 /usr/local/redis文件下 到bin目录下 启动 ./redis-server &
    
#    更改redis密码，使用命令  ./redis-cli (首先redis-server先要启动) 
#    config set requirepass 1234

#    使用redisdeskManager客户端连接redis数据库 命令keys *XX*  查找key为XX的数据缓存
#   备注：linux上安装redis的推荐网址：https://www.cnblogs.com/wangchunniu1314/p/6339416.html

# redis的使用配置 RedisConfig类， 项目一启动，就加载该类，包含一些redis数据库的连接信息
# 还有有一个 RedisUtilService类，该类是操作RedisTemplate，进行key的添加，删除和获取缓存值
# 在项目中使用RedisController作为入口进行redis使用的测试

# **四 spring事务**
# 1、要想事务起作用，必须是主方法名上有@Transactional注解，方法体内不能用try catch；如果要用，则catch中必须用throw new Exception
# 2、只有来自外部的方法调用才会把AOP代理捕捉，类内部方法嗲用类内部的其他方法，子方法并不会引起事务行为，即使被调用的方法上使用有@Transactional注解

# 总结: A方法有事务，调用了B方法，不管B有没有加事务注解，只要B抛出异常，两个方法都会回滚，（抛出异常一直向上抛，也不管有没有写throw）
#       如果将异常进行捕获，等于没抛出异常，自然也就不存在回滚。
#      什么情况下会回滚（只有在抛出异常的时候）
#      内层事务抛出异常，外层方法进行捕获。此时会有异常，此时需要在内层事务上加“PROPAGATION_NESTED”，即内层回滚，外层正常提交(注：该解释适用于内层事务在另外一个类中)
#           但如果在同一类中，内层事务是不起作用，外层事务捕获了异常，即两者都正常提交
#      同一类中事务A调用事务B，事务B会失效，如果事务A捕获了B的异常，B不会回滚，A正常提交，如果不对B进行捕获，AB 会抛出异常，事务也不会提交
#    《嵌套事务：就是事务方法A调用事务方法B，外层调用方法和内层被调用方法都是事务方法的情况》
#   我们一般情况下，会有以下三种需求：

#    外层调用方法和内层被调用方法，有异常一起回滚，没问题一起提交。（共用一个事务）
#    内层被调用方法回滚与否，不会影响外层调用方法。而外层调用方法出异常回滚，也不会回滚内层被调用方法（两个独立的事务）
#    内层被调用方法回滚与否，不会影响外层调用方法。而外层调用方法出异常回滚，也会回滚内层被调用方法（嵌套事务）
#  
#    此处有一个坑：在同一类中事务A方法，事务B方法，B方法有异常抛出，在A方法中进行了捕获，此时两者都会提交，因为B事务实际上是不生效的（不会回滚的），如果A方法不经捕获，
#    异常一直向上抛，A，B都不会提交。  现实的情况是有大批量的for循环，我们不能让它抛出异常，且要让它有异常的回滚（这才是问题）
#    疑问：如果for循环，内层事务抛错了，需要内层，外层也回滚，但是不能影响到for循环下一条的执行，怎么做
