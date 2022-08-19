package Socket.TCP.project5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/*
* If you use PrintWrtier then you can pass a parameter to the constructor regarding flushing the message
* but in bufferwriter you need to manually type while message and then pass using flush method
* */
public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = bufferedReader.readLine();
            System.out.println(clientUsername);
            clientHandlers.add(this);
            broadcastMessage("[Server] " + clientUsername + " has entered the chat");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            removeClientHandler();
            if(socket!=null) socket.close();
            if(bufferedReader!=null) bufferedReader.close();
            if(bufferedWriter!=null) bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeClientHandler(){
        broadcastMessage("[SERVER] "+clientUsername+" has left the chat!");
        clientHandlers.remove(this);
    }

    private void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            if(!clientHandler.clientUsername.equals(clientUsername)){
                try {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if(messageFromClient.equals("EXIT")){
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
                broadcastMessage(clientUsername+": "+messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
}
