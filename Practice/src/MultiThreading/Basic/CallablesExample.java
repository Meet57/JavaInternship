package MultiThreading.Basic;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class MeetCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "Hi";
    }
}

public class CallablesExample {
    public static void main(String[] args) {
        Callable c1 = new MeetCallable();

        FutureTask<String> f1 = new FutureTask<String>(c1);
        Thread t1 = new Thread(f1);

        System.out.println(Thread.currentThread().getName());
        t1.start();

        try {
            System.out.println("Output : " + f1.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName());
    }
}
