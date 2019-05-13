package com.kuradeon.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * test aspect
 * @author kuradeon
 * @date 2019-01-30 16:33
 */
@Component
@Aspect
public class TestAspect {

    @Pointcut("execution(* com.kuradeon.demo.controller.TestController.*(..))")
    private void testPointcut() {}

    /**
     * 后置通知
     */
    @After("testPointcut()")
    public void afterAdvice() {
        // 第⑤步，后置处理
        System.out.println("请求结束");
    }

    /**
     * 前置通知
     * @param joinPoint 被切入的类
     */
    @Before("testPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // 第②步，被切入方法执行前

        Object[] ps = joinPoint.getArgs();
        if (ps != null && ps.length > 0) {
            System.out.print("你的参数是：");
            for (Object p : ps) {
                System.out.print(p + ",");
            }
            System.out.println();
        }
    }

    /**
     * 环绕通知
     * @param joinPoint 被切入的类
     * @return 返回值
     * @throws Throwable e
     */
    @Around("testPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 第①步，开始执行
            System.out.println("开始接受请求");
            Object[] ps = joinPoint.getArgs();
            Object[] newP = new Object[ps.length];
            for (int i = 0; i < ps.length; i++) {
                newP[i] = "Hello " + ps[i];
            }
            // 第③步，执行被切入的方法
            return joinPoint.proceed(newP);
        } finally {
            // 第④步，方法执行后回到此处
            System.out.println("处理完毕");
        }
    }

    /**
     * 后置通知
     * @param retValue 返回值
     */
    @AfterReturning(value = "testPointcut()", returning = "retValue")
    public void afterReturningAdvice(Object retValue) {
        // 第⑥步，正常返回
        System.out.println("你的结果是：" + retValue);
    }

    /**
     * 异常后通知
     */
    @AfterThrowing(value = "testPointcut()", throwing = "e")
    public void afterThrowing(Exception e) {
        // 第⑥步，如果有异常
        System.out.println("发生异常了, message：" + e.getMessage());
    }
}
