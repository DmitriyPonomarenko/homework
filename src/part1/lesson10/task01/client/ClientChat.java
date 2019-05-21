package part1.lesson10.task01.client;

import part1.lesson10.task01.client.texts.TextMessage;
import part1.lesson10.task01.messages.ErrorMessage;
import part1.lesson10.task01.messages.Message;
import part1.lesson10.task01.messages.SenderMessage;
import part1.lesson10.task01.properties.Properties;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

    private Socket socket;
    private ObjectOutputStream oos;
    private Scanner scanner = new Scanner(System.in);

    public ClientChat() throws IOException {
        Properties properties = new Properties();
        socket = new Socket(properties.getHost(), properties.getPort());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void doWork() {
        try {
            ServerListener listener = new ServerListener(socket);
            //noinspection StatementWithEmptyBody
            while (!login(listener)) ;
            new Thread(listener).start();
            while (true) {
                Message message = new Message(scanner.nextLine());
                if (socket.isClosed()) break;
                oos.writeObject(message);
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

    private boolean login(ServerListener listener) throws IOException {
        oos.writeObject(new SenderMessage(getName()));
        Message message = listener.getMessage();
        return !(message instanceof ErrorMessage);
    }

    private String getName() {
        System.out.print(TextMessage.NEW_CLIENT);
        return scanner.nextLine();
    }

}
