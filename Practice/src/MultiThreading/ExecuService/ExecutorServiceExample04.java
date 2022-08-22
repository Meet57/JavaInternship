package MultiThreading.ExecuService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample04 {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(3);

        List<Callable<String>> callables = new ArrayList<>();

        callables.add(newCallable("Task 1",3));
        callables.add(newCallable("Task 2",2));
        callables.add(newCallable("Task 3",1));
        callables.add(newCallable("Task 4",2));
        callables.add(newCallable("Task 5",1));

        /*
        * InvokeAny run all the callables and whoever responds the faster
        * is taken in action
        * */

        try {
            String str = executorService.invokeAny(callables);
            System.out.println(str);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---------");

        try {
            List<Future<String>> results = executorService.invokeAll(callables);
            for (Future future : results) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
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
