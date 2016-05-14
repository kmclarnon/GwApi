package us.gameandwatching.gwapi.core;

import org.immutables.value.Value;

@GwStyle
@Value.Immutable
public interface ExceptionEntityIF {
  String getCode();
}
