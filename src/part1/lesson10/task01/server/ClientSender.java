package part1.lesson10.task01.server;

import part1.lesson10.task01.messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class ClientSender implements Runnable {

    private ServerChat serverChat;
    private Socket clientSocket;
    private ObjectOutputStream oos;
    private String clientName;
    private final Queue<Message> messages = new ConcurrentLinkedQueue<>();

    ClientSender(ServerChat chat, Socket socket, String name) throws IOException {
        serverChat = chat;
        clientSocket = socket;
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        clientName = name;
    }

    void sendMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messages.poll();
                if (message != null) {
                    oos.writeObject(message);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            } catch (Exception e) {
                serverChat.disconnectClient(clientSocket);
                return;
            }
        }
    }

    String getClientName() {
        return clientName;
    }

}
