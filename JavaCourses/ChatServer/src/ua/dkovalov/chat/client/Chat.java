package ua.dkovalov.chat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.dkovalov.chat.Connection;
import ua.dkovalov.chat.IMessage;
import ua.dkovalov.chat.Message;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// TODO: sending messages on enter
// TODO: decrease the height of the input area
// TODO: handling form close event
// TODO: modal window with client name input

public class Chat extends Application {
    private static final int SERVER_PORT = 2305;
    private static final String SERVER_ADDRESS = "127.0.0.1";
    public Connection<Message> connection;

    public Chat() {
    }

    public void connect() {
        try {
            connection = new Connection<>(new Socket(SERVER_ADDRESS, SERVER_PORT));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public void disconnect() {
        connection.close();
    }

    public void sendMessages(String message) {
        connection.sendMessage(new Message(connection.getLocalConnectionID(), Calendar.getInstance(), message));
    }

    public List<IMessage> receiveMessages() {
        List<IMessage> messages = new ArrayList<>();
        while (connection.hasUnreadData()) {
            messages.add(connection.receiveMessage());
        }
        return messages;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ChatForm.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        Platform.exit();
    }
}
