package com.antone.stream;

import com.antone.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 重点
 * 筛选与切片
 * filter—接收 Lambda，从流中排除某些元素。
 * limit—截断流，使其元素不超过给定数量。
 * skip（n）一 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit（n）互补distinct-筛选，通过流所生成元素的 hashCode（）和 equals（）去除重复元素
 */
public class stream_API_核心 {

    List<Employee> employees = Arrays.asList(
            new Employee("A",18,9999.99),
            new Employee("B",32,9991.99),
            new Employee("C",33,9992.99),
            new Employee("D",23,9992.99),
            new Employee("D",23,9992.99),
            new Employee("D",23,9992.99)
    );
    /**
     * 重点
     * 筛选与切片
     * filter—接收 Lambda，从流中排除某些元素。
     * limit—截断流，使其元素不超过给定数量。
     * skip（n）一 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit（n）互补
     * distinct-筛选，通过流所生成元素的 hashCode（）和 equals（）去除重复元素
     */
    @Test
    public void test1(){

        Stream<Employee> stream = employees.stream()
                .filter((e) -> e.getAge() > 1)
                .distinct();
        // 内部迭代 stream.forEach
        stream.forEach(System.out::println);

        System.out.println();

        // 外部迭代，迭代器
        Stream<Employee> stream1 = employees.stream();
        Iterator<Employee> iterator = stream1.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    /**
     * 映射
     * map—接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，
     * 该函数会被应用到每个元素上，并将其映射成一个新的元素，并返回一个新的流。
     * flatMap—接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test2(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("------------------");
        list.stream()
                .map(stream_API_核心::filterCharacter)
                .forEach(sm -> sm.forEach(System.out::println));
        System.out.println("------------------");

        list.stream().flatMap(stream_API_核心::filterCharacter).forEach(System.out::println);

        System.out.println("------------------");
        // 关于集合中 add 和 addAll的区别
        //add:如果添加的是一个集合，那么会把整个集合当作整体放进去
        List list2 = new ArrayList();
        list2.add(1);
        list2.add(2);
        list2.add(list);
        System.out.println(list2);
        list2.addAll(list);
        System.out.println(list2);

        //addAll:如果添加的是一个集合，那么会把整个集合中的元素一一拿出去，一个一个单独放进去

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch: str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted（）—自然排序 Comparable
     * sorted（Comparator com）—定制排序
     */
    @Test
    public void test3(){
        List<String> list = Arrays.asList("bbb","aa","ddd","fff","ee");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("------------------");

        //员工对象，没有自然排序，需要定制排序
        employees.stream().sorted((a,b) -> {
            return a.getAge() == b.getAge() ?
                    a.getName().compareTo(b.getName()) : -a.getAge().compareTo(b.getAge());
        }).forEach(System.out::println);
    }

    /**
     *
     */
    @Test
    public void test4(){

    }
}
