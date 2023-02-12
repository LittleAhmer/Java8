package com.antone.stream;

import com.antone.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream流创建的步骤
 * 1. 创建stream
 *
 * 2. 中间操作:在终止操作命令开始前，中间操作是一句都不会执行的
 *
 * 3. 终止操作
 *      Stream流属于懒加载，你不终止，他就不会执行，只有终止了，才会开始一系列的过程执行
 *      多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！
 *      而在终止操作时一次性全部处理，称为“惰性求值”。
 */
public class stream_API_创建流 {

    //创建流
    @Test
    public void test(){
        // 1. 通过Collection集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        // 2. 通过Arrays中的静态方法stream获取数组流
        Employee [] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        // 3.通过stream类中的静态方法 of()
        Stream<String> stream3 = Stream.of("a", "b", "c");

        // 4. 创建无限流
        Stream<Integer> iterate = Stream.iterate(1, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);

        // 通过流限制生成一些随机数并打印
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);

    }




}
