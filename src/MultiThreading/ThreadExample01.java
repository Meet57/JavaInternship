package MultiThreading;
/*
* Ways to create Threads
* */

public class ThreadExample01 {

    //    Method 2
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("class Thread");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("class Thread Finished");
        }
    }

    //    Method 3
    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("runnable Thread");
        }
    }

    public static void main(String[] args) {
//        Method 1 to init a thread
        Thread thread = new Thread();
        thread.start();

//        Method 2 - Class Approach
        MyThread myThread = new MyThread();
        myThread.start();

//        Method 3 - Runnable
        Thread myRunnable = new Thread( new MyRunnable() );
        myRunnable.start();

        int a  = 10;
//        Method 4 - Runnable as anonymous
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Runnable Thread");
                System.out.println(a);
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();

//        Method 5 - Runnable with lambda expression
        runnable = () -> {
            System.out.println("Lambda anonymous runnable thread: " + Thread.currentThread().getName());
        };

        Thread thread2 = new Thread(runnable, "Meet Patel");
        thread2.start();
    }
}
