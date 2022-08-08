package MultiThreading.forkjoinpoolpackage;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/*
 * Here in this example we are summing the array
 * if the array consist of more than 10 numbers it splits and execute the same
 * */

public class Example01 {
    public static void main(final String[] arguments) throws InterruptedException {
        int proc = Runtime.getRuntime().availableProcessors();
        System.out.println("numbers of core available in your processor:" + proc);
        int[] n = {20, 23, 5, 6, 7, 8, 23, 12, 56, 1, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1000, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1000, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1, 20, 23, 5, 6, 7, 8, 23, 12, 56, 1000};
        ForkJoinPool Pool = new ForkJoinPool(proc*2);
        System.out.println(n.length);
        Testl1 t = new Testl1(n, 0, n.length);
        Integer r = Pool.invoke(t);
        Pool.shutdown();
        Pool.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Pool.invoke :" + r);
    }
}


class Testl1 extends RecursiveTask<Integer> {
    int st;
    int end;
    int[] arr;

    Testl1(int[] arr, int st, int end) {
        this.arr = arr;
        this.st = st;
        this.end = end;
    }

    protected Integer compute() {
        if (end - st <= 10) {
            int sum = 0;
            for (int i = st; i < end; ++i)
                sum += arr[i];
            return sum;
        } else {
            System.out.println(Thread.currentThread().getName());
            int mid = st + (end - st) / 2;
            Testl1 t1 = new Testl1(arr, st, mid);
            Testl1 t2 = new Testl1(arr, mid, end);
            invokeAll(t1, t2);
            return (t1.join() + t2.join());
        }
    }
}