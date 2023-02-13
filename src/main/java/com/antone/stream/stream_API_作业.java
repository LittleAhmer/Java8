package com.antone.stream;

import com.antone.enums.Status;
import com.antone.pojo.Worker;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class stream_API_作业 {

    /**
     * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * ，给定【1，2，3，4，5】，应该返回【1，4，9，16，25】。
     */
    @Test
    public void test1(){
        Integer[] nums = {1,2,3,4,5};
        Arrays.stream(nums).map(n -> n*n).forEach(System.out::println);

    }

    /**
     * 2.用map和reduce数一数Work里有多少个对象
     */

    List<Worker> workers = Arrays.asList(
            new Worker(1,"张三1",18,9999.99, Status.BUSY),
            new Worker(2,"张三2",19,9999.97, Status.VACATION),
            new Worker(3,"张三3",12,9999.96, Status.FREE),
            new Worker(4,"张三4",13,9999.93, Status.BUSY),
            new Worker(5,"张三5",15,9999.92, Status.VACATION),
            new Worker(6,"张三6",13,9999.91, Status.BUSY),
            new Worker(7,"张三7",13,6999.91, Status.BUSY)
    );
    @Test
    public void test2(){
        Optional<Integer> reduce = workers.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}
