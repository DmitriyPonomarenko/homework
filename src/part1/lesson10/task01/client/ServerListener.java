package part1.lesson10.task01.client;

import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.messages.SenderMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerListener implements Runnable {

    private Socket socket;
    private ObjectInputStream ois;

    ServerListener(Socket socket) throws IOException {
        this.socket = socket;
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                getMessage();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Message getMessage() throws IOException {
        Message message;
        try {
            message = (Message) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e.getMessage());
        }
        if (message instanceof SenderMessage) {
            System.out.print(((SenderMessage)message).getClientName() + ":");
        }
        System.out.println(message.getText());
        return message;
    }
}
