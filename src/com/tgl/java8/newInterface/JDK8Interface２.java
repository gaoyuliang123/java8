package com.tgl.java8.newInterface;

/**
 * 
 */
public interface JDK8Interface２ {

    // default修饰符定义默认方法
    default void defaultMethod() {
        System.out.println("JDK8Interface２接口中的默认方法");
    }
}