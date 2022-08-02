package MultiThreading.ExecuService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Interface ExecutorService
*       Class ThreadPoolExecutor
*               As fast as it can
*       Class ScheduleThreadPoolExecutor
*               Schedule tasks for future
* */

public class ExecutorServiceExample {
    public static Runnable myRunnable(String msg){
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+" : "+msg);
            }
        };
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        es.execute(myRunnable("Task 1"));
        es.execute(myRunnable("Task 2"));
        es.execute(myRunnable("Task 3"));
        es.execute(myRunnable("Task 4"));

        es.shutdown();
    }
}
