package com.transformers.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.transformers.model.Transformers;
import com.transformers.service.TransformersService;

@Path("/myresource")
public class TransformersResource {
	
	TransformersService service = new TransformersService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Transformers addTransformers(Transformers transformers){
		return service.addTransformers(transformers);
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Transformers getTransformers(@PathParam("id") int id){
		return service.getTransformers(id);
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/all")
	public ArrayList<Transformers> getAllTransformers(){
		return service.getAllTransformers();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Transformers updateTransformers(@PathParam("id") int id,Transformers transformers){
		return service.updateTransformers(transformers, id);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deleteTransformer(@PathParam("id") int id){
		 service.deleteTransformer(id);
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/autobots")
	public CopyOnWriteArrayList<Transformers> getAllAutobotsTransformers(){
		return service.getAllAutobotsTransformers();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/decepticons")
	public CopyOnWriteArrayList<Transformers> getAllDecepticonsTransformers(){
		return service.getAllDecepticonsTransformers();
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/fight")
	public List<String> startFight(){
		return service.startFight();
	}
	
	
}
