package com.hskj.proxydynamic.demo;


public class RealSubject implements Subject
{

    public void request()
    {

        System.out.println("From real subject.");
    }

}

