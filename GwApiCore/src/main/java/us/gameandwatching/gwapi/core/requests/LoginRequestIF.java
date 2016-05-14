package us.gameandwatching.gwapi.core.requests;

import org.immutables.value.Value;

import us.gameandwatching.gwapi.core.GwStyle;

@GwStyle
@Value.Immutable
public interface LoginRequestIF {
  String getEmail();
  String getPassword();
}
