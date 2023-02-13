package com.antone.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

//分治框架，数据量越大越有优势！
public class TestForkJoin {

    @Test
    public void test(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,1200000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start,end).toMillis());
    }
    @Test
    public void test1(){
        Instant start = Instant.now();
        Long sum = 0l;
        for (long i = 0;i<1200000000L;i++){
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start,end).toMillis());
    }

    /**
     * 刚才又是分治又是对比优化的，热火朝天很火爆，但是属于经典白学
     * 下面步入正题，java 8 stream并行流
     *  数据量小用sequential单线程足以，数据量大，百万，千万，亿级以上用最好！
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        LongStream.rangeClosed(0,9000000000L).parallel().reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start,end).toMillis());
        start = Instant.now();
        LongStream.rangeClosed(0,9000000000L).sequential().reduce(0,Long::sum);
        end = Instant.now();
        System.out.println("耗时：" + Duration.between(start,end).toMillis());
    }

}
