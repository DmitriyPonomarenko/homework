package part1.lesson10.task02.server.connections;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * соединение с клиентом чата
 */
public class ClientConnection {

    private Socket socket;
    private ObjectOutputStream oos;
    private String clientName;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
