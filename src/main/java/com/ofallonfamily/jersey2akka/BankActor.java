package com.ofallonfamily.jersey2akka;

import ae.stock.dao.BankAccountDAO;
import ae.stock.dao.MoneyTransactionDAO;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class BankActor extends UntypedActor{

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public static Props mkProps() {
        return Props.create(BankActor.class);
    }
    @Override
    public void preStart() {
        log.debug("starting");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        BankMessage msg=(BankMessage)message;
        if(msg instanceof BankMessage) {
            if(msg.getMessageType().contentEquals("bank-details")) {
                getSender().tell(BankAccountDAO.get(msg.getMessage()), getSelf());
            }else if(msg.getMessageType().contentEquals("account-transactions")) {
                getSender().tell(MoneyTransactionDAO.getTransactions(msg.getMessage()), getSelf());
            }else if(msg.getMessageType().contentEquals("bank-accounts")) {
                getSender().tell(BankAccountDAO.getAll(), getSelf());
            }
        }

    }

}
