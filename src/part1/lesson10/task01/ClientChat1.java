package part1.lesson10.task01;

import part1.lesson10.task01.client.ClientChat;

import java.io.IOException;

/**
 * запуск клиента чата
 */
public class ClientChat1 {
    public static void main(String[] args) throws IOException {
        new ClientChat().doWork();
    }
}
