package Socket.UDP.project01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket(8080);
        byte[] s = new byte[256];

        DatagramPacket packet = new DatagramPacket(s,s.length);
        client.receive(packet);

        String data = new String(packet.getData()).trim();
        System.out.println(data);
    }
}
