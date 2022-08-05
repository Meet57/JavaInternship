package MultiThreading.ExecuService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample05 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(1);

        List<Callable<String>> callables = new ArrayList<>();

        callables.add(newCallable("Task 1",1));
        callables.add(newCallable("Task 2",2));
        callables.add(newCallable("Task 3",1));
        callables.add(newCallable("Task 4",2));
        callables.add(newCallable("Task 5",1));

//        executorService.submit(callables);
        List<Future<String>> results = executorService.invokeAll(callables);
        List<Runnable> runnable = executorService.shutdownNow();
        for (Runnable runner : runnable) {
            System.out.println(runner);
        }
        executorService.shutdown();
    }

    private static Callable<String> newCallable(String s,int seconds) {
        return new Callable(){
            @Override
            public String call() throws Exception {
                Thread.sleep((long) seconds*1000);
                return Thread.currentThread().getName() + " : " + s;
            }
        };
    }
}
