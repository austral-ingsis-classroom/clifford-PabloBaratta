package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.ErrorCommand;
import edu.austral.ingsis.clifford.command.MkdirCommand;
import edu.austral.ingsis.clifford.structure.FileSystem;
import java.util.Optional;

// could mix with File
public class MkdirParser implements CommandParser {

  private static final String INVALID_LENGTH = "Touch only has file name";
  private static final String INVALID_NAME = "Invalid Name for a file";

  @Override
  public Command parse(String options, FileSystem fs) {
    String[] split = options.strip().split(" ");
    Optional<ErrorCommand> optionalError = checkErrors(split);
    if (optionalError.isPresent()) {
      return optionalError.get();
    }
    return new MkdirCommand(split[0], fs.getPwd());
  }

  private Optional<ErrorCommand> checkErrors(String[] split) {
    if (split.length != 1) {
      return Optional.of(new ErrorCommand(INVALID_LENGTH));
    } else if (isNameInvalid(split[0])) {
      return Optional.of(new ErrorCommand(INVALID_NAME));
    }
    return Optional.empty();
  }

  private boolean isNameInvalid(String s) {
    return s.contains("/") || s.contains(" ");
  }
}
