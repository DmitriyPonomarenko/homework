package part1.lesson10.task02.server;

import part1.lesson10.task02.messages.ErrorMessage;
import part1.lesson10.task02.messages.Message;
import part1.lesson10.task02.messages.SenderMessage;
import part1.lesson10.task02.messages.UnicastMessage;
import part1.lesson10.task02.server.connections.ClientConnection;
import part1.lesson10.task02.server.exceptions.DuplicateNameException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * слушатель входящего потока от клиента
 */
class ClientListener implements Runnable {

    private ServerChat serverChat;
    private ClientConnection clientConnection;

    ClientListener(ServerChat chat, ClientConnection connection) {
        serverChat = chat;
        clientConnection = connection;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientConnection.getSocket().getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientConnection.getSocket().getInputStream());
            clientConnection.setOos(oos);

            //noinspection InfiniteLoopStatement
            while (true) {
                Message message = (Message) ois.readObject();
                if (message instanceof SenderMessage) {
                    try {
                        serverChat.connectClient(clientConnection, ((SenderMessage) message).getClientName());
                    } catch (DuplicateNameException e) {
                        oos.writeObject(new ErrorMessage(e.getMessage()));
                    }
                } else {
                    String clientName = clientConnection.getClientName();
                    if (clientName != null) {
                        SenderMessage senderMessage = new SenderMessage(clientName, message.getText());
                        if (message instanceof UnicastMessage) {
                            serverChat.sendUnicastMessage(senderMessage, ((UnicastMessage) message).getAddresseeName());
                        } else {
                            serverChat.sendMessage(senderMessage);
                        }
                    }
                }
            }
        } catch (Exception e) {
            serverChat.disconnectClient(clientConnection);
        }
    }
}
