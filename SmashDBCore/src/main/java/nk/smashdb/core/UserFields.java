package nk.smashdb.core;

public interface UserFields {
  String getEmail();
  String getPasswordHash();
  int getElo();
  long getCreatedAt();
}
