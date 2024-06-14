package edu.austral.ingsis.clifford.structure;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.parser.CommandParser;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner implements FileSystemRunner {

  public static final String COMMAND_NOT_FOUND = "Command Not Found";
  private final FileSystem fileSystem;
  private final Map<String, CommandParser> commands;

  public Runner(FileSystem fileSystem, Map<String, CommandParser> commands) {
    this.fileSystem = fileSystem;
    this.commands = commands;
  }

  public List<String> executeCommands(List<String> commands) {
    return commands.stream().map(this::executeCommand).collect(Collectors.toList());
  }

  public String executeCommand(String line) {
    String commandKey = getCommand(line);

    CommandParser commandParser = commands.get(commandKey);
    if (commandParser == null) {
      return COMMAND_NOT_FOUND;
    }

    String optionsAndParams = getOptionsAndParams(line);

    Command command = commandParser.parse(optionsAndParams, fileSystem);

    return command.execute();
  }

  private String getOptionsAndParams(String line) {
    String[] words = line.split(" ");
    String[] subArray = Arrays.copyOfRange(words, 1, words.length);
    return String.join(" ", subArray);
  }

  private String getCommand(String line) {
    return line.split(" ")[0];
  }
}
