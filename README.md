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
# String.format(parm1,parm2,parm3)

十六、根据身份证地址的市级名称查询是否属于准入城市列表中之一
#  两个单元测试类 AddressListFilter是测试是否属于准入城市之一，AddressUtilsTest补全身份证的不全地址
#  用到service类 ： AddressService
#  用到DTO是 AddressDTO UserAreaDTO 还有一个解析准入城市列表的DTO CityNameDTO
#  使用上面的类可以实现 根据不全的地址信息推算完整的地址信息，以过滤满足条件的城市

十七、抽奖的例子
# src\main\java\com\example\demo\exercise\lottery\Lottery.java
# 随机产生一注奖或者多注奖，可以设置
十八、nginx配置负载均衡转发
# nginx安装之后执行./configuration make make install
# 之后会自动生成一个nginx文件夹，这个文件夹就是安装目录，之后对这个安装目录进行配置就行
# 此处浪费了几个小时，在nginx安装包内的配置文件进行操作，完全不起作用，最后发现应该操作的是安装后的目录
# 即自动生成的那个nginx目录，需要改这里面的配置才会生效
# 总结：应该从最简单的改动，比如改下nginx启动页面的显示信息，看下时候改动生效，只想着实现功能，最后却发现改配置
# 改错了，浪费了很长时间，寻找问题所在从最简单的地方入手，才能更快发现问题
# 之后就可以使用nginx实现减库存（redis分布式锁的实现20191211 00:30）

# 使用JMeter工具
# 加上锁之后测试发现出现超卖情况，需要继续研究为什么会出现并发，扣减剩余后的相同数字

十九、使用诸葛老师讲的redis分布式锁
在exercise文件夹下的deduct_stock减库存的例子中
# 第一个版本使用生成一个UUID，在解锁的时候判断该线程是否执行完，再解锁
# 为什么使用UUID呢，是因为第二个线程进来的时候，第一个线程把第二个线程的锁给删掉了
# 第一线程10s已经失效，在执行到15s的时候，第二个线程执行5s，给第二个线程上锁，这个时候第一个线程15s的时候就将第二个线程的锁给删除了，是会有问题的
# 这时候还会有第三个线程来，接着第四个，第五个
# 高并发场景下无法预料线程什么时候来，造成锁失效，就会有问题。如果在秒杀情况下，一万的库存，可能就会有几十万的订单

#本线程加的锁，被其他线程给释放掉了的问题解决，就是给每个线程加一个UUID，在释放锁的时候，判断这个线程的UUID（redis锁的value），判断要删除的锁的线程是否是
# 当前线程

# 第二个版本使用redission分布式锁
# 由于导入不了redisson的依赖，所以redisson只做了解
# RLock redissonLock = redisson.getLock(lockKey)
# redissonLock.lock(30, TimeUnit.SECONDS)
# redissonLock.unlock(); 
# 将Boolean relationIdLock =   注释掉
# 将finally里面的if语句也给去掉

# DateUtil增加一个方法用于计算出当前时间和第二天凌晨之间的相隔的秒数
 原本想通过这个方法将一个key设置为只有当天失效，其实也可以设置一个含有年与日的key，这样的话，第二天redis的key就会变为第二天的
  key，重新统计当天这个key存储的次数
  场景是：每个用户增加每日手输实名认证次数限制，每个自然日（0点-24点）有三次手输入实名认证机会，当手输实名请求次数大于3次时，拒绝请求，
  并给出前端返回相应错误码，这样我可以设置一个key= 前缀+客户号+年月日作为当天含若干次数的key，到第二天key重新取值
# 以後的测试类在单元测试中进行测试

# 删除链表中的一个节点  在leetcode目录下，以后关于链表的ListNode题目可以在此进行编写

# 提交对象copy，对象有对象list，该对象list命名一致，如果不一致要再次进行对象间的copy
  demo/beancopier/InoutPlanNotifyDTO、InoutPlanQueryDTO、ItemDTO、BeanCopyListObjectTest
  
# 1、[随机生成自带种子的随机数] [20200505]
     分布在0-10之间的客户编码 分布在11-99之间的客户编码 采用客户编码作为种子
     代码在util包里的 RandomUtil类
     
# 2、[日期格式化和短信随机6位][20200519]
   ①日期用String.format格式化 Date类型转化为需要的格式  ②生成一个短信6位随机码的三种方式 在test_demo中的DateSplitTest类中
# 3、[Java内存溢出和内存泄漏][20200519]
    内存溢出：是指程序在申请内存时，没有足够的内存空间供其使用，出现out of memory；比如申请了一个integer,但给它存了long才能存下的数，那就是内存溢出
    内存泄漏：是指程序在申请内存后，无法释放已申请的内存空间，一次内存泄露危害可以忽略，但内存泄露堆积后果很严重，无论多少内存,迟早会被占光。
    程序计数器，是唯一一个在java虚拟机规范中没有规定任何OutOfMemoryError的区域
    [因为]：程序计算器仅仅只是一个运行指示器，它所需要存储的内容仅仅就是下一个需要待执行的命令的地址，无论代码有多少，最坏情况下死循环也不会让这块内存
          区域超限，因为程序计算器所维护的就是下一条待执行的命令的地址，所以不存在OutOfMemoryError
# 4、[HashMap的四种遍历方式][20200519]
     ①方法1 Map<Integer, Integer> map = new HashMap<Integer, Integer>();
     for(Map.Entry<Integer, Integer> entry : map.entrySet()){
     System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue())
     此种方式在大数据量的时候效率最高
     如果你遍历的map是null的话，For-Each循环会抛出NullPointerException异常，所以在遍历之前你应该判断是否为空引用。
     但是经过测试map集合为空，for循环会不走的
     ②方法2 如果你只需要用到map的keys或values时，你可以遍历KeySet或者values代替entrySet 二次取值
     for (Integer key : map.keySet()) {
	 System.out.println("Key = " + key);
	 ③方法3 使用Iterator迭代 一次取值
	 Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
     while (entries.hasNext()) {
     	Map.Entry<Integer, Integer> entry = entries.next();
     	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
     }
     一个重要的特性是可以让你在迭代的时候从map中删除entries的(通过调用iterator.remover())唯一方法.如果你试图在For-Each迭代的时候删除entries，你将会得
     到unpredictable resultes 异常。
     ④迭代keys并搜索values（低效的）二次取值(性能差)
     for (Integer key : map.keySet()) {
	  Integer value = map.get(key);
	  System.out.println("Key = " + key + ", Value = " + value);
	 }
# 5、[int数组中三个数乘积最大值][20200601]
    ①先对数组排序Arrays.sort(nums);
    ②nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3] 或者 nums[0]*nums[1]*nums[nums.length-1]中的最大值
# 6、[校验银行卡号-卢恩算法][20200809]
    com.example.demo.exercise.valid_bank_card.ValidAccountNoTest
    1、校验卡号位数 16-19位
    2、使用卢恩算法luhn，又称模10算法进行校验
    银行卡号码的校验采用Luhn算法，校验过程大致如下：
    1. 从右到左给卡号字符串编号，最右边第一位是1，最右边第二位是2，最右边第三位是3….
    2. 从右向左遍历，对每一位字符t执行第三个步骤，并将每一位的计算结果相加得到一个数s。
    3. 对每一位的计算规则：如果这一位是奇数位，则返回t本身，如果是偶数位，则先将t乘以2得到一个数n，如果n是一位数（小于10），直接返回n，否则将n的个位数和十位数相加返回。
    4. 如果s能够整除10，则此号码有效，否则号码无效。
    因为最终的结果会对10取余来判断是否能够整除10，所以又叫做模10算法。
# 7、[计算经纬度位置gohash算法][20200809]   
    com.example.demo.util.GeoHashUtil
    计算经纬度的hash值  使用 GeoHashUtil工具类
    String geoHash = GeoHashUtil.encode(Double.valueOf(lbss[0]), Double.valueOf(lbss[1]));
    

