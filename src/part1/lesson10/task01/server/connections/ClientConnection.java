package part1.lesson10.task01.server.connections;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {

    private Socket socket;
    private ObjectOutputStream oos;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
