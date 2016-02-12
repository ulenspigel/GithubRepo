package ua.dkovalov.chat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message implements Serializable {
    private String senderID;
    private Calendar date;
    private String content;

    public Message(String senderID, Calendar date, String content) {
        this.senderID = senderID;
        this.date = date;
        this.content = content;
    }

    public Message() { }

    @Override
    public String toString() {
        return "SenderID=" + senderID + "\nDate=" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date.getTime()) + "\nContent=" + content;
    }

    public boolean equals(Message message) {
        return this.senderID.equals(message.getSenderID()) && this.date.equals(message.getDate()) && this.content.equals(message.getContent());
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
