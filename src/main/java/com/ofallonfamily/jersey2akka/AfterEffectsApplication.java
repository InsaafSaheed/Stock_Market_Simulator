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



}