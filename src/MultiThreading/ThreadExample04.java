package MultiThreading;

/*
 * Shared and Separate objects of runnable
 * If a variable is mentioned volatile in the class or runnable
 * It skips the caching layer for members and is directly written in Main memory
 *
 * WRITE
 * Sequence of the above code can shuffle above but remains above
 * when a volatile member is used
 * But cant go below volatile member
 * */

public class ThreadExample04 {
    public static class MyRunnable implements Runnable {
        private volatile int i = 0;

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                synchronized (this) {
                    this.i++;
                }
            }
            System.out.println(Thread.currentThread().getName() + " : " + this.i);
        }
    }

    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable();
        MyRunnable r2 = new MyRunnable();
        MyRunnable r3 = new MyRunnable();

        Thread t1 = new Thread(r1, "T1");
        Thread t2 = new Thread(r2, "T2");

        t1.start();
        t2.start();

        Thread t3 = new Thread(r3, "T3");
        Thread t4 = new Thread(r3, "T4");

        t3.start();
        t4.start();
    }
}
