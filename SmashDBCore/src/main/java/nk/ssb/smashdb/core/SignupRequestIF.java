package nk.ssb.smashdb.core;

import org.immutables.value.Value;

@SmashDBStyle
@Value.Immutable
public interface SignupRequestIF {
  String getEmail();
  String getUsername();
  String getPassword();
  String getPasswordConfirmation();
}
