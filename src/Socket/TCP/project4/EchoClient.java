package Socket.TCP.project4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        Socket soc = new Socket("10.20.40.226", 8080);

        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

        Scanner sc = new Scanner(System.in);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String str;
                while (true){
                    try {
                        str = in.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Server: "+str);
                }
            }
        }, "Reader").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = "";
                while (true){
//                    System.out.print("Client: ");
                    str = sc.nextLine();
                    out.println(str);
                }
            }
        },"Writer").start();
    }
}
