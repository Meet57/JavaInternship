package Socket.UDP.project01;

import java.io.IOException;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        String s = "Hello world";
        byte[] b = s.getBytes();
        DatagramSocket server = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(b,b.length,InetAddress.getByName("10.20.40.226"),8080);
        server.send(packet);
        server.close();
    }
}
