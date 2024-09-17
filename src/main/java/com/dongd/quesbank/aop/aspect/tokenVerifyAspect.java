package com.dongd.quesbank.aop.aspect;
import com.dongd.quesbank.utils.JwtHelper;
import com.dongd.quesbank.utils.Result;
import com.dongd.quesbank.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;


@Aspect
@Order(-200)
@Component
public class tokenVerifyAspect {

    @Autowired
    private JwtHelper jwtHelper;


    public static ThreadLocal<Integer> uidThreadLocal=new ThreadLocal();

    @Pointcut("@annotation(com.dongd.quesbank.aop.TokenVerify)")
    public void tvfPointCut(){}


    @Around("tvfPointCut()")
    public Object tokenVerify(ProceedingJoinPoint joinPoint){
        try{

            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            String token=request.getHeader("Authorization");

            token=token.substring(1,token.length()-1);

            boolean expiration =jwtHelper.isExpiration(token);

            if(expiration)return Result.build(null, ResultCodeEnum.TOKEN_EXPIRED);

            int userId=jwtHelper.getUserId(token).intValue();

            uidThreadLocal.set(userId);

            Object proceed = joinPoint.proceed();

            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }finally {
            uidThreadLocal.remove();
        }
    }

}
