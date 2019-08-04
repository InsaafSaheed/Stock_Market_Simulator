package com.ofallonfamily.jersey2akka;

import ae.stock.core.GameCore;
import ae.stock.dao.BankAccountDAO;
import ae.stock.dao.PlayerDAO;
import ae.stock.dao.PlayerSharesDAO;
import ae.stock.entities.BankAccount;
import ae.stock.entities.Player;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PlayerActor extends UntypedActor{

LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	public static Props mkProps() {
        return Props.create(PlayerActor.class);
    }
	@Override
    public void preStart() {
        log.debug("starting");
    }
	
	@Override
	public void onReceive(Object message) throws Exception {
		PlayerMessage msg=(PlayerMessage)message;
		if (msg instanceof PlayerMessage) {
			if(msg.getMessageType().equals("all-players")) {
				getSender().tell(PlayerDAO.getAll(), getSelf());
			}else if(msg.getMessageType().equals("new-player")) {
				Player player=new Player(msg.getMessage());
				boolean not_found=true;
				for(Player plyr:PlayerDAO.getAll()) {
					if(plyr.getPlayer_name().equals(player.getPlayer_name())) {
						not_found=false;
					}
				}
				if(not_found) {
					BankAccount bank_account=new BankAccount(player.getPlayer_name());
					BankAccountDAO.save(bank_account);
					PlayerDAO.save(player);
					GameCore.checkPlayers();
					getSender().tell(not_found, getSelf());
				}else {
					getSender().tell(not_found, getSelf());
				}
			}else if(msg.getMessageType().equals("player-info")) {
				getSender().tell(PlayerDAO.get(msg.getMessage()), getSelf());
			}else if(msg.getMessageType().equals("player-shares")) {
				getSender().tell(PlayerSharesDAO.getStocks(msg.getMessage()), getSelf());
			}
        } else {
            unhandled(message);
        }
		
	}

}
