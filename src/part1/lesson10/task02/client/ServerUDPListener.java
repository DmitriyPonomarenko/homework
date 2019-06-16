package part1.lesson10.task02.client;

import part1.lesson10.task02.messages.Message;
import part1.lesson10.task02.messages.SenderMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * слушатель широковещательной рассылки от сервера
 */
public class ServerUDPListener implements Runnable {

    private DatagramSocket socket;
    private byte[] buf = new byte[1024];

    ServerUDPListener(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        if (socket == null) return;
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                getMessage();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            socket.close();
        }
    }

    private void getMessage() throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        Message message;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
             ObjectInput ois = new ObjectInputStream(bais)) {
            message = (Message) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e.getMessage());
        }
        if (message instanceof SenderMessage) {
            System.out.print(((SenderMessage) message).getClientName() + ":");
        }
        System.out.println(message.getText());
    }
}
