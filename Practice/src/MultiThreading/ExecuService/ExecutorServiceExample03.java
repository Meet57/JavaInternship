package MultiThreading.ExecuService;

import java.util.concurrent.*;

public class ExecutorServiceExample03 {

    private static Callable myCallable(String msg){
        return new Callable() {
            @Override
            public String call() throws Exception {
                String asd = Thread.currentThread().getName() + " : " + msg;
                return asd;
            }
        };
    }

    public static void main(String[] args) {
        ExecutorService threadPoolExecutor =
                Executors.newFixedThreadPool(2);

        Future future = threadPoolExecutor.submit(myCallable("Meet"));

        System.out.println("Task Completed : "+future.isDone());

        try {
            String str = (String) future.get();
            System.out.println(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task Completed : "+future.isDone());

        threadPoolExecutor.shutdown();
    }
}
