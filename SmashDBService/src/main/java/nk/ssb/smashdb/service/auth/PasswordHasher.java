package nk.ssb.smashdb.service.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.google.common.base.Throwables;

public class PasswordHasher {

  public static String hash(String password, String salt) {
    try {
      String payload = password + salt;
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return Base64.getUrlEncoder().encodeToString(digest.digest(payload.getBytes(StandardCharsets.UTF_8)));
    } catch (NoSuchAlgorithmException e) {
      throw Throwables.propagate(e);
    }
  }
}
