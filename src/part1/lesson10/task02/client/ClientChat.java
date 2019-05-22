package part1.lesson10.task02.client;

import part1.lesson10.task02.client.texts.TextMessage;
import part1.lesson10.task02.messages.ErrorMessage;
import part1.lesson10.task02.messages.Message;
import part1.lesson10.task02.messages.SenderMessage;
import part1.lesson10.task02.messages.UnicastMessage;
import part1.lesson10.task02.properties.Properties;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Реализует функционал клиента чата
 */
public class ClientChat {

    private Socket socket;
    private ObjectOutputStream oos;
    private Scanner scanner = new Scanner(System.in);

    /**
     * установка соединения с сервером
     *
     * @throws IOException нет соединения
     */
    public ClientChat() throws IOException {
        socket = new Socket(Properties.getHost(), Properties.getPort());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    /**
     * цикл отправки сообщений на сервер
     */
    public void doWork() {
        try {
            ServerListener listener = new ServerListener(socket);
            //noinspection StatementWithEmptyBody
            while (!login(listener)) ;
            new Thread(listener).start();
            String line;
            while (!(line = scanner.nextLine()).equals(TextMessage.QUIT_COMMAND)) {
                int pos = line.indexOf(':');
                boolean isUnicast = pos > -1;
                Message message;
                if (isUnicast) {
                    message = new UnicastMessage(line.substring(pos + 1), line.substring(0, pos));
                } else {
                    message = new Message(line);
                }
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

    /**
     * регистрация пользователя на сервере
     *
     * @param listener слушатель входящего потока
     * @return результат регистрации
     * @throws IOException если регистрация не прошла
     */
    private boolean login(ServerListener listener) throws IOException {
        oos.writeObject(new SenderMessage(getName()));
        Message message = listener.getMessage();
        return !(message instanceof ErrorMessage);
    }

    /**
     * получение имени пользователя
     *
     * @return имя пользователя
     */
    private String getName() {
        System.out.print(TextMessage.NEW_CLIENT);
        return scanner.nextLine();
    }

}
