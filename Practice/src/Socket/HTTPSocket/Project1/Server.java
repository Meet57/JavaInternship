package Socket.HTTPSocket.Project1;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException, URISyntaxException {
        int PORT = 8080;
        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true){
            Socket clientSocket = serverSocket.accept();
            System.err.println("Client connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String s;
            while((s = in.readLine())!=null){
                System.out.println(s);
                if(s.isEmpty()){
                    break;
                }
            }
            OutputStream clientOutput = clientSocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<h1>Hello World</h1>".getBytes());
            clientOutput.flush();
            System.err.println("Client connection closed!");
            in.close();
            clientOutput.close();

        }
    }
}
