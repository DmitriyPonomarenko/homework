package part1.lesson10.task01.messages;

import java.io.Serializable;

/**
 * сообщение для обмена данными
 */
public class Message implements Serializable {

    private String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
