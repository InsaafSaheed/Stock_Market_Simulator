package com.ofallonfamily.jersey2akka;

public class ClockMessage {
    private String MessageType;
    private String Message;
    public String getMessageType() {
        return MessageType;
    }
    public void setMessageType(String messageType) {
        MessageType = messageType;
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        Message = message;
    }
    public ClockMessage(String messageType, String message) {
        super();
        MessageType = messageType;
        Message = message;
    }
	
}
