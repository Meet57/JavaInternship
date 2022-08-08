package MultiThreading.forkjoinpoolpackage;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Example02 extends RecursiveTask<Integer> {
    private static final int var = 5;
    private final int[] value;
    private final int st;
    private final int ed;

    //parameterized constructor
    public Example02(int[] value, int st, int ed) {
        this.value = value;
        this.st = st;
        this.ed = ed;
    }

    public Example02(int[] value) {
        this(value, 0, value.length);
    }

    @Override
    protected Integer compute() {
        final int length = ed - st;
        if (length < var) {
            return computeDirectly();
        }
        final int split = length / 2;
        //new class object
        ForkJoinPool pl = new ForkJoinPool();
        Example02 left = new Example02(value, st, st + split);
        pl.invoke(left.fork());
        Example02 right = new Example02(value, st + split, ed) ;
        return Math.max(right.compute(), left.join());
    }

    private Integer computeDirectly() {
        System.out.println(Thread.currentThread().getName() + " computing: " + st + " to " + ed);
        return Arrays.stream(value).max().getAsInt();
    }

    public static void main(String[] args) throws InterruptedException {
        // create a random data set
        final int[] value = new int[10];
        final Random rand = new Random();
        for (int i = 0; i < value.length; i++) {
            value[i] = rand.nextInt(1000);
        }
        // submit the task to the pool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Example02 finder = new Example02(value);
        System.out.println(pool.invoke(finder));
        //  pool.invokeAll(t);
        pool.invoke(finder);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });

        pool.awaitTermination(2, TimeUnit.MICROSECONDS);
        System.out.println("Main ended");
    }
}
