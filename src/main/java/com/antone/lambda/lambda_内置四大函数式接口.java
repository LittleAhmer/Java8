package com.antone.lambda;

import org.junit.Test;
import org.springframework.expression.spel.ast.Literal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 1. Consumer<T>：消费性接口 - void accept(T t);
 * 2. Supplier<T>: 供给型接口 - T get();
 * 3. Function<T,R>：函数型接口 - R apply(T t);
 * 4. Predicate<T>：断言型接口 - boolean test(T t);
 *
 * */
public class lambda_内置四大函数式接口 {

    @Test
    public void test(){
        //Consumer<T>
        consumer(1000, m -> System.out.println("赚了："+ m +"万"));
        System.out.println();

        //Supllier<T>
        List<Integer> list = supplier(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);
        System.out.println();

        //Function<T,R>
        Integer len = function("abcdefg", str -> str.length());
        System.out.println(len);
        System.out.println();

        //Predicate<T>
        List<String> strList = predicate(10, str -> str.length() > 18);
        strList.forEach(System.out::println);
    }


    private void consumer(int money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    private List<Integer> supplier(int n, Supplier<Integer> supplier) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<n;i++){
            Integer rand = supplier.get();
            list.add(rand);
        }
        return list;
    }

    private Integer function(String str, Function<String,Integer> function) {
        return function.apply(str);
    }

    /**
     * 小需求：
     * 1. 随机生成n个字符串
     * 2. 将字符串长度大于10的字符串添加到一个list中
     * 3. 最终返回该list
     */
    private List<String> predicate(int n, Predicate<String> predicate) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String uuid = UUID.randomUUID().toString();
            int a = (int) (Math.random() * 10);
            int b = (int) (Math.random() * 36);
            uuid.substring(a,b);
            if(predicate.test(uuid))
                list.add(uuid);
        }
        return list;
    }

}
