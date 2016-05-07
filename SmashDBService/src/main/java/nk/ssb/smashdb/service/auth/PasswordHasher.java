package nk.ssb.smashdb.service.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Throwables;
import com.google.inject.Singleton;

@Singleton
public class PasswordHasher {

  public String hash(String password, String salt) {
    try {
      String payload = password + salt;
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return new String(digest.digest(payload.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    } catch (NoSuchAlgorithmException e) {
      throw Throwables.propagate(e);
    }
  }
}
