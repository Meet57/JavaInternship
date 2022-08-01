package MultiThreading;
/*
* Stoppable thread
* If two threads are running: main and thread
* If main ends, thread will continue running till work is completed
* To stop it
* use thread.setDaemon(true) will stop the thread when main is ended
* */

public class ThreadExample02 {
    public static class StopabbleRunnable implements Runnable{
        private  boolean stopRunnable = false;

        public synchronized void requestStop(){
            this.stopRunnable = true;
        }

        public synchronized boolean isRequestStop(){
            return this.stopRunnable;
        }

        private void sleep(long m){
            try {
                Thread.sleep(m);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            System.out.println("StopabbleRunnable Running");
            while (!isRequestStop()){
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("StopabbleRunnable Stopped");
        }
    }

    public static void main(String[] args) {
        StopabbleRunnable sr = new StopabbleRunnable();
        Thread thread = new Thread(sr);
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Requesting Stop");
        sr.requestStop();
        System.out.println("stop requested");
    }
}
