package com.ofallonfamily.jersey2akka;

import java.util.List;

import ae.stock.dao.BankAccountDAO;
import ae.stock.dao.CompanyDAO;
import ae.stock.dao.MoneyTransactionDAO;
import ae.stock.dao.PlayerDAO;
import ae.stock.dao.PlayerSharesDAO;
import ae.stock.dao.StockTransactionsDAO;
import ae.stock.entities.BankAccount;
import ae.stock.entities.Company;
import ae.stock.entities.MoneyTransaction;
import ae.stock.entities.Player;
import ae.stock.entities.PlayerShares;
import ae.stock.entities.StockTransactions;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class BrokerActor extends UntypedActor{

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public static Props mkProps() {
        return Props.create(BrokerActor.class);
    }
    @Override
    public void preStart() {
        log.debug("starting");
    }
    @Override
    public void onReceive(Object message) throws Exception {
        BrokerMessage msg=(BrokerMessage)message;
        if(msg instanceof BrokerMessage) {
            if(msg.getMessageType().equals("buy")) {
                List<PlayerShares> stocks_list=PlayerSharesDAO.getStocks(msg.getPlayer());
                Player plyr=PlayerDAO.get(msg.getPlayer());
                boolean found=false;
                for(Company cmpny:CompanyDAO.getAll()) {
                    if(cmpny.getCompany_Name().equals(msg.getCompany())) {
                        for(PlayerShares ps:stocks_list) {
                            if(ps.getCompany().equals(cmpny.getCompany_Name())) {
                                found=true;
                                for(BankAccount bank_account:BankAccountDAO.getAll()) {
                                    if(bank_account.getAccountHolder().equals(plyr.getPlayer_name())) {
                                        if(bank_account.getAccountBalance()>=(cmpny.getShare_Vlaue()*msg.getStockAmount())) {
                                            ps.setStock_Count(ps.getStock_Count()+msg.getStockAmount());
                                            ps.setStock_Value(ps.getStock_Count()*cmpny.getShare_Vlaue());
                                            PlayerSharesDAO.update(ps);
                                            StockTransactions transaction=new StockTransactions(plyr.getPlayer_name(),ps.getCompany(),msg.getStockAmount(),cmpny.getShare_Vlaue()*msg.getStockAmount(),"BUY");
                                            StockTransactionsDAO.save(transaction);
                                            plyr.setStock_value(plyr.getStock_value()+(cmpny.getShare_Vlaue()*msg.getStockAmount()));
                                            PlayerDAO.update(plyr);
                                            cmpny.setNo_of_Stocks(cmpny.getNo_of_Stocks()+msg.getStockAmount());
                                            CompanyDAO.update(cmpny);
                                            MoneyTransaction account_transaction=new MoneyTransaction(bank_account.getAccountHolder(), "WITHDRAW", (cmpny.getShare_Vlaue()*msg.getStockAmount()), (bank_account.getAccountBalance()-(cmpny.getShare_Vlaue()*msg.getStockAmount())));
                                            MoneyTransactionDAO.save(account_transaction);
                                            BankAccountDAO.withdraw(bank_account, (cmpny.getShare_Vlaue()*msg.getStockAmount()));
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        if(found==false) {

                            for(BankAccount bank_account:BankAccountDAO.getAll()) {
                                if(bank_account.getAccountHolder().equals(plyr.getPlayer_name())) {
                                    if(bank_account.getAccountBalance()>=(cmpny.getShare_Vlaue()*msg.getStockAmount())) {
                                        PlayerShares player_stock=new PlayerShares(plyr.getPlayer_name(),cmpny.getCompany_Name(),msg.getStockAmount(),cmpny.getShare_Vlaue()*msg.getStockAmount());
                                        PlayerSharesDAO.save(player_stock);
                                        StockTransactions transaction=new StockTransactions(plyr.getPlayer_name(),cmpny.getCompany_Name(),msg.getStockAmount(),cmpny.getShare_Vlaue()*msg.getStockAmount(),"BUY");
                                        StockTransactionsDAO.save(transaction);
                                        plyr.setStock_value(plyr.getStock_value()+(cmpny.getShare_Vlaue()*msg.getStockAmount()));
                                        PlayerDAO.update(plyr);
                                        CompanyDAO.update(cmpny);
                                        MoneyTransaction account_transaction=new MoneyTransaction(bank_account.getAccountHolder(), "WITHDRAW", (cmpny.getShare_Vlaue()*msg.getStockAmount()), (bank_account.getAccountBalance()-(cmpny.getShare_Vlaue()*msg.getStockAmount())));
                                        MoneyTransactionDAO.save(account_transaction);
                                        BankAccountDAO.withdraw(bank_account, (cmpny.getShare_Vlaue()*msg.getStockAmount()));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                getSender().tell(true, getSelf());
            }else if(msg.getMessageType().equals("sell")) {
                List<PlayerShares> stocks_list=PlayerSharesDAO.getStocks(msg.getPlayer());
                Player plyr=PlayerDAO.get(msg.getPlayer());
                for(Company cmpny:CompanyDAO.getAll()) {
                    if(cmpny.getCompany_Name().equals(msg.getCompany())) {
                        for(PlayerShares ps:stocks_list) {
                            if(ps.getCompany().equals(cmpny.getCompany_Name())) {
                                ps.setStock_Count(ps.getStock_Count()-msg.getStockAmount());
                                ps.setStock_Value(ps.getStock_Count()*cmpny.getShare_Vlaue());
                                PlayerSharesDAO.update(ps);
                                StockTransactions transaction=new StockTransactions(plyr.getPlayer_name(),ps.getCompany(),msg.getStockAmount(),cmpny.getShare_Vlaue()*msg.getStockAmount(),"SELL");
                                StockTransactionsDAO.save(transaction);
                                plyr.setStock_value(plyr.getStock_value()-(cmpny.getShare_Vlaue()*msg.getStockAmount()));
                                PlayerDAO.update(plyr);
                                for(BankAccount bank_account:BankAccountDAO.getAll()) {
                                    if(bank_account.getAccountHolder().equals(plyr.getPlayer_name())) {
                                        BankAccountDAO.deposit(bank_account, (cmpny.getShare_Vlaue()*msg.getStockAmount()));
                                        MoneyTransaction account_transaction=new MoneyTransaction(bank_account.getAccountHolder(), "DEPOSIT", (cmpny.getShare_Vlaue()*msg.getStockAmount()), (bank_account.getAccountBalance()+(cmpny.getShare_Vlaue()*msg.getStockAmount())));
                                        MoneyTransactionDAO.save(account_transaction);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                getSender().tell(true, getSelf());
            }else if(msg.getMessageType().equals("stock-count")) {
                getSender().tell(PlayerSharesDAO.getShareCount(msg.getCompany(), msg.getPlayer()), getSelf());
            }
        }else {
            unhandled(message);
        }

    }

}
