package MultiThreading.Project;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/*
* The fork() method submits a task to a pool, but it doesn't trigger its execution.
* pool.invoke(task) returns the result of the task
* pool.execute(task) starts the thread in the back and continue the
*           main thread and to hold the result we call join
*
* */

class Task extends RecursiveTask {
    private long st, ed, sum;

    Task(long st, long ed) {
        this.st = st;
        this.ed = ed;
        this.sum = 0;
    }

    @Override
    protected Long compute() {
        long mid = ed - st;
        if (mid <= 1000) {
//            System.out.println("Adding from " + st + " to " + ed);
            for (long i = st + 1; i <= ed; i++) {
                sum += i;
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " : " + st + " - " + ed);
            mid = (st + ed) / 2;
            System.out.println(mid + " = " + st + " : " + ed);
            Task t1 = new Task(st, mid);
            Task t2 = new Task(mid, ed);
            invokeAll(t1, t2);
            sum = (long) t1.join() + (long) t2.join();

//            ForkJoinPool f1 = ForkJoinPool.commonPool();
//            mid = (st + ed) / 2;
//            Task t1 = new Task(st, mid);
//            Task t2 = new Task(mid, ed);
//            f1.invoke(t1.fork());
//            f1.invoke(t2.fork());
//
//            long a, b;
//            a = (long) t2.join();
//            b = (long) t1.join();
//
//            sum = a + b;
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        Task tk = new Task(0, 999999);
        long result = (long) pool.invoke(tk);
        System.out.println(result);

        pool.execute(tk);
        result = (long) tk.join();

        System.out.println(result);

//        result = pool.submit(tk);
//        System.out.println(result);
    }
}
