package nk.smashdb.service.daos;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.hubspot.rosetta.databind.AutoDiscoveredModule;

public class LowerCaseWithUnderscoresModule extends AutoDiscoveredModule {

  private static final String MODULE_NAME = "LowerCaseWithUnderscores";
  private static final Version VERSION = new Version(1, 0, 0, "SNAPSHOT", "nk.smashdb", "SmashDBService");

  @Override
  public String getModuleName() {
    return MODULE_NAME;
  }

  @Override
  public Version version() {
    return VERSION;
  }

  @Override
  public void setupModule(SetupContext context) {
    context.setNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
  }
}
