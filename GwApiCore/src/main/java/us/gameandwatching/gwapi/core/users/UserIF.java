package us.gameandwatching.gwapi.core.users;

import org.immutables.value.Value;

import us.gameandwatching.gwapi.core.GwStyle;

@GwStyle
@Value.Immutable
public interface UserIF extends UserFields {
  int getId();
}
