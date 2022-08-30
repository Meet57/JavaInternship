package Single;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client01 {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REQ);

        socket.connect("tcp://localhost:5555");

        String reply = "Hello from Client";
        socket.send(reply.getBytes());

        byte[] response = socket.recv();
        System.out.println("[GOT] "+ new String(response,ZMQ.CHARSET));

        context.close();
    }
}
