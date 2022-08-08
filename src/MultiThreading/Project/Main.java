package MultiThreading.Project;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

class Task extends RecursiveTask {
    private int st, ed, sum;

    Task(int st, int ed) {
        this.st = st;
        this.ed = ed;
        this.sum = 0;
    }

    @Override
    protected Integer compute() {
        int mid = ed - st;
        if (mid <= 1000) {
            System.out.println("Adding from " + st + " to " + ed);
            for (int i = st+1; i <= ed; i++) {
                sum += i;
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " : " + st + " - " + ed);
            mid = (st + ed) / 2;
//            System.out.println(mid + " = " + st + " : " + ed);
            Task t1 = new Task(st, mid);
            Task t2 = new Task(mid, ed);
            invokeAll(t1, t2);
            sum = (int) t1.join() + (int) t2.join();
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Task tk = new Task(0, 6000);
        int result = (int) pool.invoke(tk);
        System.out.println(result);
    }
}
