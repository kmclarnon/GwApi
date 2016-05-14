package us.gameandwatching.gwapi.service.auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import us.gameandwatching.gwapi.core.Secrets;
import us.gameandwatching.gwapi.service.daos.SecretsDao;

@Singleton
public class SecretsConfig {

  public static final String COOKIE_CRYPT_ALGORITHM = "AES";
  public static final int KEY_LENGTH = 32;

  private final Secrets secrets;
  private final SecretsDao secretsDao;

  @Inject
  public SecretsConfig(SecretsDao secretsDao) {
    this.secretsDao = secretsDao;
    this.secrets = secretsDao.getSecrets().orElseGet(this::generateAndInsertNewSecrets);
  }

  public Key getCookieKey() {
    return new SecretKeySpec(secrets.getCookieKey().getBytes(StandardCharsets.UTF_8), COOKIE_CRYPT_ALGORITHM);
  }

  private Secrets generateAndInsertNewSecrets() {
    Secrets secrets = Secrets.builder()
        .setCookieKey(RandomStringUtils.randomAlphanumeric(KEY_LENGTH))
        .build();
    secretsDao.insert(secrets);
    return secrets;
  }
}
