package com.ofallonfamily.jersey2akka;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ManagedAsync;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.dispatch.OnComplete;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

@Path("/player-broker")
public class PlayerBrokerService {

	@Context ActorSystem actorSystem;
    LoggingAdapter log;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ManagedAsync
    public void getCompanies(@Suspended final AsyncResponse res) {
    	ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("companies",""), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	
	@GET
    @Path("player-info/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getPlayer(@PathParam("player") String player,@Suspended final AsyncResponse res) {
		ActorSelection playerActor = actorSystem.actorSelection("/user/playerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(playerActor, new PlayerMessage("player-info",player), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	@GET
    @Path("bankdetails/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void bankAccount(@PathParam("player") String player,@Suspended final AsyncResponse res) {
		ActorSelection bankActor = actorSystem.actorSelection("/user/bankRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(bankActor, new BankMessage("bank-details",player), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	
	@GET
    @Path("stocktransactions/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getTrans(@PathParam("player") String player,@Suspended final AsyncResponse res) {
		ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("stock-transactions",player), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	@GET
    @Path("accounttransactions/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getAccTrans(@PathParam("player") String player,@Suspended final AsyncResponse res) {
		ActorSelection bankActor = actorSystem.actorSelection("/user/bankRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(bankActor, new BankMessage("account-transactions",player), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	@GET
    @Path("playershares/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getPlayerStocks(@PathParam("player") String player,@Suspended final AsyncResponse res) {
		ActorSelection playerActor = actorSystem.actorSelection("/user/playerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(playerActor, new PlayerMessage("player-shares",player), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	@GET
    @Path("players")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getPlayers(@Suspended final AsyncResponse res) {
		ActorSelection playerActor = actorSystem.actorSelection("/user/playerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(playerActor, new PlayerMessage("all-players",""), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	
	@GET
	@Path("addplayer/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void addplayer(@PathParam("player_name") String player_name,@Suspended final AsyncResponse res) {
		ActorSelection playerActor = actorSystem.actorSelection("/user/playerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(playerActor, new PlayerMessage("new-player",player_name), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	
	@GET
    @Path("sell/{player}/{company}/{stocks}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void sellShares(@PathParam("player") String player,@PathParam("company") String company,@PathParam("stocks") int stocks,@Suspended final AsyncResponse res) {
		ActorSelection brokerActor = actorSystem.actorSelection("/user/brokerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(brokerActor, new BrokerMessage("sell",player,company,stocks), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	
	@GET
    @Path("buy/{player}/{company}/{stocks}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void buyshares(@PathParam("player") String player,@PathParam("company") String company,@PathParam("stocks") int stocks,@Suspended final AsyncResponse res) {
		ActorSelection brokerActor = actorSystem.actorSelection("/user/brokerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(brokerActor, new BrokerMessage("buy",player,company,stocks), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }
        },actorSystem.dispatcher());
    }
	
	@GET
    @Path("stock-count/{company_name}/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getStockCount(@PathParam("company_name") String company_name,@PathParam("player_name") String player_name,@Suspended final AsyncResponse res) {
		ActorSelection brokerActor = actorSystem.actorSelection("/user/brokerRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(brokerActor, new BrokerMessage("stock-count",player_name,company_name,0), timeout);
        future.onComplete(new OnComplete<Object>() {
            public void onComplete(Throwable failure, Object result) {
                if (failure != null) {
                    if (failure.getMessage() != null) {
                        HashMap<String,String> response = new HashMap<String,String>();
                        response.put("error", failure.getMessage());
                        res.resume(Response.serverError().entity(response).build());
                    } else {
                        res.resume(Response.serverError());
                    }
                } else {
                    HashMap<String,Object> response = new HashMap<String,Object>();
                    response.put("results",result);
                    res.resume(Response.ok().entity(response).build());
                }
            }

        },actorSystem.dispatcher());
    }
}
