package com.ofallonfamily.jersey2akka;

public class BankMessage {
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
    public BankMessage(String messageType, String message) {
        super();
        MessageType = messageType;
        Message = message;
    }
	
}
