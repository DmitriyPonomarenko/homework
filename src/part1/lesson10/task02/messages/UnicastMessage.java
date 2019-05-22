package part1.lesson10.task02.messages;

/**
 * личное сообщение
 */
public class UnicastMessage extends Message {

    private String addresseeName;

    public UnicastMessage(String text, String addresseeName) {
        super(text);
        this.addresseeName = addresseeName;
    }

    public String getAddresseeName() {
        return addresseeName;
    }
}
