package us.gameandwatching.gwapi.core.users;

public interface UserFields {
  String getEmail();
  String getUsername();
  String getPasswordHash();
  String getPasswordSalt();
  int getElo();
  long getCreatedAt();
}
