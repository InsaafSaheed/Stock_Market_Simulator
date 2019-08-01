package com.ofallonfamily.jersey2akka;

import java.util.ArrayList;
import java.util.List;

import ae.stock.core.AiPlayerCore;
import ae.stock.core.GameCore;
import ae.stock.dao.CompanyDAO;
import ae.stock.dao.PlayerDAO;
import ae.stock.dao.StockTransactionsDAO;
import ae.stock.entities.AnalystSuggession;
import ae.stock.entities.ShareValues;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MarketActor extends UntypedActor{
LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	public static Props mkProps() {
        return Props.create(MarketActor.class);
    }
	@Override
    public void preStart() {
        log.debug("starting");
    }
	@Override
	public void onReceive(Object message) throws Exception {
		MarketMessage msg=(MarketMessage)message;
		if(msg instanceof MarketMessage) {
			if(msg.getMessageType().equals("players")) {
				getSender().tell(PlayerDAO.getAll(), getSelf());
			}else if(msg.getMessageType().equals("companies")){
				getSender().tell(CompanyDAO.getAll(), getSelf());
			}else if(msg.getMessageType().equals("ai-players")){
				getSender().tell(AiPlayerCore.getPlayers(), getSelf());
			}else if(msg.getMessageType().equals("company-trends")){
				getSender().tell(GameCore.getCompanyTrends(), getSelf());
			}else if(msg.getMessageType().equals("init")){
				GameCore.initialize();
				getSender().tell("Success", getSelf());
			}else if(msg.getMessageType().equals("stock-transactions")) {
				getSender().tell(StockTransactionsDAO.getTransactions(msg.getMessage()), getSelf());
			}else if(msg.getMessageType().equals("stock-analysis")) {
				List<ShareValues> company_trends=GameCore.getCompanyTrends();
		        List<AnalystSuggession> recommendations=new ArrayList<AnalystSuggession>();
		        for(ShareValues company_trend:company_trends) {
		        	int round=GameCore.getCurrent_round()-1;
		        	int remaining=10-round;
		        	int future_up=remaining/2;
		        	double current_value=company_trend.getRound_values()[round];
		        	double future_value=company_trend.getRound_values()[(round+future_up)];
		        	double ratio=(future_value/current_value)*100;
		        	if(ratio>=110) {
		        		AnalystSuggession recommendation=new AnalystSuggession(company_trend.getCompany_name(), "BUY");
		        		recommendations.add(recommendation);
		        	}
		        	else if(ratio>=10 && ratio<100){
		        		AnalystSuggession recommendation=new AnalystSuggession(company_trend.getCompany_name(), "SELL");
		        		recommendations.add(recommendation);
		        	}
		        }
				getSender().tell(recommendations, getSelf());
			}
		}else {
            unhandled(message);
        }
	}


}
