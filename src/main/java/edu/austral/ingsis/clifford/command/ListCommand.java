package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.structure.Directory;
import edu.austral.ingsis.clifford.structure.Sorter;
import edu.austral.ingsis.clifford.structure.SystemNode;

import java.util.stream.Collectors;

public class ListCommand implements Command {

  private final Directory dir;
  private final Sorter sorter;

  public ListCommand(Directory dir, Sorter sorter) {
    this.dir = dir;
    this.sorter = sorter;
  }

  public ListCommand(Directory dir) {
    this.dir = dir;
    this.sorter = null;
  }

  @Override
  public String execute() {
    String string =
        sorter != null
            ? sorter.sort(dir.list()).stream().map(SystemNode::getName).collect(Collectors.toList()).toString()
            : dir.list().stream().map(SystemNode::getName).collect(Collectors.toList()).toString();
    return string.substring(1, string.length() - 1).replace(",", "");
  }
}
