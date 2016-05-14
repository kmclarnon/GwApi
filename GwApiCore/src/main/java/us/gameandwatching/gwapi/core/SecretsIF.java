package us.gameandwatching.gwapi.core;

import org.immutables.value.Value;

@GwStyle
@Value.Immutable
public interface SecretsIF {
  String getCookieKey();
}
