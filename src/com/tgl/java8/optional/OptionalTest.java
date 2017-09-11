package com.tgl.java8.optional;

import java.util.Optional;

/**
 * 使用Optional避免null导致的NullPointerException
 * 无需在写大量的if(obj!=null)这样的判断了，前提是你得将数据用Optional装着，它就是一个包裹着对象的容器。
 *
 * 设计一个方法时，考虑将返回值用Optional包裹
 */
public class OptionalTest {

    public static void main(String[] args){
        //1.创建Optional对象
        //1.1 of()为非null的值创建一个Optional。of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。如果传入参数为null，则抛出NullPointerException。
        Optional<String> name = Optional.of("zhangsan");
        //Optional<String> nameNull = Optional.of(null);//java.lang.NullPointerException

        //1.2 ofNullable()为指定的值创建一个Optional，如果指定的值为null，则返回一个空的(empty)Optional。
        //ofNullable与of方法相似，唯一的区别是可以接受参数为null的情况。
        Optional<String> empty = Optional.ofNullable(null);

        //2.常用api
        //2.1 isPresent() 如果存在值就返回true，否则返回false。
        if (name.isPresent()) {
            //在Optional实例内调用get()返回已存在的值
            System.out.println(name.get());
        }
        //2.2 ifPresent(consumer)如果Optional实例有值则调用consumer，否则不做处理
        //理解ifPresent方法，首先需要了解Consumer类。简答地说，Consumer类包含一个抽象方法。该抽象方法对传入的值进行处理，但没有返回值。
        name.ifPresent((value) ->System.out.println("value:" + value));
        //2.3 orElse(default)如果有值则将其返回，否则返回指定的默认值。
        //如果Optional实例有值则将其返回，否则返回orElse方法传入的参数。
        System.out.println(empty.orElse("default"));
        //2.4 orElseGet()
        //orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，orElseGet方法可以接受Supplier接口的实现用来生成默认值。
        System.out.println(empty.orElseGet(() ->"default value"));
        //2.5 orElseThrow() 如果有值则将其返回，否则抛出supplier接口创建的异常。
        try {
            empty.orElseThrow(() ->new RuntimeException());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
