# springbootPro

# 一、aop中执行顺序
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


# 二、swagger的使用

# 首先引入两个依赖 springfox-swagger2 和 springfox-swagger-ui
# @Api 作用在类上，说明该类的作用 ，其中tags和description会显示在swagger的管理页面上
# @ApiOperation：注解来给API增加方法说明， 其中notes和value也会显示在swagger的管理页面上
# @OnlineApi这个注解是写的自定义的注解（新模块是开发人员写的，用于只有添加了ApiOperation注解的method才在API中显示）
#需要引入SwaggerConfig类

# swagger的访问路径为 http://localhost:8045/swagger-ui.html#

#三、ResultDTO的使用

# 依赖ResultUtils类，依赖CodeMsg类
# 返回ResultUtils.success()或者是ResultUtils.fail