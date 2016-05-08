package nk.ssb.smashdb.core;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

@SmashDBStyle
@Value.Immutable
public interface AuthCookieIF {
  @JsonProperty("u")
  int getUserId();
  @JsonProperty("a")
  long getAuthenticatedAt();
}
