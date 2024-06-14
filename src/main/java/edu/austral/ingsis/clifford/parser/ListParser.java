package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.ErrorCommand;
import edu.austral.ingsis.clifford.command.ListCommand;
import edu.austral.ingsis.clifford.structure.FileSystem;
import edu.austral.ingsis.clifford.structure.Sorter;
import edu.austral.ingsis.clifford.util.ComparatorByName;
import java.util.Map;

public class ListParser implements CommandParser {

  public static final String PARAM_ERROR = "ls hast at most one option: --ord=asc | --ord=desc";
  public static final String NULL_OPTION_PARAM = "";

  private static final Map<String, Sorter> sorterMap =
      Map.of(
          "asc",
          new Sorter(new ComparatorByName()),
          "desc",
          new Sorter(new ComparatorByName().reversed()));

  @Override
  public Command parse(String options, FileSystem fs) {
    String[] stripOptions = options.strip().split(" ");
    if (stripOptions.length > 1) { // could change
      return new ErrorCommand(PARAM_ERROR);
    } else if (stripOptions.length == 1 && !stripOptions[0].isEmpty()) {
      Tuple<String, String> parseOption = ParseUtil.parseoption(stripOptions[0]);
      if (notValidOption(parseOption)) {
        return new ErrorCommand(PARAM_ERROR);
      }
      return new ListCommand(fs.getPwd(), sorterMap.get(parseOption.getValue()));
    }
    return new ListCommand(fs.getPwd());
  }

  private static boolean notValidOption(Tuple<String, String> parseoption) {
    return !parseoption.getKey().startsWith("--ord")
        || !sorterMap.containsKey(parseoption.getValue());
  }
}
