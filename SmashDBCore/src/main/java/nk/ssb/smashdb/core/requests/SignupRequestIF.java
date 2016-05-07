package nk.ssb.smashdb.core.requests;

import org.immutables.value.Value;

import nk.ssb.smashdb.core.SmashDBStyle;

@SmashDBStyle
@Value.Immutable
public interface SignupRequestIF {
  String getEmail();
  String getUsername();
  String getPassword();
  String getPasswordConfirmation();
}
