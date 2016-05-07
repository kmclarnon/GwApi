package nk.ssb.smashdb.core;

import org.immutables.value.Value;

@SmashDBStyle
@Value.Immutable
public interface UserIF extends UserFields {
  int getId();
}
