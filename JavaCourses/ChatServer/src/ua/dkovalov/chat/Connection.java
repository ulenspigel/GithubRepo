package ua.dkovalov.chat;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connection {
    private String localConnectionID;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    /*private BufferedReader input;
    private PrintWriter output;*/

    public Connection(Socket socket) throws IOException {
        System.out.println("Inside Connection constructor");
        this.socket = socket;
        localConnectionID = socket.getLocalSocketAddress().toString();
        System.out.println("Socket assigned && local address is " + localConnectionID);
        InputStream inputStream = this.socket.getInputStream();
        System.out.println("Got input stream");
        input = new ObjectInputStream(inputStream);
        System.out.println("Input stream assigned");
        output = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Output stream assigned");
        /*input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);*/
    }

    public boolean hasUnreadData() {
        try {
            return socket.getInputStream().available() > 0;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public Message receiveMessage() {
        try {
            return (Message) input.readObject();
            //return input.readLine();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Message message) {
        try {
            output.writeObject(message);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        //output.println(message);
    }

    public void sendMessages(List<Message> messages) {
        for (Message message : messages) {
            sendMessage(message);
        }
    }

    public String getLocalConnectionID() {
        return localConnectionID;
    }
}
