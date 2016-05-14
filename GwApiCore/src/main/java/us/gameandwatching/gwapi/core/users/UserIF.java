package us.gameandwatching.gwapi.core.users;

import org.immutables.value.Value;

import us.gameandwatching.gwapi.core.SmashDBStyle;

@SmashDBStyle
@Value.Immutable
public interface UserIF extends UserFields {
  int getId();
}
