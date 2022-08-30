package PubSub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.concurrent.CountDownLatch;

public class Server implements Runnable {

    @Override
    public void run() {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.PUB);

        socket.bind("tcp://localhost:5555");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 10; i++) {
            String reply = String.valueOf(i);
            socket.send(reply.getBytes());
        }
        socket.send("exit".getBytes());

        context.close();
    }
}
