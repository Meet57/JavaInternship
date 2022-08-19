package Socket.UDP.project02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket();
        DatagramPacket packet;
        try {
            while (true) {
                Thread.sleep(1000);
                String time = String.valueOf(new Date().getTime());
                byte[] b = time.getBytes();
                packet = new DatagramPacket(
                        b,
                        b.length,
                        InetAddress.getByName("10.20.40.226"),
                        8080
                );
                server.send(packet);
                packet = new DatagramPacket(
                        b,
                        b.length,
                        InetAddress.getByName("10.20.40.226"),
                        8081
                );
                server.send(packet);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
