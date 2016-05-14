package us.gameandwatching.gwapi.core;

public interface UserFields {
  String getEmail();
  String getPasswordHash();
  int getElo();
  long getCreatedAt();
}
