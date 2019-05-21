package part1.lesson10.task01.server;

import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.server.connections.ClientConnection;

import java.io.ObjectOutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class ClientSender implements Runnable {

    private ServerChat serverChat;
    private ClientConnection clientConnection;
    private final Queue<Message> messages = new ConcurrentLinkedQueue<>();

    ClientSender(ServerChat chat, ClientConnection connection) {
        serverChat = chat;
        clientConnection = connection;
    }

    void sendMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = clientConnection.getOos();

            //noinspection InfiniteLoopStatement
            while (true) {
                Message message = messages.poll();
                if (message != null) {
                    oos.writeObject(message);
                }
                try {
                    Thread.sleep(1000);
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
