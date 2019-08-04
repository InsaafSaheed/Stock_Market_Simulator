package com.ofallonfamily.jersey2akka;

import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import ae.stock.core.GameCore;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import scala.concurrent.duration.Duration;

import javax.annotation.PreDestroy;
import javax.ws.rs.ApplicationPath;
import java.util.concurrent.TimeUnit;

@ApplicationPath("/after-effects")
public class AfterEffectsApplication extends ResourceConfig {

 private ActorSystem system;

    public AfterEffectsApplication() {

    	GameCore.initialize();
        system = ActorSystem.create("ExampleSystem");
        system.actorOf(PlayerActor.mkProps().withRouter(new RoundRobinPool(5)),"playerRouter");
        system.actorOf(MarketActor.mkProps().withRouter(new RoundRobinPool(5)),"marketRouter");
        system.actorOf(BrokerActor.mkProps().withRouter(new RoundRobinPool(5)),"brokerRouter");
        system.actorOf(BankActor.mkProps().withRouter(new RoundRobinPool(5)),"bankRouter");
        system.actorOf(ClockActor.mkProps().withRouter(new RoundRobinPool(5)),"clockRouter");

        register(new AbstractBinder() {
            protected void configure() {
                bind(system).to(ActorSystem.class);
            }
        });

        register(new JacksonJsonProvider().
            configure(SerializationFeature.INDENT_OUTPUT, true));

        packages("com.ofallonfamily.jersey2akka");

    }

    @PreDestroy
    private void shutdown() {
        system.shutdown();
        system.awaitTermination(Duration.create(15, TimeUnit.SECONDS));
    }

}