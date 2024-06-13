package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.structure.Directory;
import edu.austral.ingsis.clifford.structure.Sorter;
import edu.austral.ingsis.clifford.structure.SystemNode;

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
    if (sorter != null) {
      return sorter.sort(dir.list()).stream().map(SystemNode::getName).toList().toString();
    }
    return dir.list().stream().map(SystemNode::getName).toList().toString();
  }
}
