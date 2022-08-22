package Socket.UDP.project02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient2 {
    public static void main(String[] args) throws SocketException {
        DatagramPacket packet;
        DatagramSocket client = new DatagramSocket(8081);

        byte[] b = new byte[64];
        String data = "no data";

        try {
            while (true) {
                packet = new DatagramPacket(b, b.length);
                client.receive(packet);
                data = new String(packet.getData()).trim();
                System.out.println(data);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        } finally {
            client.close();
        }


    }
}
