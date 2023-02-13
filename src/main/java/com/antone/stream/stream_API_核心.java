package com.antone.stream;

import com.antone.enums.Status;
import com.antone.pojo.Employee;
import com.antone.pojo.Worker;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
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


    List<Worker> workers = Arrays.asList(
      new Worker(1,"张三1",18,9999.99, Status.BUSY),
      new Worker(2,"张三2",19,9999.97, Status.VACATION),
      new Worker(3,"张三3",12,9999.96, Status.FREE),
      new Worker(4,"张三4",13,9999.93, Status.BUSY),
      new Worker(5,"张三5",15,9999.92, Status.VACATION),
      new Worker(6,"张三6",13,9999.91, Status.BUSY),
      new Worker(7,"张三7",13,6999.91, Status.BUSY)
    );

    /**
     * stream 终止操作
     *查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch——检查是否至少匹配一个元素
     * noneMatch—检查是否沒有匹配所有元素
     * findFirst—返回第一个元素
     * findAny—返回当前流中的任意元素
     * count—返回流中元素的总个数
     * max—返回流中最大值
     * min——返回流中最小值
     */
    @Test
    public void test4(){
        boolean flag = workers.stream().allMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(flag);
        flag = workers.stream().anyMatch(e -> e.getStatus().equals(Status.VACATION));
        System.out.println(flag);
        flag = workers.stream().noneMatch(e -> e.getAge() == 18);
        System.out.println(flag);
        Optional<Worker> first = workers.stream().findFirst();
        //Optional是一个容器，为了避免空指针异常导致的程序崩溃，其中的orElse可以在空的时候，找一个新的替代
        System.out.println(first.orElse(new Worker(5,"张三5",15,9999.92, Status.VACATION)));
        //若不需要替代，也可以直接通过get获取输出
        System.out.println(first.get());

        // stream,流，普通流，串行流，单线程串行处理
        Optional<Worker> sany = workers.stream().findAny();
        System.out.println(sany.get());

        // parallelStream，并行流，即操作会由多个线程同时来执行过程，
        // 线程1：filter  ->  findAny
        // 线程2：filter  ->  findAny
        // ...
        // 谁找到算谁的
        Optional<Worker> any = workers.parallelStream()
                .filter(worker -> worker.getStatus().equals(Status.FREE))
                .findAny();
        // 并行的开始过滤，并找到其中任意一个状态为FREE的工人，谁先找到算谁的
        System.out.println("ANY:" + any.get());
        long count = workers.stream().count();
        System.out.println(count);
        //max min 要自定义比较的方式
        Optional<Worker> max = workers.stream().max(Comparator.comparing(Worker::getSalary));
        System.out.println(max.get());
        max = workers.stream().max((a,b) -> Double.compare(a.getSalary(), b.getSalary()));
        System.out.println(max.get());
        Optional<Double> sa = workers.stream().map(Worker::getSalary).max(Double::compare);
        System.out.println(sa.get());
        Optional<Worker> min = workers.stream().min(Comparator.comparing(Worker::getSalary));
        System.out.println(min.get());
    }

    /**
     * BinaryOperator 二元运算
     * 归约： reduce(T identity, BinaryOperator bo)
     * 归约： reduce(BinaryOperator bo)
     * 可以将流中元素反复结合起来，得到一个值
     *
     * 收集：collect 将流转换为其他形式，接收一个Collector接口的实现，
     * 用于给Stream中元素做汇总的方法
     */
    @Test
    public void test5(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //因为有起始值，不可能为空，即不会有空指针异常，故可直接用int,或者Integer接收
        int sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        //此处因为没有起始值，始终操作的都是对象，故可能会有空指针，需要用optional作为辅助容器接收
        Optional<Double> reduce = workers.stream().map(Worker::getSalary).reduce(Double::sum);
        System.out.println(reduce.get());
        //此处为避免为空可以安上备胎, -1.0表示计算工资总和失败
        System.out.println(reduce.orElse(-1.0));

        //收集，把员工名字全提取出来，放到一个集合中去
        List<String> nameList = workers.stream().map(Worker::getName).collect(Collectors.toList());
        System.out.println(nameList);
        Set<String> nameSet = workers.stream().map(Worker::getName).collect(Collectors.toSet());
        System.out.println(nameSet);
        HashSet<String> nameHashSet = workers.stream().map(Worker::getName).collect(Collectors.toCollection(HashSet::new));
        nameHashSet.forEach(System.out::println);
        Map<Integer, String> map = workers.stream().collect(Collectors.toMap(Worker::getId, Worker::getName));
        map.forEach((k,v) -> System.out.println(k + ":" + v));

        //分组
        Map<Status, List<Worker>> statusGroupMap = workers.stream().collect(Collectors.groupingBy(Worker::getStatus));
        statusGroupMap.forEach((k,v) -> System.out.println(k + ":" + v +"\n"));

        //多级分组：我先按照状态分，状态分完年龄分
        Map<Status, Map<String, List<Worker>>> groupByManyColMap = workers.stream().collect(Collectors.groupingBy(Worker::getStatus, Collectors.groupingBy(
                e -> {
                    if (e.getAge() > 35) {
                        return "中年";
                    } else {
                        return "青年";
                    }
                }
        )));
        System.out.println("----------------------------");
        groupByManyColMap.forEach((k,v) -> System.out.println(k + ":" + v +"\n"));
        //分区
        System.out.println("----------------------------");
        Map<Boolean, List<Worker>> partitionMap = workers.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        partitionMap.forEach((k,v) -> System.out.println(k + ":" + v +"\n"));

        //数值集合计算器
        System.out.println("----------------------------");
        DoubleSummaryStatistics statistics = workers.stream().collect(Collectors.summarizingDouble(Worker::getSalary));
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());

        //字符串集合计算器
        System.out.println("----------------------------");
        String collect = workers.stream().map(Worker::getName).collect(Collectors.joining());
        System.out.println(collect);
        collect = workers.stream().map(Worker::getName).collect(Collectors.joining(",","---","---"));
        System.out.println(collect);
    }



}
