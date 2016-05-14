package us.gameandwatching.gwapi.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.inject.Inject;

import us.gameandwatching.gwapi.core.requests.LoginRequest;
import us.gameandwatching.gwapi.core.requests.SignupRequest;
import us.gameandwatching.gwapi.core.users.User;
import us.gameandwatching.gwapi.core.users.UserEgg;
import us.gameandwatching.gwapi.service.auth.cookie.CookieResponseGenerator;
import us.gameandwatching.gwapi.service.auth.PasswordHasher;
import us.gameandwatching.gwapi.service.daos.UsersDao;
import us.gameandwatching.gwapi.service.exceptions.EmailUnavailableException;
import us.gameandwatching.gwapi.service.exceptions.InvalidUserPasswordException;
import us.gameandwatching.gwapi.service.exceptions.MismatchPasswordException;
import us.gameandwatching.gwapi.service.exceptions.UsernameUnavailableException;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

  private static final int SALT_LENGTH = 10;
  private static final int INITIAL_ELO = 1000;

  private final CookieResponseGenerator cookieResponseGenerator;
  private final UsersDao usersDao;

  @Inject
  public AuthResource(CookieResponseGenerator cookieResponseGenerator,
                        UsersDao usersDao) {
    this.cookieResponseGenerator = cookieResponseGenerator;
    this.usersDao = usersDao;
  }

  @POST
  @Path("signup")
  public Response signup(SignupRequest signupRequest) {
    validateSignup(signupRequest);
    String passwordSalt = RandomStringUtils.randomAlphabetic(SALT_LENGTH);
    String passwordHash = PasswordHasher.hash(signupRequest.getPassword(), passwordSalt);
    int userId = usersDao.insert(UserEgg.builder()
        .setEmail(signupRequest.getEmail())
        .setUsername(signupRequest.getUsername())
        .setPasswordHash(passwordHash)
        .setPasswordSalt(passwordSalt)
        .setElo(INITIAL_ELO)
        .setCreatedAt(System.currentTimeMillis())
        .build());
    return cookieResponseGenerator.responseWithCookie(Response.ok().build(), userId);
  }

  @POST
  @Path("login")
  public Response login(LoginRequest loginRequest) {
    User user = usersDao.getUserByEmail(loginRequest.getEmail()).orElseThrow(InvalidUserPasswordException::new);
    String requestPasswordHash = PasswordHasher.hash(loginRequest.getPassword(), user.getPasswordSalt());
    if (!requestPasswordHash.equals(user.getPasswordHash())) {
      throw new InvalidUserPasswordException();
    }
    return cookieResponseGenerator.responseWithCookie(Response.ok().build(), user.getId());
  }

  @POST
  @Path("logout")
  public Response logout() {
    return cookieResponseGenerator.responseErasingCookie(Response.ok().build());
  }

  private void validateSignup(SignupRequest signupRequest) {
    if (!signupRequest.getPassword().equals(signupRequest.getPasswordConfirmation())) {
      throw new MismatchPasswordException();
    }
    if (usersDao.getUserByEmail(signupRequest.getEmail()).isPresent()) {
      throw new EmailUnavailableException();
    }
    if (usersDao.getUserByUsername(signupRequest.getUsername()).isPresent()) {
      throw new UsernameUnavailableException();
    }
  }
}
