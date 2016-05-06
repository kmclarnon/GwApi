package nk.smashdb.service.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.inject.Inject;

import nk.smashdb.core.User;
import nk.smashdb.core.UserEgg;
import nk.smashdb.service.daos.UsersDao;

@Path("users")
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
