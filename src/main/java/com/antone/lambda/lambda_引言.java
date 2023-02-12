package com.antone.lambda;

import com.antone.pojo.Employee;
import com.antone.service.MyPredicate;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

public class lambda_引言 {


    /**
     * Day 1
     * 通过lambda表达式优化匿名内部类的臃肿
     */
    @Test
    public void test1(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    // 普通数组转list
    List<Employee> employees = Arrays.asList(
            new Employee("A",18,9999.99),
            new Employee("B",32,9991.99),
            new Employee("C",33,9992.99),
            new Employee("D",23,9992.99),
            new Employee("D",36,9992.99)
    );

    // 需求：获取员工年龄大于30的员工信息
    // 需求：获取工资大于5000的员工信息
    // 需求：获取...大于...的员工信息
    // ......
    // 需求：获取...大于...的员工信息
    public List<Employee> filterEmployee(List<Employee> list){
        ArrayList<Employee> empList = new ArrayList<>();
        for (Employee emp: list)
            if(emp.getAge() > 30)
                empList.add(emp);
        return empList;
    }

    /**
     * 面对这种多需求，但是代码99%都是一致的，只需要改其中的一小部分就能解决问题的情况
     * 如果一份一份复制，修改局部的1%，那整体的代码量增大，且让人感觉很没有必要
     * 但是根据java 8之前的语法不支持，只能通过设计模式来解决。但现在java 8语法支持了，看如何解决
     * 优化方式1：通过策略设计模式，设计接口和对应实现类，但每次更麻烦还要搞一个实现类，代码量更大了
     * 优化方式2：匿名内部类，实现不写实现类，实现接口中的函数
     * 优化方式3：java 8 lambda表达式新特性
     * 优化方式4：Stream API
     */

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        ArrayList<Employee> empList = new ArrayList<>();
        for (Employee employee : list){
            if(mp.test(employee)){
                empList.add(employee);
            }
        }
        return empList;
    }
    public List<Employee> filterEmployee2(List<Employee> list, Predicate<Employee> predicate){
        ArrayList<Employee> empList = new ArrayList<>();
        for (Employee employee : list){
            if(predicate.test(employee)){
                empList.add(employee);
            }
        }
        return empList;
    }
    @Test
    public void test2(){
        List<Employee> list = filterEmployee(employees, (e) -> e.getSalary() > 5000);
        list.forEach(System.out::println);
        System.out.println();
        list = filterEmployee2(employees, (e) -> e.getAge() > 35);
        list.forEach(System.out::println);
    }
    @Test
    public void test3(){
        employees.stream().filter((e) -> e.getAge() > 30).forEach(System.out::println);
    }
}
