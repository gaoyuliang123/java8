package com.tgl.java8.lambda;

/**
 * lambda其实就是为@FunctionalInterface服务的，能够用一种全新的、简洁的语法创建函数式接口的对象。
 * 当然如果一个接口不符合函数式接口的定义，是不能通过lambda表达式来创建其对象的。
 * lambda其实就是定义入参、函数体、返回值，然后可以生成任意一个接口(符合该函数入参和返回值的函数式接口)的对象。
 */

/**
 * lambda表达式的一般语法：
 * (Type1 paranm1, Type2 param2) -> {
 *     statment1;
 *     statment2;
 *     .........;
 *     return statmentM;
 * }
 */

import java.util.Arrays;
import java.util.List;

/**
 *
 * Lambda表达式的特点：
 * 一个 Lambda 表达式可以有零个或多个参数
 * 参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
 * 所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
 * 空圆括号代表参数集为空。例如：() -> 42
 * 当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -> a*a
 * Lambda 表达式的主体可包含零条或多条语句
 * 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。
 * 匿名函数的返回类型与该主体表达式一致
 * 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。
 * 表达式就是Lambda的返回值，意味着你无需写return，若表达式没有返回则为空
 * 可以显示的通过return关键字返回值，但必须将主体写在{}中，如(Integer i) -> return "Alan" + i;是错误的写法Lambda表达式可以用变量接收。
 */
public class LambdaTest {

    public static void main(String[] args){

        /*lambda表达式各种简化版本*/
        //1、入参的类型是可以省略的（当然加上也没有问题），因为可以从上下文环境中推断出lambda表达式的参数类型。
        ManyArgsInterface mi1 = (int a, int b) -> System.out.println("no argument");
        //省略类型
        ManyArgsInterface mi2 = (a, b) -> System.out.println("no argument");
        //2、没有入参的时候，比如上面的Runnable接口，直接用()代表无参数。
        FunctionInterface fi = () -> System.out.println("函数式接口");
        fi.say();
        //3、当lambda表达式的参数个数只有一个，可以省略小括号。
        // 完整语法
        OneArgsInterface oneArgs1 = (String a) -> {System.out.println("arguments=" + a);};
        OneArgsInterface oneArgs2 = a -> System.out.println("argument:" + a);
        oneArgs2.findKeyWord("java");
        //4、当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号。
        OneArgsInterface oneArgs3 = a -> System.out.println("argument:" + a);

        /*lambda表达式其他特点*/
        //lambda内部可以使用静态、非静态和局部变量，这称为lambda内的变量捕获。
        //Lambda表达式在Java中又称为闭包或匿名函数
        //lambda表达式有个限制，那就是只能引用 final 或 final 局部变量，这就是说不能在lambda内部修改定义在域外的变量。
        List<Integer> primes = Arrays.asList(new Integer[]{2, 3,5,7});
        int factor = 2;
        //primes.forEach(element -> {factor++;}); //Compile time error : "local variables referenced from a lambda expression must be final or effectively final"
        primes.forEach(element -> { System.out.println(factor*element); }); //只是访问它而不作修改是可以的
    }
}
