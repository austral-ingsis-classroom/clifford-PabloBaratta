package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.ChangeDirectoryCommand;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.ErrorCommand;
import edu.austral.ingsis.clifford.structure.FileSystem;

public class ChangeDirectoryParser implements CommandParser {

  public static final String PARAM_ERROR = "CD must have one param";

  @Override
  public Command parse(String options, FileSystem fs) {
    String[] params = options.split(" ");

    if (params.length != 1) {
      return new ErrorCommand(PARAM_ERROR);
    }
    boolean fromRoot = params[0].strip().charAt(0) == '/';
    String[] path = params[0].split("/");

    return new ChangeDirectoryCommand(path, fs, fromRoot);
  }
}
