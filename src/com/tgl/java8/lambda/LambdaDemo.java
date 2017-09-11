package com.tgl.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaDemo {
    private static Map<String, Integer> items = new HashMap<>();

    static {
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
    }

    private static List<Person> personList = new ArrayList();

    static {
        personList.add(Person.Builder.builer().id(new Long(10)).address("shanghai").build());
        personList.add(Person.Builder.builer().id(new Long(12)).address("wuhan").build());
        personList.add(new Person.Builder().id(new Long(16)).address("nanjing").build());
        personList.add(new Person.Builder().id(new Long(18)).address("nanjing").build());
    }

    public static void main(String[] args) {
        //1.用lambda表达式实现Runnable
        new Thread(() -> {
            System.out.println("java8 lambada expression run method~~~~~~~");
        }).start();
        //2.使用lambda表达式对列表进行迭代
        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu", "qianqi");
        names.forEach(name -> System.out.println(name));
        //过滤
        //lambda表达式内可以使用方法引用，仅当该方法不修改lambda表达式提供的参数。本例中的lambda表达式可以换为方法引用，因为这仅是一个参数相同的简单方法调用。
        names.stream().filter(s -> s.contains("z")).forEach(System.out::println);
        //3.遍历map
        items.forEach((key, value) -> System.out.println("key:" + key + " value:" + value));

        //4.使用lambda表达式和函数式接口Predicate
        //使用 java.util.function.Predicate 函数式接口以及lambda表达式，可以向API方法添加逻辑，用更少的代码支持更多的动态行为。
        //下面的例子展示了过滤集合数据的多种常用方法。Predicate接口非常适用于做过滤。
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        filter(languages, (str)->{ return str.startsWith("J");});
        filter(languages, (str)->true);
        filter(languages, (str)->false);
        filter(languages, (str) -> str.length() > 4);

        //可以用and()、or()和xor()逻辑函数来合并Predicate
        Predicate<String> stratWith = str ->str.startsWith("J");
        Predicate<String> fourLetterLong  = str ->str.length()==4;
        languages.stream().filter(stratWith.and(fourLetterLong)).forEach(str ->System.out.println(str));

        //5.使用lambda表达式的Map和Reduce
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + 20).forEach(System.out::println);
        long total = costBeforeTax.stream().map((cost) -> cost + 20).reduce((sum, cost) ->sum + cost).get();
        System.out.println(total);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println(bill);

        //6.对列表的每个元素应用函数
        //通常需要对列表的每个元素使用某个函数，例如逐一乘以某个数、除以某个数或者做其它操作。这些操作都很适合用 map() 方法，可以将转换逻辑以lambda表达式的形式放在 map() 方法里，就可以对集合的各个元素进行转换了
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map((country) ->country.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(G7Countries);

        //7.复制不同的值，创建一个子列表
        //用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(n ->n*n).distinct().collect(Collectors.toList());
        System.out.printf("numbers: %s ; distinct: %s %n",numbers,distinct);

        //8.计算集合元素的最大值、最小值、总和以及平均值
        //IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。可以返回 IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistic s，描述流中元素的各种摘要数据
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt(n ->n).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

        //分组
        Map<String, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(p ->p.getAddress()));
        System.out.println(collect);

    }

    public static void filter(List<String> list, Predicate<String> condition) {
        list.stream().filter((String element) -> condition.test(element)).forEach((String element) -> System.out.println(element));
    }
}

//builder模式
class Person {
    private final Long id;
    private final String address;

    private Person(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public static class Builder{
        private  Long id;
        private  String address;

        public Builder(){
            this.id = null;
            this.address = null;
        }

        public Builder(Long id, String address) {
            this.id = id;
            this.address = address;
        }
        public static Builder builer() {
            return new Builder();
        }
        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}



