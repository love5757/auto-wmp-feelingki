package kr.yerina.wmp.autonomousRegistration.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class WmpAspect {

    @Before("execution(* kr.yerina.wmp.autonomousRegistration.controller..*.*(..))")
    public void logBefore(JoinPoint joinPoint) throws Throwable {

        log.info("logBefore()");
        log.info("method : " + joinPoint.getSignature().getName());
        log.info("arguments : " + Arrays.toString(joinPoint.getArgs()));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("requset uri : "+request.getRequestURI());
        log.info("remote addr : "+ getUserIP(request));
        log.info("******");
    }

    @After("execution(* kr.yerina.wmp.autonomousRegistration.controller..*.*(..))")
    public void logAfter(JoinPoint joinPoint) throws Throwable {

        log.info("logAfter()");
        log.info("method : " + joinPoint.getSignature().getName());
        log.info("arguments : " + Arrays.toString(joinPoint.getArgs()));
        log.info("******");
    }


    public String getUserIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        log.info("TEST : X-FORWARDED-FOR : "+ip);
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("TEST : Proxy-Client-IP : "+ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("TEST : WL-Proxy-Client-IP : "+ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info("TEST : HTTP_CLIENT_IP : "+ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("TEST : HTTP_X_FORWARDED_FOR : "+ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
