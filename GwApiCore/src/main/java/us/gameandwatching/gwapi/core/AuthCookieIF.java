package us.gameandwatching.gwapi.core;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

@GwStyle
@Value.Immutable
public interface AuthCookieIF {
  @JsonProperty("u")
  int getUserId();
  @JsonProperty("a")
  long getAuthenticatedAt();
}
