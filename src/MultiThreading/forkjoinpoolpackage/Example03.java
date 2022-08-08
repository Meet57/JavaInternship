package MultiThreading.forkjoinpoolpackage;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
* Invoke returns a object whereas Execute doesnt
* */
public class Example03 extends RecursiveTask<Integer> {
    private static final int var = 5;
    private final int[] value;
    private final int st;
    private final int ed;

    //parameterized constructor
     Example03(int[] value, int st, int ed) {
        this.value = value;
        this.st = st;
        this.ed = ed;
    }
    Example03(int[] value) {
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
        ForkJoinPool pl = new  ForkJoinPool();
        Example03 left = new Example03(value, st, st + split);
        left.fork();
        Example03 right = new Example03(value, st + split, ed);
        return Math.max(right.compute(), left.join());
    }
    private Integer computeDirectly() {
        System.out.println(Thread.currentThread().getName() + " computing: " + st + " to " + ed);
        int max = Integer.MIN_VALUE;
        for (int i = st; i < ed; i++) {
            if (value[i] > max) {
                max = value[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        // create a random data set
        final int[] value = new int[10];
        final Random rand = new Random();
        for (int i = 0; i < value.length; i++) {
            value[i] = rand.nextInt(100);
        }
        // submit the task to the poool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Example03 finder = new Example03(value);
        System.out.println(pool.invoke(finder));
        System.out.println("-=---");
        pool.execute(finder);

    }
}
