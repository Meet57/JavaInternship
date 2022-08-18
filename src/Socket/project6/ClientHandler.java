package Socket.project6;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * If you use PrintWrtier then you can pass a parameter to the constructor regarding flushing the message
 * but in bufferwriter you need to manually type while message and then pass using flush method
 * */
public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static Map<String, ArrayList<ClientHandler>> groups = new HashMap<>();
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private String GroupName = null;

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
            if (socket != null) socket.close();
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeClientHandler() {
        broadcastMessage("[SERVER] " + clientUsername + " has left the chat!");
        clientHandlers.remove(this);
    }

    private void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.clientUsername.equals(clientUsername)) {
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

    private void privateMessage(String name, String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.clientUsername.equals(name)) {
                try {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }

    private void createGroup(String s) {
        groups.put(s, new ArrayList<>());
    }

    private String getGroupList() {
        StringBuilder sb = new StringBuilder("Groups are : ");

        for (String a : groups.keySet()) {
            sb.append(a + " ");
        }
        return sb.toString();
    }

    private void privateMessageGroup(String groupName, String message) {
        for (ClientHandler clientHandler : groups.get(groupName)) {
            if (!clientHandler.clientUsername.equals(clientUsername)) {
                try {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
    }

    private String getMembers() {
        StringBuilder sb = new StringBuilder("Total Members are : ");
        for (ClientHandler client : clientHandlers) {
            sb.append(client.clientUsername + "  ");
        }
        return sb.toString();
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                if (messageFromClient.charAt(0) == '/') {
                    switch (messageFromClient.split(" ")[0]) {
                        case "/all":
                            privateMessage(clientUsername, getMembers());
                            break;
                        case "/createGroup":
                            createGroup(messageFromClient.split(" ")[1]);
                            break;
                        case "/groups":
                            privateMessage(clientUsername, getGroupList());
                            break;
                        case "/join":
                            if (messageFromClient.split(" ").length != 2) {
                                privateMessage(clientUsername, "Wrong parameters");
                                break;
                            }
                            if (groups.containsKey(messageFromClient.split(" ")[1])) {
                                this.GroupName = messageFromClient.split(" ")[1];
                                privateMessageGroup(this.GroupName, "[GROUP]" + clientUsername + " Joined the group");
                                groups.get(this.GroupName).add(this);
                                privateMessage(clientUsername, "You Joined the Group");
                                break;
                            }
                            privateMessage(clientUsername, "Group does not exist");
                            break;
                        case "/leave":
                            if (this.GroupName == null) break;
                            groups.get(this.GroupName).remove(this);
                            this.GroupName = null;
                            break;
                        case "/msg":
                            privateMessage(messageFromClient.split(" ")[1], clientUsername + "[Private Message]-" + messageFromClient.split("-")[1]);
                            break;
                        case "/exit":
                            closeEverything(socket, bufferedReader, bufferedWriter);
                            break;
                        default:
                            privateMessage(clientUsername, "Wrong Command");
                    }
                } else {
                    if (this.GroupName == null) {
                        broadcastMessage(clientUsername + ": " + messageFromClient);
                    } else {
                        privateMessageGroup(this.GroupName, "[GROUP]" + clientUsername + ": " + messageFromClient);
                    }
                }
                if (messageFromClient.equals("/exit")) break;
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

}
