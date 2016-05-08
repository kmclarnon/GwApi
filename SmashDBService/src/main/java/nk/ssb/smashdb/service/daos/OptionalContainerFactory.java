package nk.ssb.smashdb.service.daos;

import org.skife.jdbi.v2.ContainerBuilder;
import org.skife.jdbi.v2.tweak.ContainerFactory;

import java.util.Optional;

public class OptionalContainerFactory implements ContainerFactory<Optional<?>> {

  @Override
  public boolean accepts(Class<?> type) {
    return type == Optional.class;
  }

  @Override
  public ContainerBuilder<Optional<?>> newContainerBuilderFor(Class<?> type) {
    return new OptionalContainerBuilder();
  }

  private static class OptionalContainerBuilder implements ContainerBuilder<Optional<?>> {
    Optional<?> optional = Optional.empty();

    @Override
    public ContainerBuilder<Optional<?>> add(Object it) {
      optional = Optional.ofNullable(it);
      return this;
    }

    @Override
    public Optional<?> build() {
      return optional;
    }
  }
}
