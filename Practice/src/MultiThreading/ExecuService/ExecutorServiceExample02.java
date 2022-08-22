package MultiThreading.ExecuService;

import java.util.concurrent.*;

public class ExecutorServiceExample02 {

    public static void main(String[] args) {
        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 3000;
//        Terminate the extra threads from the pool
//        If thread doesn't get task for 3000 milli then it is terminated

        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(128)
                        /*
                         * The tasks are stored in this queue and each
                         * Thread pick the task and works
                         * */
                );

//        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
//        threadPoolExecutor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        /*
         * Both are same
         * */
        Future future = threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println("Task Completed : "+future.isDone());

        /*
        * future.get waits for the thread to complete that task
        * */
        try {
            System.out.println("Waiting to let it complete");
            future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task Completed : "+future.isDone());

        threadPoolExecutor.shutdown();
    }
}
