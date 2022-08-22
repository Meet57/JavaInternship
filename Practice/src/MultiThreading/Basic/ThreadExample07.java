package MultiThreading.Basic;

/*
 * ThreadLocal class is used to create thread local
 * variables which can only be read and written by the same thread
 * */

public class ThreadExample07 {
    public static void main(String[] args) {
//        ThreadLocal<String> tl = new ThreadLocal<>();
//        ThreadLocal<String> tl = ThreadLocal.withInitial(() -> "Meet");

//        ThreadLocal <String> tl =
//                new ThreadLocal<String>(){
//                    @Override
//                    protected String initialValue() {
//                        return "Meet";
//                    }
//                };


        /*
         *   IF you initialise the thread with any value every thread will get that value
         * initially at the thread start
         * */

        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("Main Thread");

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 : "+tl.get());
            tl.set("Thread 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 1 : "+tl.get());
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 : "+tl.get());
            tl.set("Thread 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 : "+tl.get());
        });

        t1.start();
        t2.start();

//      main is itself runs as a thread ,so we can give a local variable as well
        System.out.println(tl.get());
    }
}
