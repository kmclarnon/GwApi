package nk.ssb.smashdb.core;

import org.immutables.value.Value;

@SmashDBStyle
@Value.Immutable
public interface SecretsIF {
  String getCookieKey();
}
