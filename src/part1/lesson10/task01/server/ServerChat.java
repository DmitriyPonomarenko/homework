package part1.lesson10.task01.server;

import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.messages.SenderMessage;
import part1.lesson10.task01.properties.Properties;
import part1.lesson10.task01.server.connections.ClientConnection;
import part1.lesson10.task01.server.exceptions.DuplicateNameException;
import part1.lesson10.task01.server.texts.TextMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Реализует функционал сервера чата
 */
public class ServerChat {

    private final ConcurrentMap<String, ClientSender> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        new ServerChat().doWork();
    }

    /**
     * принимает входящие соединения и создает для каждого поток-слушатель
     *
     * @throws IOException если не удалось зарегистрировать порт
     */
    private void doWork() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Properties.getPort());
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(clientSocket);
                new Thread(new ClientListener(this, clientConnection)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * регистрация нового клиента
     *
     * @param clientConnection клиентское соединение
     * @param clientName       имя клиента
     * @throws DuplicateNameException если клиент с таким именем уже есть
     */
    void connectClient(ClientConnection clientConnection, String clientName) throws DuplicateNameException {
        ClientSender clientSender;
        synchronized (clients) {
            clientSender = clients.get(clientName);
            if (clientSender != null) {
                throw new DuplicateNameException(TextMessage.DUPLICATE_NAME);
            }
            clientConnection.setClientName(clientName);
            clientSender = new ClientSender(this, clientConnection);
            clients.put(clientName, clientSender);
        }
        new Thread(clientSender).start();
        sendMessage(new Message(clientName + TextMessage.NEW_CLIENT));
    }

    /**
     * разрывает соединение с клиентом
     *
     * @param clientConnection клиентское соединение
     */
    void disconnectClient(ClientConnection clientConnection) {
        String clientName = clientConnection.getClientName();
        if (clientName != null) {
            ClientSender clientSender = clients.remove(clientName);
            if (clientSender != null) {
                sendMessage(new Message(clientName + TextMessage.CLIENT_EXIT));
            }
        }
        try {
            clientConnection.getSocket().close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * broadcast рассылка
     *
     * @param message сообщение
     */
    void sendMessage(Message message) {
        for (Map.Entry<String, ClientSender> clientEntry : clients.entrySet()) {
            if (message instanceof SenderMessage) {
                if (clientEntry.getKey().equals(((SenderMessage) (message)).getClientName())) {
                    continue;
                }
            }
            clientEntry.getValue().sendMessage(message);
        }
    }
}
