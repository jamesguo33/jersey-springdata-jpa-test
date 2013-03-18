package sample.op.eastblue.shellstown.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import sample.op.eastblue.dawnisland.model.User;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserResourceTest extends BaseTestClient {
	
	protected Long createUser(String name) {
		WebResource resource = resource();
		User user = new User();
		user.setFullName(name);
		ClientResponse clientResponse = resource
				.type(MediaType.APPLICATION_JSON_TYPE)
				.accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, "{\"fullName\":\"User 1\"}");
		String location = clientResponse.getHeaders().getFirst("Location");		
		location = location.substring(getPath().length() + getResourcePath().length());
		Long id = new Long(location);
		return id;
	}
	
	@Test
	public void testGetUser() {
		Long id = createUser("User 1");
		String result = resource().path(id.toString()).get(String.class);
		System.out.println(result);
		assertNotNull(result);
		
		User user = json2Obj(result, User.class);
		assertNotNull(user);
		assertEquals(id.intValue(), user.getId().intValue());
	}



	protected String getResourcePath() {
		return "/user";
	}
}
