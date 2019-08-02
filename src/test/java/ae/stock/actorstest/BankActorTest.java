package ae.stock.actorstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.ofallonfamily.jersey2akka.AfterEffectsApplication;

public class BankActorTest extends JerseyTest{
	
protected Application configure() {
        return new AfterEffectsApplication();
    }
	
	protected void configureClient(ClientConfig clientConfig) {
        clientConfig.register(new JacksonJsonProvider());
    }

@Test
    public void testBank() {
		Response response = target("/player-broker/addplayer/abc").request().get();
		  assertEquals("should return status 200", 200, response.getStatus());
		  assertNotNull("Should return boolean", response.getEntity().toString());
		  System.out.println(response.getStatus());
		  System.out.println(response.readEntity(String.class));
		  
		  response = target("/game/bank-accounts").request().get();
		  assertEquals("should return status 200", 200, response.getStatus());
		  assertNotNull("Should return boolean", response.getEntity().toString());
		  System.out.println(response.getStatus());
		  System.out.println(response.readEntity(String.class));
		  
		  response = target("/player-broker/bankdetails/abc").request().get();
		  assertEquals("should return status 200", 200, response.getStatus());
		  assertNotNull("Should return boolean", response.getEntity().toString());
		  System.out.println(response.getStatus());
		  System.out.println(response.readEntity(String.class));
		  
		  response = target("/player-broker/accounttransactions/abc").request().get();
		  assertEquals("should return status 100", 200, response.getStatus());
		  assertNotNull("Should return boolean", response.getEntity().toString());
		  System.out.println(response.getStatus());
		  System.out.println(response.readEntity(String.class));
		  
	}

}
