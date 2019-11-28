# springbootPro

一、aop中执行顺序
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


二、swagger的使用

# 首先引入两个依赖 springfox-swagger2 和 springfox-swagger-ui
# @Api 作用在类上，说明该类的作用 ，其中tags和description会显示在swagger的管理页面上
# @ApiOperation：注解来给API增加方法说明， 其中notes和value也会显示在swagger的管理页面上
# @OnlineApi这个注解是写的自定义的注解（新模块是开发人员写的，用于只有添加了ApiOperation注解的method才在API中显示）
#需要引入SwaggerConfig类

# swagger的访问路径为 http://localhost:8045/swagger-ui.html#

三、ResultDTO的使用

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

四 spring事务
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
#     参考https://my.oschina.net/zjllovecode/blog/1863103

五 关于xxl-job传入时间的问题
#  linux上传入的时间是GTC时间，也即时间标准时间，比中国时间晚了8个小时
#  所以传入的GTC时间，我们的Java项目接收json数据时间会转化为当前的CST时间，即中国时区时间
#  如果在xxl-job 上要需要获取昨天的GTC时间的话，需 cur_date=`date -d yesterday -u  "+%Y-%m-%dT%H:%M:%SZ"` 
#  示例   xxl上记录的时间 当前日期 2019-05-07T16:02:52Z
#        getPersonByDateAndName方法：打印传入的date时间：Wed May 08 00:02:52 CST 2019，建金中心，姓名：wangxiaomei
#       打印当前系统的时间：Thu May 09 00:02:52 CST 2019

六 ftp数据的上传和下载，可以使用FTPUtil,其中搭建ftp服务，可以在linux系统上搭建，搭建流程可参考
#    ftp服务在centos7上搭建流程  https://www.cnblogs.com/zhuozhang/articles/7856723.html
#    ftp的上传和下载可参考网址https://blog.csdn.net/qq_38380025/article/details/80679128  和 https://blog.csdn.net/rodge_rom/article/details/78888541

七 redis锁的应用
# 参考逾期跑批时加入redis锁，防止重复跑批

八 twitter的雪花算法
#    雪花算法产生的字符串的长度为18位，按递增，每秒可产生26万个不重复的ID
#
#     UUID是由一组32位数的16进制数字所构成，如550e8400-e29b-41d4-a716-446655440000
#     每秒产生10亿笔UUID，100年后只产生一次重复的机率是50%。如果地球上每个人都各有6亿笔GUID，发生一次重复的机率是50%。产生重复GUID并造成错误的情况非常低
#     UUID的唯一缺陷在于生成的结果串会比较长

#     自增ID：对于数据敏感场景不宜使用，且不适合于分布式场景。
#     GUID：采用无意义字符串，数据量增大时造成访问过慢，且不宜排序。

九 RabbitMQ之消息确认
#    参考https://www.cnblogs.com/wuzhiyuan/p/6862036.html
#    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息
#    注：做过的项目使用的是这种方式

#    System.out.println("消息已重复处理失败,拒绝再次接收...");
#    channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息

#    System.out.println("消息即将再次返回队列处理...");
#    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列

十 内存和cpu的关系
# CPU是负责运算和处理的，内存是交换数据的
# 当程序或者操作者对CPU发出指令，这些指令和数据暂存在内存里，在CPU空闲时传送给CPU，CPU处理后把结果输出到输出设备上，输出设备就是显示器，打印机等。

十一 http请求第三方接口加密加签和验签解密
# 在src\main\java\com\example\demo\test\sign\SignTest.java类中
# Java加解密技术系列之RSA详解  参考网址：https://www.jb51.net/article/96144.htm
# 数字签名
#   就是只有信息的发送者才能产生的，别人无法伪造的一段数字串，它同时也是对发送者发送的信息的真实性的一个证明
# 签名
#    对需要上送的报文[en_data]计算特征值[sign_block]，相关算法包括[md5、sha1、sha256、sha512等等]。
#    使用私钥[pri_key]对sign_block加密获得数字签名[sign]。
#    将sign与en_data打包发送给对方。
#验签
#    解析收到的报文，拆分为sign 及 en_data。
#    对en_data计算特征值[sign_block]，相关算法包括[md5、sha1、sha256、sha512等等双方协商]。
#    使用公钥[pub_key]对sign解密，获得sign_block1。
#    比较sign_block 和 sign_block1，若匹配则验证成功，报文未被篡改。

十二、ApiAuthAop的使用
# aop扫描com.example.demo.test.listmap.controller
# 所有访问这个controller下的接口，都会经过验签
# 比如说访问getUser接口，需要通过小米支付代发签名验证，首先这个controller上需要有  @ApiAuth(value = "MI_PAY_LOAN")
#之后通过 verifyChannelSignService.verifyMipayLoanSign(request)， 使用对方的公钥对sign进行解密，和原始的json串content进行比较，相等则通过签名验证
# 在上一步需要注意 name和age参数是封装在encryptData中的，postman里面不需要写name和age参数，再加上encryptKey和sign这三个参数即可访问验签代码

#通过验签后，正式访问接口，接口使用DTO进行接收参数，DTO上需要加上注解 @ModelAttribute @RequestBody，尤其是@ModelAttribute，否则会报错，它用于从model、
# Form表单或者URL请求参数中获取属性值
# 取出encryptKey，使用自己的私钥将encryptKey进行解密，得到原始AES KEY，再使用这个AES KEY将encryptData进行解密，得到原始的json串后进行业务操作


十三、redis在虚拟机上的启动
 # 在虚拟机上的位置在  /usr/local/redis/bin
 # 在bin目录下执行   ./redis-server /usr/local/redis/etc/redis.conf & (这种方法启动的时候，会去带上配置文件redis.conf，里面设置的有redis启动密码requirepass)
 # 之后执行redis客户端  ./redis-cli
 # auth 123456 (是进入客户端)
 #  设置密码 命令：config set requirepass 123456 
 #  查询keys   命令： keys *
 
十四、logback的使用
# 项目可以直接加上logback-spring.xml文件（直接添加就会生效），这样项目的打印日志就会如配置中的配置进行设置
# 如： spring-boot-wild [http-nio-48890-exec-1] [] - INFO  [UserController.java:41] : 打印say的结果

# 催收项目日志打印 
# 03:00:00.122 [http-nio-48810-exec-2] [42375a4b-7320-4827-abbe-d33fe97f279d] INFO  [BatchService.java:114] - 当前批次号：20190916，上期批次号：20190816
# 其中[http-nio-48810-exec-2] 是项目集群中的启用端口
# 其中[42375a4b-7320-4827-abbe-d33fe97f279d]这个是只要请求同一个接口的所有操作，这个id是一样的
# 日志拦截器LogInterceptor.java （@Component，自启动）日志拦截器，打印出该请求所有方法链的requestId
# 注意：logback是slf4j的官方实现，log4j是另一个实现，logback和log4j才是二选一，slf4j是门面日志的api。
# lombok注解中有   import lombok.extern.slf4j.Slf4j; 所以打印日志只需引入lombok注解即可

十五、http url 拼接
# src\main\java\com\example\demo\util\StringConcatUtil.java 拼接url参数字符串