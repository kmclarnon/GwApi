package nk.ssb.smashdb.service.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import nk.ssb.smashdb.core.users.User;
import nk.ssb.smashdb.core.users.UserEgg;
import nk.ssb.smashdb.service.daos.UsersDao;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

  private final UsersDao usersDao;

  @Inject
  public UsersResource(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  @GET
  public List<User> getUsers() {
    return usersDao.getAllUsers();
  }

  @POST
  public void createUser(UserEgg userEgg) {
    usersDao.insert(userEgg);
  }
}
