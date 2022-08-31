package Meet.action;

import Meet.model.MessageStore;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class HelloWorldAction{
    private MessageStore messageStore;

    public String execute()
    {
        messageStore = new MessageStore();

        return SUCCESS;
    }


    public MessageStore getMessageStore() {
        return messageStore;
    }
}
