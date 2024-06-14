package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.ErrorCommand;
import edu.austral.ingsis.clifford.command.RemoveCommand;
import edu.austral.ingsis.clifford.structure.FileSystem;
import java.util.*;
import java.util.stream.Stream;

public class RemoveParser implements CommandParser {

  public static final String INVALID_LENGHT = "rm has at most options and node to create";
  public static final String NON_VALID_OPTION = "Non valid option";
  public static final String ERROR = "Cannot construct rm with given parameters";

  // Some are thought to be expanded later
  @Override
  public Command parse(String options, FileSystem fs) {
    String[] split = options.strip().split(" ");
    if (split.length > 2) {
      return new ErrorCommand(INVALID_LENGHT);
    } else if (split.length == 1) {
      return new RemoveCommand(fs.getPwd(), split[0], false);
    }
    Map<String, Optional<String>> keys = findOptions(split);

    // if there could be new options in the future
    for (Map.Entry<String, Optional<String>> stringOptionalEntry : keys.entrySet()) {
      switch (stringOptionalEntry.getKey()) {
        case "--recursive":
          return new RemoveCommand(fs.getPwd(), split[1], true);
        default:
          return new ErrorCommand(NON_VALID_OPTION);
      }
    }
    return new ErrorCommand(ERROR);
  }

  private Map<String, Optional<String>> findOptions(String[] lines) {
    HashMap<String, Optional<String>> map = new HashMap<>();
    Stream<String> optionsStream = Arrays.stream(lines).filter(line -> line.startsWith("--"));
    Stream<Tuple<String, String>> tupleStream = optionsStream.map(ParseUtil::parseoption);
    tupleStream.forEach(tuple -> addToMap(tuple, map));
    return map;
  }

  private void addToMap(Tuple<String, String> tuple, Map<String, Optional<String>> map) {
    if (Objects.equals(tuple.getValue(), "")) {
      map.put(tuple.getKey(), Optional.empty());
    } else {
      map.put(tuple.getKey(), Optional.of(tuple.getValue()));
    }
  }
}
