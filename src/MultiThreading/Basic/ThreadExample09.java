package MultiThreading.Basic;

/*
 * Race condition
 * Here both threads access the variable at a single value and increment and
 * updated it, so if counter from 1 to 3, it is going only 1 to 2
 * */

class Counter {
    private long count = 0;

    public long incAndGet() {
//      It is called critical condition
        synchronized (this){
            this.count++;
            return this.count;
        }
    }

    public long getCount() {
        return this.count;
    }
}

public class ThreadExample09 {
    private static Runnable getRunnable(Counter counter, String message) {
        return () -> {
            for (int i = 0; i < 1000000; i++) {
                counter.incAndGet();
            }
            System.out.println(message + ":" + counter.getCount());
        };
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(getRunnable(counter, "Thread 1"));
        Thread t2 = new Thread(getRunnable(counter, "Thread 2"));

        t1.start();
        t2.start();
    }
}
