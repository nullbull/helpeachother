//package com.heo.service;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.aspectj.lang.Signature;
///**
// * @Auth justinniu
// * @Date 2018/9/26
// * @Desc
// */
//@Component
//@Aspect
//public class AOPTest {
//    @Pointcut("execution(public * com.heo.dao..*.*(..))")
//    public void Point() {}
//    @Around("Point()")
//    public void HH(ProceedingJoinPoint joinPoint) throws Throwable {
//        Around(joinPoint);
//        joinPoint.proceed();
//    }
//    public void Around(ProceedingJoinPoint joinPoint) {
//        System.out.println("start>>>>>>>>>>>>>>>>>>>>>>>>>");
//        //传入的参数
//        Object[] arguments = joinPoint.getArgs();
//        //此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
//        Signature signature = joinPoint.getSignature();
//
//        //获取参数名
//        MethodSignature methodSignature = (MethodSignature) signature;
//        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
//            //每个参数的名字
//            System.out.println(methodSignature.getParameterNames()[i] + "   " + arguments[i]);
//        }
//    }
//}
