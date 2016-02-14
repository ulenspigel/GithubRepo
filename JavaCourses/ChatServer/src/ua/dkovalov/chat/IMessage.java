package ua.dkovalov.chat;

import java.io.Serializable;
import java.util.Calendar;

public interface IMessage extends Serializable {
    String getSenderID();
    Calendar getDate();
    String getContent();
}
