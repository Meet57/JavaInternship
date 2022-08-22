package MultiThreading.ExecuService;

import java.util.concurrent.*;

/*
* Canceling a future will terminate the tasks and hence
* isDone turns to true,
* and it throws a Cancellation exception on calling get
* you can also check whether the thread task is cancelled or not by
* isCancelled
* */

public class ExecutorServiceExample06 {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(3);

        Future f = executorService.submit(newCallable("Meet",5));

        System.out.println(f.isDone());

        /*
        * cancel method return bool
        * if task has started executing then it won't be able to cancel
        * */
        boolean wasCancel = f.cancel(true);
        System.out.println(wasCancel);

        try {
            String str = f.get().toString();
            System.out.println(str);
        } catch (Exception e) {
            System.err.println(e);
        }

        System.out.println(f.isDone());
        System.out.println(f.isCancelled());

        executorService.shutdown();
    }

    private static Callable<String> newCallable(String s, int seconds) {
        return new Callable(){
            @Override
            public String call() throws Exception {
                Thread.sleep((long) seconds*1000);
                return Thread.currentThread().getName() + " : " + s;
            }
        };
    }
}
