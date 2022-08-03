package MultiThreading.ExecuService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class test {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(100);

        List<Callable<String>> callables = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            callables.add(newCallable("Task "+i,2));
        }

        /*
         * InvokeAny run all the callables and whoever responds the faster
         * is taken in action
         * */

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
                String i = "m";
                for (int j = 0; j < 1000000; j++) {
                    i += "" + j;
                }
                return Thread.currentThread().getName() + " : " + s;
            }
        };
    }
}
