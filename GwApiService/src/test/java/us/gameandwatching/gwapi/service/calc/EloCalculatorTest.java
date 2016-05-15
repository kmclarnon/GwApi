package us.gameandwatching.gwapi.service.calc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import us.gameandwatching.gwapi.core.EloPair;
import us.gameandwatching.gwapi.core.MatchResult;

public class EloCalculatorTest {

  @Test
  public void itCalculatesPlayer1WinsCorrectly() {
    EloCalculator eloCalculator = new EloCalculator();

    EloPair initialPair = EloPair.builder()
        .setPlayer1Rating(2400)
        .setPlayer2Rating(2000)
        .setResult(MatchResult.PLAYER_1_WON)
        .build();

    EloPair result = eloCalculator.updateEloRating(initialPair);

    assertThat(Math.round(result.getPlayer1Rating())).isEqualTo(2401);
    assertThat(Math.round(result.getPlayer2Rating())).isEqualTo(1997);
  }

  @Test
  public void itCalculatesPlayer2WinsCorrectly() {
    EloCalculator eloCalculator = new EloCalculator();

    EloPair initialPair = EloPair.builder()
        .setPlayer1Rating(2400)
        .setPlayer2Rating(2000)
        .setResult(MatchResult.PLAYER_2_WON)
        .build();

    EloPair result = eloCalculator.updateEloRating(initialPair);

    assertThat(Math.round(result.getPlayer1Rating())).isEqualTo(2385);
    assertThat(Math.round(result.getPlayer2Rating())).isEqualTo(2029);
  }

  @Test
  public void itCalculatesDrawsWinsCorrectly() {
    EloCalculator eloCalculator = new EloCalculator();

    EloPair initialPair = EloPair.builder()
        .setPlayer1Rating(2400)
        .setPlayer2Rating(2000)
        .setResult(MatchResult.DRAW)
        .build();

    EloPair result = eloCalculator.updateEloRating(initialPair);

    assertThat(Math.round(result.getPlayer1Rating())).isEqualTo(2393);
    assertThat(Math.round(result.getPlayer2Rating())).isEqualTo(2013);
  }

}
