package part1.lesson10.task02.server;

import part1.lesson10.task02.messages.Message;
import part1.lesson10.task02.properties.Properties;
import part1.lesson10.task02.server.connections.ClientConnection;

import java.io.ObjectOutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * поток хранит и отправляет очередь исходящих обращений
 */
class ClientSender implements Runnable {

    private ServerChat serverChat;
    private ClientConnection clientConnection;
    private final Queue<Message> messages = new ConcurrentLinkedQueue<>();

    ClientSender(ServerChat chat, ClientConnection connection) {
        serverChat = chat;
        clientConnection = connection;
    }

    /**
     * добавляет сообщение в очередь
     *
     * @param message добавляемое сообщение
     */
    void sendMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = clientConnection.getOos();

            //noinspection InfiniteLoopStatement
            while (true) {
                Message message;
                while ((message = messages.poll()) != null) {
                    oos.writeObject(message);
                }
                try {
                    Thread.sleep(Properties.getDelayInMillis());
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        } catch (Exception e) {
            messages.clear();
            serverChat.disconnectClient(clientConnection);
        }
    }

}
