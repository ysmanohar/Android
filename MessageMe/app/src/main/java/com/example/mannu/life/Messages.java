package com.example.mannu.life;

import java.sql.Time;
import java.util.Date;

/**
 * Created by mannu on 6/27/2016.
 */
public class Messages {
    String sender,receiver,message,time;
    String isread;





    public Messages(String sender, String message, String isread,String time) {
        this.sender = sender;
        this.message = message;
        this.isread = isread;
        this.time=time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }
}
