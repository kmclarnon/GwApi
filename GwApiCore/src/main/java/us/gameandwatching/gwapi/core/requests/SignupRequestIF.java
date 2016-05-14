package us.gameandwatching.gwapi.core.requests;

import org.immutables.value.Value;

import us.gameandwatching.gwapi.core.SmashDBStyle;

@SmashDBStyle
@Value.Immutable
public interface SignupRequestIF {
  String getEmail();
  String getUsername();
  String getPassword();
  String getPasswordConfirmation();
}
