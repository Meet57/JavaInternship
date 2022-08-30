package HIghWaterMark;

/*
 * Make sure your all clients are subscribed before you send the messages
 * */

public class Main {
    public static void main(String[] args) {
        new Thread(new Client(), "WORKER").start();
        new Thread(new Server(), "SERVER").start();
    }
}
