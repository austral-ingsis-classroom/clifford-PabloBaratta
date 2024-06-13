package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.ErrorCommand;
import edu.austral.ingsis.clifford.command.PwdCommand;
import edu.austral.ingsis.clifford.structure.*;

public class PwdParser implements CommandParser {

  @Override
  public Command parse(String options, FileSystem fs) {
    if (!options.isEmpty()) {
      return new ErrorCommand("PWD does not have options");
    }
    Directory pwd = fs.getPwd();
    return new PwdCommand(pwd.getPath() + pwd.getName());
  }
}
