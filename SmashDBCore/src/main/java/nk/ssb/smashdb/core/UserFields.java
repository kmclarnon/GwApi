package nk.ssb.smashdb.core;

public interface UserFields {
  String getEmail();
  String getPasswordHash();
  int getElo();
  long getCreatedAt();
}
