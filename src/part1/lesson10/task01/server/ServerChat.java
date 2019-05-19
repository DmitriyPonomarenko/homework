package part1.lesson10.task01.server;

import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.properties.Ports;
import part1.lesson10.task01.server.exceptions.DuplicateNameException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServerChat {

    private final ConcurrentMap<Socket, ClientSender> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        new ServerChat().doWork();
    }

    private void doWork() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Ports.getPort());
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientListener(this, clientSocket)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void connectClient(Socket socket, String name) throws IOException, DuplicateNameException {
        ClientSender clientSender = clients.get(socket);
        if (clientSender != null) {
            clientSender.sendMessage(new Message(ServerMessage.ALREADY_CONNECTED));
            return;
        }
        synchronized (clients) {
            for (Map.Entry<Socket, ClientSender> sender : clients.entrySet()) {
                if (sender.getValue().getClientName().equals(name)) {
                    throw new DuplicateNameException(ServerMessage.DUPLICATE_NAME);
                }
            }
            clientSender = new ClientSender(this, socket, name);
            clients.put(socket, clientSender);
        }
        new Thread(clientSender).start();
        sendMessage(new Message(name + ServerMessage.NEW_CLIENT));
    }

    void disconnectClient(Socket socket) {
        String clientName;
        synchronized (clients) {
            ClientSender clientSender = clients.get(socket);
            if (clientSender == null) {
                return;
            }
            clientName = clientSender.getClientName();
            clients.remove(socket);
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sendMessage(new Message(clientName + ServerMessage.CLIENT_EXIT));
    }

    void sendMessage(Message message) {
        for (Map.Entry<Socket, ClientSender> sender : clients.entrySet()) {
            sender.getValue().sendMessage(message);
        }
    }
}
