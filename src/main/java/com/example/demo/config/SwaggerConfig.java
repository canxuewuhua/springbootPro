package com.example.demo.config;

import com.example.demo.common.OnlineApi;
import com.example.demo.common.TestApi;
import com.google.common.base.Predicate;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
    ParameterBuilder ticketPar = new ParameterBuilder();
    ParameterBuilder userId = new ParameterBuilder();
    List<Parameter> pars = new ArrayList<>();

    public SwaggerConfig(){
        super();
        ticketPar.name("requestId").description("Request Id")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的requestId参数非必填，传空也可以
        userId.name("WORLD-USER-ID").description("userId")
                .modelRef(new ModelRef("string")).parameterType("header").defaultValue("000")
                .required(false).build();
        pars.add(ticketPar.build());
        pars.add(userId.build());
    }

    @Bean
    public Docket onlineApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                //只有添加了ApiOperation注解的method才在API中显示
                if (input.isAnnotatedWith(OnlineApi.class)) {
                    return true;
                }
                return false;
            }
        };


        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("项目对外接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(onlineApiInfo());
    }

    private ApiInfo onlineApiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("项目接口文档平台")
                //详细描述
                .description("该模块展示的是所有对外的接口")
                //版本
                .version("1.0")
                .build();
    }



    @Bean
    public Docket testApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                //只有添加了ApiOperation注解的method才在API中显示
                if (input.isAnnotatedWith(TestApi.class)) {
                    return true;
                }
                return false;
            }
        };

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("项目测试相关接口")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("测试人员使用的接口")
                //详细描述
                .description("该模块展示的是所有测试人员使用的接口，不对外开放")
                //版本
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.world"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API在线文档")
                .description("欢迎使用API，在这里可以查看接口协议并测试")
                .termsOfServiceUrl("http://www.***.com")
                .version("1.0")
                .build();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
