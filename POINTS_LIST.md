# 罗列这个项目中的练习知识点20190929-至今

 一、在springboot中，如何干掉if else!
 # src\main\java\com\example\demo\exercise\delete_if_else
 # 利利用策略模式简化过多的if else代码
 # 原文参考 https://juejin.im/post/5c551122e51d457fcc5a9790
 
 二、逾期情况下，还款计划中期数最小的一期的逾期天数
 # src\main\java\com\example\demo\test\InoutPlanMinOverdueDay.java
 # 逾期三期，逾期期数最小的是第一期，且逾期天数最大为62天
 # 同理使用lambda表达式也可以
 
 三、自定义注解
 # src\main\java\com\example\demo\knowledge\zidingyi_interface\controller\ReportInoutPlanAOPController.java
 # 以加密形式存储到数据库中
 # 以解密方式从数据库中取出
 # 本例使用将客户姓名使用AES进行加密存储到数据库中，从数据库中使用AES解密从数据库中取出解密后的客户姓名
 # 注：数据库中存储的是加密后的数据，存储的是密文数据，但是取出来是通过AES解密后的明文数据
 
 四、Redis: lua脚本支持以及抢红包案例的简单实现
 # src\main\java\com\example\demo\test\redis_grab_red_packet\RedisLuaDemo.java
 # 参考网址：https://blog.csdn.net/hanchao5272/article/details/99868549
 # 首先要连接redis，启动redis
 # 在/usr/local/redis/bin的安装目录下./redis-server，启动redis
 # 启动./redis-cli下 可以修改密码  127.0.0.1:6379> config set requirepass 123456，之后OK，代表新密码设置成功
 
 #注：再次参考https://blog.csdn.net/weixin_43936628/article/details/86511583
 #    提供另一思路
 
 五、JAVA复制Map对象 （深拷贝和浅拷贝）
 参考网址：https://www.cnblogs.com/Marydon20170307/p/9132042.html
 这个网址上描述有误，经测试putAll是深拷贝，与Map和HashMap无关
 相关类：src\main\java\com\example\demo\test\listmap\service\TongDunListMapService.java
 遇到的问题是：在风控的对账有个商户的两个产品的sql一样，得出的listMap结果一样，在第一个listMap得出结果并且封装上其他两个属性后
              再对第二个sql得出的时候，listMap没有封装也有这两个属性，且修改了第二个listMap对象后，第一个listMap也跟着改变了
 解决方法是利用Map对象的深拷贝 putAll
