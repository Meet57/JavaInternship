import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/Calculator")
public class Calculator {
    @OnOpen
    public void handleOpen(Session session) {
        try {
            session.getBasicRemote().sendText("Welcome To Calculator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String handleMessage(String message) {
        int result = 0;
        try{
            if(message.split(" ")[0].equals("add")){
                result = Integer.parseInt(message.split(" ")[1]) + Integer.parseInt(message.split(" ")[2]);
            }
            if(message.split(" ")[0].equals("sub")){
                result = Integer.parseInt(message.split(" ")[1]) - Integer.parseInt(message.split(" ")[2]);
            }
        }catch (Exception e){
            return "<span style=\"color:red\">Wrong Input<span>";
        }
        return String.valueOf(result);
    }

    @OnClose
    public void handleClose() {
        System.out.println("Client is now disconnected....");
    }

    @OnError
    public void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
