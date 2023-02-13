package com.antone.forkjoin;

import java.util.concurrent.RecursiveTask;

//分治框架，数据量越大越有优势！
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    //临界值：比如计算1到100亿，分治拆分，直到拆到10000为止，开始计算返回
    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(){

    }

    public ForkJoinCalculate(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THRESHOLD){
            long sum = 0;
            for(long i=start;i<=end;i++){
                sum += i;
            }
            return sum;
        }else {
            //若不是临界值，那么就拆分
            long mid = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, mid);
            left.fork(); // 拆分子任务，同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(mid + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
