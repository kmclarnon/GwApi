package nk.ssb.smashdb.core;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

@SmashDBStyle
@Value.Immutable
public interface AuthCookieIF {
  @JsonProperty("u")
  public int getUserId();
  @JsonProperty("a")
  public long getAuthenticatedAt();
}
