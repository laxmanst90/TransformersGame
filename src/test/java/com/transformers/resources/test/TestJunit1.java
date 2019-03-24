package com.transformers.resources.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import com.transformers.model.Transformers;
import com.transformers.resources.TransformersResource;

public class TestJunit1 extends JerseyTest {
	
	@Override
	public Application configure(){
		enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(TransformersResource.class);
		
	}

	@Test
	public void fetchAllTransformers(){
		Response output = target("/myresource/all").request().get();
		assertEquals("should return status 200", 200,output.getStatus());
		assertNotNull("should return list", output.getEntity());
	}
	
	@Test
	public void testFetchAutobots(){
		Response output = target("/myresource/autobots").request().get();
		assertEquals("should return status 200", 200,output.getStatus());
		assertNotNull("should return list of autobots only ", output.getEntity());
	}
	
	@Test
	public void testFetchDecepticons(){
		Response output = target("/myresource/decepticons").request().get();
		assertEquals("should return status 200", 200,output.getStatus());
		assertNotNull("should return list of decepticons only ", output.getEntity());
	}
	
	@Test
	public void testCreateTransformers(){
		Transformers transformers = new Transformers(1,6,6,6,6,8,8,8,8,"Autobots","Bumble Bee",false);
		Response output = target("/myresource").request().post(Entity.entity(transformers, MediaType.APPLICATION_JSON));
		assertEquals("should return status 200", 200,output.getStatus());
		assertNotNull("should return created transformer ", output.getEntity());
	}
	
	@Test
	public void testUpdateTransformers(){
		Transformers transformers = new Transformers(1,10,10,4,2,9,9,9,8,"Decepticons","Predaking",false);
		Response output = target("/myresource/2").request().put(Entity.entity(transformers, MediaType.APPLICATION_JSON));
		assertEquals("should return status 200", 200,output.getStatus());
	}
	
	@Test
	public void deleteTransformer(){
		Response output = target("/myresource/1").request().delete();
		assertEquals("should return 204", 204,output.getStatus());
	}
	
	@Test
	public void startFight(){
		Response output = target("/myresource/fight").request().get();
		assertEquals("should return 200", 200,output.getStatus());
	}
	
	
	}
