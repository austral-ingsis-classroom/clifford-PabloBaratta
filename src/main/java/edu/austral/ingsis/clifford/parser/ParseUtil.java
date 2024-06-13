package edu.austral.ingsis.clifford.parser;

public class ParseUtil {

  public static Tuple<String, String> parseoption(String option) {
    String[] split = option.strip().split("=", 2);
    return new Tuple<>(split[0], split[1]);
  }
}
