package MultiThreading;

public class ThreadExample03 {
    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Out");
        }
    }

    public static void main(String[] args) {
        Thread a = new Thread(new MyRunnable());
        a.setDaemon(true);
        a.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
