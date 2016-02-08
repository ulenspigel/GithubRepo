package ua.dkovalov.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatServer {
    private static int PORT = 2305;
    private volatile List<ClientConnection> clients = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public ChatServer() {}

    public void start() throws InterruptedException {
        try (
            ServerSocket serverSocket = new ServerSocket(PORT);
        ){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO: refactor in a separate function and replace anonymous class with lambda
                    try {
                        while (true) {
                            clients.add(new ClientConnection(serverSocket.accept()));
                        }
                    } catch (IOException ioe) {
                        throw new RuntimeException(ioe);
                    }
                }
            }).start();

            //clients.add(new ClientConnection(serverSocket.accept()));
            //clients.add(new ClientConnection(serverSocket.accept()));

            while (true) {
                // TODO: delete condition after debugging
                if (clients.size() > 0) {
                    checkClientsMessages();
                    notifyClients();
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        // TODO: finally - free resources for each socket
    }

    private void notifyClients() {
        if (messages.size() == 0) {
            return;
        }
        // TODO: check if socket was disconnected
        for (ClientConnection client : clients) {
            for (String message : messages) {
                client.output.println(">> " + message);
            }
        }
        messages.clear();
    }

    // TODO: turn method into synchronized to avoid adding new clients during the check
    private void checkClientsMessages() {
        // TODO: check if socket was disconnected
        String message;
        for (ClientConnection client : clients) {
            try {
                //while ((message = client.input.readLine()) != null) {
                if (client.socket.getInputStream().available() > 0) {
                    messages.add(client.input.readLine());
                }
                //}
            } catch (IOException ioe) {
                // TODO: add errors handling
                throw new RuntimeException(ioe);
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        new ChatServer().start();
    }

    private static class ClientConnection {
        static long clientId = 0;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

        public ClientConnection(Socket socket) throws IOException {
            clientId++;
            this.socket = socket;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }
    }

    public class Message implements Serializable {
        private int id;
        private Date date;
        private String content;

        public Message(int id, Date date, String content) {
            this.id = id;
            this.date = date;
            this.content = content;
        }

        public Message() {
        }

        @Override
        public String toString() {
            return "Id=" + id + "\nDate=" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) + "\nContent=" + content;
        }
    }
}
