package part1.lesson10.task02.client;

import part1.lesson10.task02.messages.Message;
import part1.lesson10.task02.messages.SenderMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * слушатель входящего потока от сервера
 */
public class ServerListener implements Runnable {

    private Socket socket;
    private ObjectInputStream ois;

    ServerListener(Socket socket) throws IOException {
        this.socket = socket;
        ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
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

    /**
     * обработчик входящих сообщений
     *
     * @return входящее сообщение
     * @throws IOException ошибка ввода-вывода
     */
    public Message getMessage() throws IOException {
        Message message;
        try {
            message = (Message) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e.getMessage());
        }
        if (message instanceof SenderMessage) {
            System.out.print(((SenderMessage) message).getClientName() + ":");
        }
        System.out.println(message.getText());
        return message;
    }
}
