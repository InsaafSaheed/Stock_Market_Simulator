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



}
