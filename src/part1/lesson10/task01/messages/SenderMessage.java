package part1.lesson10.task01.messages;

public class SenderMessage extends Message {

    private String clientName;

    public SenderMessage(String clientName, String text) {
        super(text);
        this.clientName = clientName;
    }

    public SenderMessage(String clientName) {
        this(clientName, null);
    }

    public String getClientName() {
        return clientName;
    }

}
