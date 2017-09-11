package com.tgl.java8.newInterface;

/**
 *Java 8中的接口现在支持在声明方法的同时提供实现，通过两种方式可以完成这种操作。
 *其一，Java 8允许在接口内声明静态方法。
 *其二，Java 8引入了一个新功能，叫默认方法。
 *
 */
public interface JDK8Interface１ {
    //jdk8之前变量必须是public、static、final的，方法必须是public、abstract的。由于这些修饰符都是默认的
    public static final int field = 0;

    public abstract void method();

    // static修饰符定义静态方法
    static void staticMethod() {
        System.out.println("接口中的静态方法");
    }

    // default修饰符定义默认方法
    default void defaultMethod() {
        System.out.println("JDK8Interface１接口中的默认方法");
    }
}