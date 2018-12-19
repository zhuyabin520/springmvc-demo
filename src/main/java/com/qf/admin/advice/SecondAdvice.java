package com.qf.admin.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 增强类
 */
@Aspect
public class SecondAdvice {

    @Pointcut("execution(* com.qf.admin.service.impl.UserServiceImpl.*(..))")
    public void pc(){}
    /**
     * 前置增强：在目标方法执行之前就会被执行
     */
    @Before("pc()")
    public void before() {
        System.out.println("这里是前置通知");
    }

    /**
     * 执行目标方法之后的后置增强
     */
    @AfterReturning("pc()")
    public void afterReturning(){
        System.out.println("这里是后置通知");
    }

    /**
     * 环绕增强，甚至都可以阻止目标方法的执行
     * @param pjp
     * @return
     */
    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("+++++++++++++++前");
        Object o = pjp.proceed();
        System.out.println("+++++++++++++++后");
        return o;
    }

    /**
     * 异常后增强
     */
    @AfterThrowing("pc()")
    public void afterException(){
        System.out.println("异常后增强");
    }

}
