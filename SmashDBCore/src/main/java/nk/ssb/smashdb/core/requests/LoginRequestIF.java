package nk.ssb.smashdb.core.requests;

import org.immutables.value.Value;

import nk.ssb.smashdb.core.SmashDBStyle;

@SmashDBStyle
@Value.Immutable
public interface LoginRequestIF {
  String getEmail();
  String getPassword();
}
