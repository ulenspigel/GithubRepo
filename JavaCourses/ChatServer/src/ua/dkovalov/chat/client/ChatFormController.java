package ua.dkovalov.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import ua.dkovalov.chat.IMessage;

import java.util.List;

public class ChatFormController {
    private ChatClient chatClient;
    @FXML private TextArea messageInput;
    @FXML private ListView<String> messagesOutput;

    public ChatFormController() {
        chatClient = new ChatClient();
        chatClient.connect();
    }

    // TODO: replace with mechanism that waits for messages with wait() method and once they were received, displays them
    public void initialize() {
        messagesOutput.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new MessageCell();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final List<IMessage> messages = chatClient.receiveMessages();
                    if (messages.size() > 0) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (IMessage message : messages) {
                                    messagesOutput.getItems().add(message.toString());
                                }
                            }
                        });
                    }
                }
            }
        }).start();
    }

    @FXML
    protected void handleSendButtonClick(ActionEvent event) {
        StringBuilder messageText = new StringBuilder();
        for (CharSequence messagePart : messageInput.getParagraphs()) {
            messageText.append(messagePart + "\n");
        }
        if (messageText.length() > 0) {
            chatClient.sendMessages(messageText.toString());
            messageInput.clear();
        }
    }

    private static class MessageCell extends ListCell<String> {
        @Override
        public void updateItem(String messageText, boolean isEmpty)
        {
            super.updateItem(messageText, isEmpty);
            if(messageText != null && !isEmpty)
            {
                Data data = new Data();
                data.setInfo(string);
                setGraphic(data.getBox());
            }
        }
    }
}
