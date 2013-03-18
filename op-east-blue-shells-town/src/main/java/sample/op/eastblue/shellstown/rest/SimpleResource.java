package sample.op.eastblue.shellstown.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;


@Path("/test")
@Controller
public class SimpleResource {
	
	
	@GET
	@Path("/get")
	public String testGet(){
		return "Jersey rolling";		
	}

}
