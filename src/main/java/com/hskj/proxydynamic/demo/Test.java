package com.hskj.proxydynamic.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        /*
         * 被代理的对象(RealSubject)可以在运行时动态改变，需要控制的接口(Subject接口)可以在运行时改变，控制的方式(
         * DynamicSubject类)也可以动态改变，从而实现了非常灵活的动态代理关系
         */
        RealSubject rs = new RealSubject();
        InvocationHandler invocationHandler = new DynamicSubject(rs);

        Class cl = rs.getClass();

        Subject subject = (Subject) Proxy.newProxyInstance(cl.getClassLoader(), cl.getInterfaces(), invocationHandler);

        subject.request();
    }
    

}


/*
 * 实际的应用:spring中的AOP其实就是一个动态代理
 */