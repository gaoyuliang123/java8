package com.tgl.java8.newInterface;

public class JDK8InterfaceImpl implements JDK8Interface１,JDK8Interface２ {
    @Override
    public void method() {
        System.out.println("JDK8之前的方法");
    }
    // 类或者父类中声明的方法的优先级高于任何默认方法。如果无法解决冲突，那就供的默认方法
    // 如果两个接口中定义了一模一样的默认方法，并且一个实现类同时实现了这两个接口，那么必须在实现类中重写默认方法，否则编译失败
    @Override
    public void defaultMethod() {
        System.out.println("重写接口中的默认方法");
    }

    public static void main(String[] args) {
        //static方法必须通过接口类调用
        JDK8Interface１.staticMethod();

        new JDK8InterfaceImpl().defaultMethod();
        //default方法必须通过实现类的对象调用
        new JDK8InterfaceImpl().method();
    }
}

















































































