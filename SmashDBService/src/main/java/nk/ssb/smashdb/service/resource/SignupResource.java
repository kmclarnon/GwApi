package nk.ssb.smashdb.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.inject.Inject;

import nk.ssb.smashdb.core.SignupRequest;
import nk.ssb.smashdb.core.users.UserEgg;
import nk.ssb.smashdb.service.auth.CookieResponseGenerator;
import nk.ssb.smashdb.service.auth.PasswordHasher;
import nk.ssb.smashdb.service.daos.UsersDao;
import nk.ssb.smashdb.service.exceptions.EmailUnavailableException;
import nk.ssb.smashdb.service.exceptions.MismatchPasswordException;
import nk.ssb.smashdb.service.exceptions.UsernameUnavailableException;

@Path("signup")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SignupResource {

  private static final int SALT_LENGTH = 10;
  private static final int INITIAL_ELO = 1000;

  private final CookieResponseGenerator cookieResponseGenerator;
  private final PasswordHasher passwordHasher;
  private final UsersDao usersDao;

  @Inject
  public SignupResource(CookieResponseGenerator cookieResponseGenerator,
                        PasswordHasher passwordHasher,
                        UsersDao usersDao) {
    this.cookieResponseGenerator = cookieResponseGenerator;
    this.passwordHasher = passwordHasher;
    this.usersDao = usersDao;
  }

  @POST
  public Response signup(SignupRequest signupRequest) {
    validateSignup(signupRequest);
    String passwordSalt = RandomStringUtils.randomAlphabetic(SALT_LENGTH);
    String passwordHash = passwordHasher.hash(signupRequest.getPassword(), passwordSalt);
    int userId = usersDao.insert(UserEgg.builder()
        .setEmail(signupRequest.getEmail())
        .setPasswordHash(passwordHash)
        .setPasswordSalt(passwordSalt)
        .setElo(INITIAL_ELO)
        .setCreatedAt(System.currentTimeMillis())
        .build());
    return cookieResponseGenerator.responseWithCookie(Response.ok().build(), userId);
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
