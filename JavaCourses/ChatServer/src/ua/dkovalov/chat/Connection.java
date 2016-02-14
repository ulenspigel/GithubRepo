package ua.dkovalov.chat;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection<E extends IMessage> {
    private String localConnectionID;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        localConnectionID = socket.getLocalSocketAddress().toString();
        output = new ObjectOutputStream(this.socket.getOutputStream());
        input = new ObjectInputStream(this.socket.getInputStream());
    }

    public boolean hasUnreadData() {
        try {
            return socket.getInputStream().available() > 0;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public E receiveMessage() {
        try {
            return (E) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(E message) {
        try {
            output.writeObject(message);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void sendMessages(List<E> messages) {
        for (E message : messages) {
            sendMessage(message);
        }
    }

    public String getLocalConnectionID() {
        return localConnectionID;
    }

    public boolean isDisconnected() {
        return !socket.isConnected() || socket.isClosed();
    }

    public void close() {
        try {
            output.close();
            input.close();
            socket.close();
        // may be disconnected previously
        } catch (IOException ioe) {}
    }
}
