package com.ofallonfamily.jersey2akka;

import ae.stock.core.GameCore;
import ae.stock.dao.PlayerDAO;
import ae.stock.entities.Player;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ClockActor extends UntypedActor{

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public static Props mkProps() {
        return Props.create(ClockActor.class);
    }
    @Override
    public void preStart() {
        log.debug("starting");
    }
    @Override
    public void onReceive(Object message) throws Exception {
        ClockMessage msg=(ClockMessage)message;
        if(msg instanceof ClockMessage) {
            if(msg.getMessageType().equals("current-round")) {
                getSender().tell(GameCore.getCurrent_round(), getSelf());
            }else if(msg.getMessageType().equals("new-round")) {
                GameCore.setCurrent_round(GameCore.getCurrent_round()+1);
                getSender().tell(GameCore.getCurrent_round(), getSelf());
            }else if(msg.getMessageType().equals("update-round")) {
                Player p=new Player();
                for(Player plyr:PlayerDAO.getAll()) {
                    if(plyr.getPlayer_name().equals(msg.getMessage())) {
                        p=plyr;
                        int cur_round=p.getCurrent_round();
                        if(cur_round<10) {
                            p.setCurrent_round(p.getCurrent_round()+1);
                            PlayerDAO.update(p);
                        }

                    }
                }
                getSender().tell(p, getSelf());
            }
        }
    }

}
