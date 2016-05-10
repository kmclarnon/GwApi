package nk.ssb.smashdb.core;

import org.immutables.value.Value;

@SmashDBStyle
@Value.Immutable
public interface ExceptionEntityIF {
  String getCode();
}
