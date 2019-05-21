package part1.lesson10.task01.server;

import part1.lesson10.task01.messages.ErrorMessage;
import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.messages.SenderMessage;
import part1.lesson10.task01.server.connections.ClientConnection;
import part1.lesson10.task01.server.exceptions.DuplicateNameException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
                    serverChat.sendMessage(new SenderMessage(clientConnection.getName(), message.getText()));
                }
            }
        } catch (Exception e) {
            serverChat.disconnectClient(clientConnection);
        }
    }
}
