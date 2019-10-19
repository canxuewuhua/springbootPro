package com.example.demo.aop;

import com.example.demo.common.ApiAuth;
import com.example.demo.common.CodeMsg;
import com.example.demo.common.service.VerifyChannelSignService;
import com.example.demo.common.service.VerifyMd5SignService;
import com.example.demo.exception.ServiceException;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@ComponentScan
@EnableAspectJAutoProxy
@Slf4j
public class ApiAuthAop {

    @Value("${api.auth.switch:true}")
    private boolean apiAuthSwitch;

    @Autowired
    private VerifyMd5SignService verifyMd5SignService;

    @Autowired
    private VerifyChannelSignService verifyChannelSignService;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.example.demo.test.listmap.controller.*.*(..))")
    public void api() {
    }

    @Around(value = "api()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        // 查找ApiAuth注解，先从方法上查找，方法上未找到然后从该对象类中查找(不找父类)
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        ApiAuth apiAuth = targetMethod.getDeclaredAnnotation(ApiAuth.class);
        if (apiAuth == null) {
            log.debug("方法{}.{}上没有ApiAuth注解，检查该类是否有ApiAuth注解", joinPoint.getTarget().getClass().getName(), targetMethod.getName());
            apiAuth = joinPoint.getTarget().getClass().getAnnotation(ApiAuth.class);
        }

        // 检查全局验签开关,全局开关只对内部签名有效
        if (!apiAuthSwitch && (apiAuth == null || "DEFAULT".equals(apiAuth.value()))) {
            log.error("签名验证开关已关闭，请检查生产环境是否已经开启签名验证！！！");
            return joinPoint.proceed();
        }

        boolean verifyResult = false;
        String apiAuthType = "DEFAULT";
        if (apiAuth == null) {
            log.debug("{}.{}在类和方法上都没有加ApiAuth注解，使用系统默认的验签方式", joinPoint.getTarget().getClass().getName(), targetMethod.getName());
            verifyResult = verifyMd5SignService.verifyDefaultSign(request);
        } else {
            // 检查该注解中的开关
            boolean authSwitch = apiAuth.authSwitch();
            if (!authSwitch) {
                log.debug("方法{}.{}有验签注解但是不做签名验证", joinPoint.getTarget().getClass().getName(), targetMethod.getName());
                return joinPoint.proceed();
            }

            // 检查验签类型
            apiAuthType = apiAuth.value();
            if ("DEFAULT".equals(apiAuthType)) {
                log.info("默认的网关内部签名验证");
                verifyResult = verifyMd5SignService.verifyDefaultSign(request);
            } else if ("MI_PAY_LOAN".equals(apiAuthType)) {
                log.info("小米支付代发签名验证");
                verifyResult = verifyChannelSignService.verifyMipayLoanSign(request);
            } else if ("MI_PAY_REPAYMENT".equals(apiAuthType)) {
                log.info("小米支付代扣签名验证");
                //verifyResult = verifyChannelSignService.verifyMipayRepaymentSign(request);
            }  else {
                log.error("未知的签名类型:{}", apiAuthType);
                throw new ServiceException(CodeMsg.SYSTEM_VERIFY_SIGN_FAIL);
            }
        }
        // 签名未通过直接返回
        if (!verifyResult) {
            return this.getChannelFailResult(apiAuthType);
        }
        Object result = joinPoint.proceed(joinPoint.getArgs());
        return result;
    }

    /**
     * 根据通道类型获取验签失败的返回值
     *
     * @param apiAuthType
     * @return
     */
    private Object getChannelFailResult(String apiAuthType) {

        switch (apiAuthType) {
            case "MI_PAY_LOAN":
                return ResponseEntity.ok("FAIL");
            case "MI_PAY_REPAYMENT":
                return ResponseEntity.ok("FAIL");
            default:
                return ResultUtils.fail(CodeMsg.SYSTEM_VERIFY_SIGN_FAIL);
        }

    }

}
