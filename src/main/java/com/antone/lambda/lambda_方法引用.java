package com.antone.lambda;

import com.antone.pojo.Employee;
import org.junit.Test;

import javax.annotation.processing.SupportedOptions;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * Day 2 - 方法引用与构造器引用
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用方法引用
 * 主要有三种语法格式：
 * 对象：：实例方法名
 * 类：：静态方法名
 * 类：：实例方法名(有特殊规则)
 *
 * 注意：
 * 1. 在lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一直！
 * 这样才能使用引用操作，否则不可
 * 2. Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用
 * ClassName::method
 *
 * 二、构造器引用
 * 格式：ClassName::new
 * 无参，有返回值，使用Supplier，用于引用无参构造函数
 * 一个参，有返回值，使用Function
 * 传两个参数，有返回值，使用BiFunction,Function的子类
 * 传更多参数，则需要自定义子类
 */
public class lambda_方法引用 {
    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> sup = () -> new Employee();
        Supplier<Employee> sup1 = Employee::new;
        Employee employee = sup1.get();
        employee.setName("HHH");
        System.out.println(employee.getName());

        Function<Integer,Employee> f = x -> new Employee(x);
        Employee apply = f.apply(1);
        System.out.println(apply.getAge());

        Function<Integer,Employee> fun = Employee::new;
        employee = fun.apply(10);
        System.out.println(employee.getAge());

        BiFunction<String,Integer,Employee> bf = (x,y) -> new Employee(x,y);
        Employee e1 = bf.apply("小明", 11);
        System.out.println(e1.getName() + "  "+ e1.getAge());

        BiFunction<String,Integer,Employee> bf1 = Employee::new;
        Employee e2 = bf1.apply("小明2", 19);
        System.out.println(e2.getName() + "  "+ e2.getAge());
    }

    // 类::实例方法名
    @Test
    public void test3(){
        BiPredicate<String,String> bp = (x,y)-> x.equals(y);
        BiPredicate<String,String> bp1 = String::equals;
        boolean res = bp1.test("a", "b");
        System.out.println(res);


    }


    // 对象::实例方法名
    @Test
    public void test(){
        //原来：需要指明参数
        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer1.accept("原来测试，需要指明参数");

        //新特性：如果已知了方法参数和返回值，那么直接调用就可以，无需进行传参操作
        PrintStream out = System.out;
        Consumer<String> consumer = out::println;
        consumer.accept("测试新特性：对象方法引用");

        //原来
        Employee emp = new Employee();
        emp.setName("AAA");
        Supplier<String> supplier = () -> emp.getName();
        String str = supplier.get();
        System.out.println(str);

        //新特性
        Supplier<String> supplier1 = emp::getName;
        System.out.println(supplier1.get());
    }

    @Test
    public void test1(){
        // 类：：静态方法名
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
        int res = com.compare(1, 2);
        System.out.println(res);
        res = com.compare(2, 2);
        System.out.println(res);
        res = com.compare(2, 1);
        System.out.println(res);

        //新特性
        Comparator<Integer> com1 = Integer::compare;
        res = com1.compare(1, 2);
        System.out.println(res);
    }



}
