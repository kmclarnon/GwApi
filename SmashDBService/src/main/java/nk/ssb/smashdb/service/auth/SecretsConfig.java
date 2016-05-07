package nk.ssb.smashdb.service.auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import nk.ssb.smashdb.core.Secrets;
import nk.ssb.smashdb.service.daos.SecretsDao;

@Singleton
public class SecretsConfig {

  public static final String COOKIE_CRYPT_ALGORITHM = "AES";

  private final Secrets secrets;

  @Inject
  public SecretsConfig(SecretsDao secretsDao) {
    this.secrets = secretsDao.getSecrets();
  }

  public Key getCookieKey() {
    return new SecretKeySpec(secrets.getCookieKey().getBytes(StandardCharsets.UTF_8), COOKIE_CRYPT_ALGORITHM);
  }
}
