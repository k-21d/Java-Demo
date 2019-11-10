package com.k21d.java.java8concurrency.java7;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.LongAdder;

/**
 * 1-10累加
 */
public class ForkJoinDemo2 {
    public static void main(String[] args) {
        //并行：多核心参与
        //并发：一同参与
        //ForkJoin线程池 ForkJoinPool
        System.out.printf("当前CPU 处理器数：%d\n",Runtime.getRuntime().availableProcessors());
        System.out.printf("当前公用的ForkJoin线程池 并行数：%d\n", ForkJoinPool.getCommonPoolParallelism());
        //与ThreadPoolExecutor 类似
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //累加对象
        LongAdder longAdder = new LongAdder();
        //RecursiveAction 递归操作
        AddTask addTask = new AddTask(nums, longAdder);

        forkJoinPool.invoke(addTask);
        System.out.println(longAdder);
        forkJoinPool.shutdown();
    }


    private static class AddTask extends RecursiveAction {
        private final List<Integer> nums ;

        private final LongAdder longAdder;

        private AddTask(List<Integer> nums, LongAdder longAdder) {
            this.nums = nums;
            this.longAdder = longAdder;
        }

        @Override
        protected void compute() {
            int size = nums.size();
            if (size>1){
                int parts = size/2;
                //上半部
                List<Integer> leftPar = nums.subList(0,parts);
                AddTask leftTask = new AddTask(leftPar, longAdder);
                //下半部
                List<Integer> rightPar = nums.subList(parts,size);
                AddTask rightTask = new AddTask(rightPar, longAdder);
                invokeAll(leftTask,rightTask);
            }else {
                //当前只有一个元素
                if (size==0){//保护
                    return;
                }
                Integer value = nums.get(0);
                longAdder.add(value);
            }



        }
    }
}
