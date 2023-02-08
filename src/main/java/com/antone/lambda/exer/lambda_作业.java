package com.antone.lambda.exer;

import com.antone.pojo.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class lambda_作业 {

    List<Employee> employees = Arrays.asList(
            new Employee("A",18,9999.99),
            new Employee("B",32,9991.99),
            new Employee("C",33,9992.99),
            new Employee("D",23,9992.99),
            new Employee("D",36,9992.99)
    );

    /**
     * 调用 Collections.sort（）方法，
     * 通过定制排序比较两个 Employee（先按年龄比，年龄相同按姓名比），使用Lambda作为参数传递。
     */
    @Test
    public void test(){
        Collections.sort(employees, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return - Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        employees.forEach(System.out::println);
    }

    /**
     * ①声明函数式接口，接口中声明抽象方法，public String getValue（String str）；
     * ②声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
     * ③再将一个字符串的第 2 个和第 4 个索引位置进行截取子串。
     */
    @Test
    public void test1(){
        String upperStr = getUpperStr("jbqkwejasdaab",str -> str.toUpperCase());
        System.out.println(upperStr);
        upperStr = getUpperStr("jbqkwejasdaab",str -> str.substring(2,5));
        System.out.println(upperStr);
    }

    public String getUpperStr(String str, MyFunction mf){
        return mf.getValue(str);
    }


    /**
     * ①声明一个带两个泛型的函数式接口，泛型类型为<T，R> T为参数，R为返回值
     * ②接口中声明对应抽象方法
     * ③在TestLambda类中声明方法，使用接口作为参数，计算两个long 型参数的和
     * ④再计算两个long型参数的乘积
     */
    @Test
    public void test2(){
        Long sum = operate(100l,200l, (a,b) -> a+b);
        System.out.println(sum);
        Long multiplication = operate(100l,200l, (a,b) -> a*b);
        System.out.println(multiplication);
    }

    public Long operate(Long a, Long b, MyFunction2<Long, Long> mf2){
        return mf2.test(a,b);
    }
}
