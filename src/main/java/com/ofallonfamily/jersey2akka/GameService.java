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

@Path("/game")
public class GameService {
    @Context ActorSystem actorSystem;
    LoggingAdapter log;

    @GET
    @Path("init")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void initMarket(@Suspended final AsyncResponse res) {
        ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("init",""), timeout);
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
        ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("players",""), timeout);
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
    @Path("companies")
    @Produces({ MediaType.APPLICATION_JSON })
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
    @Path("current-round")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void getRound(@Suspended final AsyncResponse res) {
        ActorSelection marketActor = actorSystem.actorSelection("/user/clockRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new ClockMessage("current-round",""), timeout);
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
    @Path("new-round")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void newRound(@Suspended final AsyncResponse res) {
        ActorSelection marketActor = actorSystem.actorSelection("/user/clockRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new ClockMessage("new-round",""), timeout);
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
    @Path("player-stock/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void getPlayerStocks(@PathParam("player_name") String player_name,@Suspended final AsyncResponse res) {
        ActorSelection playerActor = actorSystem.actorSelection("/user/playerRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(playerActor, new PlayerMessage("player-shares",player_name), timeout);
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
    @Path("bank-accounts")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void getAccounts(@Suspended final AsyncResponse res) {
        ActorSelection bankActor = actorSystem.actorSelection("/user/bankRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(bankActor, new BankMessage("bank-accounts",""), timeout);
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
    @Path("ai-players")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void getAIs(@Suspended final AsyncResponse res) {
        ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("ai-players",""), timeout);
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
    @Path("company-trends")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void getCompanyTrends(@Suspended final AsyncResponse res) {
        ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("company-trends",""), timeout);
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
    @Path("update_round/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
    @ManagedAsync
    public void updateRound(@PathParam("player_name") String player_name,@Suspended final AsyncResponse res) {
        ActorSelection marketActor = actorSystem.actorSelection("/user/clockRouter");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new ClockMessage("update-round",player_name), timeout);
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
