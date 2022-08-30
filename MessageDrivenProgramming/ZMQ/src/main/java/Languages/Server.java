package Languages;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REQ);

        socket.bind("tcp://localhost:5555");

        Thread.sleep(1000);
        String reply = "Hello from Java";
        socket.send(reply.getBytes());

        System.out.println("[GOT] " + new String(socket.recv()));

        context.close();
    }
}
