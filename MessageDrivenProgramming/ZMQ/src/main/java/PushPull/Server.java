package PushPull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server implements Runnable {
    @Override
    public void run() {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.PUSH);
        socket.setHWM(1);
        System.out.println(socket.getRcvHWM()+" "+socket.getSndHWM());
        socket.bind("tcp://localhost:5555");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("NoBlock condition");
        for (int i = 1; i <= 10; i++) {
            String reply = String.valueOf(i);
            if (socket.send(reply.getBytes(),ZMQ.NOBLOCK))
            {
//                System.out.println(i);
            }
            else
            {
                System.err.println(i);
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Default Block condition");
        for (int i = 1; i <= 10; i++) {
            String reply = String.valueOf(i);
            if (socket.send(reply.getBytes()))
            {
//                System.out.println(i);
            }
            else
            {
                System.err.println(i);
            }
        }
        System.out.println("EXIT");
        socket.send("exit".getBytes());

        socket.close();
    }
}
