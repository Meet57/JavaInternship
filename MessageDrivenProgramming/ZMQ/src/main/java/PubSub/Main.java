package PubSub;

/*
* Make sure your all clients are subscribed before you send the messages
* */

public class Main {
    public static void main(String[] args) {

        new Thread(new Client(1),"A").start();
        new Thread(new Client(2),"B").start();
        new Thread(new Server(),"SERVER").start();
    }
}
