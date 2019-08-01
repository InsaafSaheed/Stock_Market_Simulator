package com.ofallonfamily.jersey2akka;

public class BrokerMessage {
    private String messageType;
    private String player;
    private String company;
    private int stockAmount;
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public int getStockAmount() {
        return stockAmount;
    }
    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }
    public BrokerMessage(String messageType, String player, String company, int stockAmount) {
        super();
        this.messageType = messageType;
        this.player = player;
        this.company = company;
        this.stockAmount = stockAmount;
    }
	
}
