package com.ofallonfamily.jersey2akka;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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

@Path("/analyst")
public class MarketAnalysisService {
@Context ActorSystem actorSystem;
    LoggingAdapter log;
	
	@GET
	@Path("stocks")
    @Produces({ MediaType.APPLICATION_JSON })
	@ManagedAsync
    public void getRecommendation(@Suspended final AsyncResponse res) {
		ActorSelection marketActor = actorSystem.actorSelection("/user/marketRouter");
    	Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(marketActor, new MarketMessage("stock-analysis",""), timeout);
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
