package Socket.TCP.project6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final String username;

    public Client(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner sc = new Scanner(System.in);
            String messageToSend;
            while (socket.isConnected()) {
                messageToSend = sc.nextLine();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if (messageToSend.equals("/exit")) break;
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        closeEverything(socket, bufferedReader, bufferedWriter);
    }

    public void listenForMessage() {
        String messageFromGroupChat;
        System.out.println("Listening Messages");
        while (socket.isConnected()) {
            try {
                messageFromGroupChat = bufferedReader.readLine();
                if (messageFromGroupChat == null) {
                    try {
                        System.err.println("Error Reconnecting...");
                        Thread.sleep(7000);
                        this.socket = new Socket("10.20.40.226", 1234);
                        if (socket.isConnected()) {
                            bufferedWriter.write(username);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        }
                    } catch (InterruptedException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                    continue;
                }
                System.out.println(messageFromGroupChat);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) socket.close();
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the username for the group chat: ");
        String username = sc.nextLine();

        try {
            Socket socket = new Socket("10.20.40.226", 1234);
            Client client = new Client(socket, username);
            Thread messageListener = new Thread(new Runnable() {
                @Override
                public void run() {
                    client.listenForMessage();
                }
            });
            messageListener.setDaemon(true);
            messageListener.start();
            client.sendMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}