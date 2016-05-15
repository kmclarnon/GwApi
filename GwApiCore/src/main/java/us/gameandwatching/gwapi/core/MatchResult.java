package us.gameandwatching.gwapi.core;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;
import com.hubspot.rosetta.annotations.RosettaCreator;
import com.hubspot.rosetta.annotations.RosettaValue;

public enum MatchResult {
  PLAYER_1_WON(1, true, false),
  PLAYER_2_WON(2, false, true),
  DRAW(3, false, false);

  private static Map<Integer, MatchResult> MATCH_RESULTS_BY_ID = Maps.uniqueIndex(Arrays.asList(values()), MatchResult::getId);

  private final int id;
  private final boolean player1Won;
  private final boolean player2Won;

  MatchResult(int id, boolean player1Won, boolean player2Won) {
    this.id = id;
    this.player1Won = player1Won;
    this.player2Won = player2Won;
  }

  public boolean getPlayer1Won() {
    return player1Won;
  }

  public boolean getPlayer2Won() {
    return player2Won;
  }

  @RosettaValue
  public int getId() {
    return id;
  }

  @RosettaCreator
  public static MatchResult fromId(int id) {
    return MATCH_RESULTS_BY_ID.get(id);
  }
}
