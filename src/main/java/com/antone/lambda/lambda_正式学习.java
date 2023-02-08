package com.antone.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *  Lambda：语法糖
 *  一、 Lambda表达式的基础语法：Java8中引入了一个新操作符 "->"
 *  该操作符成为箭头操作符或者Lambda操作符
 *  箭头操作符将Lambda表达式拆分成两部分：
 *  左侧：Lambda表达式的参数列表
 *  右侧：Lambda表达式中所需要执行的功能，即Lambda体
 *  JAVA里面没有函数对象这个概念，lambda必须依附在一个接口上
 *  语法格式1：无参数，无返回值
 *      () -> System.out.println("No args and no return");
 *  语法格式2：有一个参数，无返回值（一个参数小括号可以省略）
 *      (arg) -> System.out.println("One arg and no return");
 *       arg -> System.out.println("One arg and no return");
 *  语法格式3：有多个参数，有返回值，且lambda体中有多条语句
 *      (arg1,arg2) -> {
 *          System.out.println("Many args and one return1");
 *          System.out.println("Many args and one return2");
 *          return Integer.compare(x,y);
 *      };
 *  语法格式4：Lambda表达式参数列表中的数据类型可以不写，因为JVM编译器会根据上下文自动推断数据类型
 *  类型推断：(Integer x, Integer y) -> Integer.compare(x,y);
 *
 *  打油诗：左右是一括号省，左侧推断类型省，return 也能省，能省则省
 * @FunctionalInterface：在接口上打上该注解(修饰)，那么该接口中只能有一个成员函数
 * 接口中函数默认抽象，就是你写与不写abstract它都自带了
 */
public class lambda_正式学习 {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello lambda!");
        runnable.run();

        Consumer<String> con = x -> System.out.println(x);
        con.accept("高级");

        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };

    }

    @Test
    public void test(){
        Integer n = operation(100, x -> 100 + x);
        System.out.println(n);
    }

    public Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }



}
