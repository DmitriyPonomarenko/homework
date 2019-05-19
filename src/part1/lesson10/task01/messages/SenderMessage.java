package part1.lesson10.task01.messages;

public class SenderMessage extends Message {

    private String clientName;

    public SenderMessage(String clientName) {
        super(null);
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

}
