package ua.dkovalov.message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Message implements Serializable {
    private int id;
    private Date date;
    private String content;

    public Message(int id, Date date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

    public Message() { }

    @Override
    public String toString() {
        return "Id=" + id + "\nDate=" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) + "\nContent=" + content;
    }

    public boolean equals(Message message) {
        return this.id == message.getId() && this.date.equals(message.getDate()) && this.content.equals(message.getContent());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String... args) {
        /*
        //SerializationMessageStore messageStore = new SerializationMessageStore();
        CustomMessageStore messageStore = new CustomMessageStore();
        //Message message = new Message(123, new Date(), "Message payload");
        //messageStore.persist(message);
        Message message2 = messageStore.loadMessage();
        System.out.println(message2.toString());
        */

        MessageStore messageStore = new CustomMessageStore("C:\\Dima\\Collection.ser");
        Collection<Message> messages = new ArrayList<Message>();
        //Test save
        /*messages.add(new Message(101, new Date(), "First message"));
        messages.add(new Message(102, new Date(), "Second message"));
        messages.add(new Message(103, new Date(), "The last one"));*/
        for (int i = 0; i < 10; i++) {
            messages.add(new Message(100 + i, new Date(), "Message with ID=" + (1000 + i)));
        }
        messageStore.persist(messages);

        //Test load
        messages = messageStore.loadMessages();
        System.out.println(messages.size());
        for (Message message: messages) {
            System.out.println(message + "\n");
        }
    }
}
