package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.structure.FileSystem;

public interface CommandParser {
  Command parse(String options, FileSystem fs);
}
