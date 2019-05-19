package part1.lesson10.task01.server;

import part1.lesson10.task01.messages.ErrorMessage;
import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.messages.SenderMessage;
import part1.lesson10.task01.server.exceptions.DuplicateNameException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientListener implements Runnable {

    private ServerChat serverChat;
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    ClientListener(ServerChat chat, Socket socket) throws IOException {
        serverChat = chat;
        clientSocket = socket;
        ois = new ObjectInputStream(clientSocket.getInputStream());
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object object = ois.readObject();
                if (object instanceof SenderMessage) {
                    try {
                        serverChat.connectClient(clientSocket, ((SenderMessage) object).getClientName());
                    } catch (DuplicateNameException e) {
                        oos.writeObject(new ErrorMessage(e.getMessage()));
                    }
                } else if (object instanceof Message) {
                    serverChat.sendMessage((Message) object);
                }
            } catch (Exception e) {
                serverChat.disconnectClient(clientSocket);
                return;
            }
        }
    }
}
