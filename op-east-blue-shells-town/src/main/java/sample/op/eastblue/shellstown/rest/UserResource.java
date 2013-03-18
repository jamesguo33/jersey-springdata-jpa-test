package sample.op.eastblue.shellstown.rest;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sample.op.eastblue.dawnisland.jpa.UserRepository;
import sample.op.eastblue.dawnisland.model.User;



@Path("/user")
@Controller
public class UserResource extends BaseService<User, UserRepository> {

	@Autowired public void setUserRepository(UserRepository repo) {
		super.setRepo(repo);
	}
	
}
