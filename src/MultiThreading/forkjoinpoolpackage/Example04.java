package MultiThreading.forkjoinpoolpackage;

/*
 * Execute
 * getActiveThreadCount
 * getAsyncMode : It tells that whether the pool serve task as FIFO or not
 * getCommonPoolParallelism :  parallelism level of the common pool in the integer form
 * getParallelism : parallelism level of the common Pool
 * getQueuedSubmissionCount : Gives you the lest tasks in the list
 * */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Example04 {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(2);
//        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println("Before pool start : " + pool.getActiveThreadCount());
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Hello from : " + Thread.currentThread().getName());
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Hello from : " + Thread.currentThread().getName());
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Hello from : " + Thread.currentThread().getName());
            }
        });
        System.out.println("After pool started : " + pool.getActiveThreadCount());
        System.out.println("Async Mode : " + pool.getAsyncMode());
        System.out.println("Parallelism level of pool :" + pool.getCommonPoolParallelism());
        System.out.println("Parallelism level of pool :" + pool.getParallelism());
//            System.out.println("Common Pool Size after  :" + pool.getPoolSize());
        System.out.println("Queued task count :" + pool.getQueuedTaskCount());
        System.out.println("Queued Submission  :" + pool.getQueuedSubmissionCount());
        System.out.println("Running Threads  :" + pool.getRunningThreadCount());
        System.out.println("Steal Count  :" + pool.getStealCount());

        pool.awaitTermination(1500, TimeUnit.MILLISECONDS);
    }
}
