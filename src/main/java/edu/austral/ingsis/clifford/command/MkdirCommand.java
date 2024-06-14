package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.structure.Directory;
import edu.austral.ingsis.clifford.structure.SystemNode;
import java.util.List;

// could add logic to one touch/mkdir command. but the design of directory could include links to
// their parent
public class MkdirCommand implements Command {
  private static final String REPEATED_NAME = "There is already a file with that name";
  private final Directory pwd;
  private final String dirName;

  public MkdirCommand(String dirName, Directory pwd) {
    this.pwd = pwd;
    this.dirName = dirName;
  }

  @Override
  public String execute() {
    if (checkIfPossibleDuplicates(dirName, pwd)) {
      return REPEATED_NAME;
    }
    Directory dir = new Directory(pwd.getPath() + getName() + "/", dirName);
    pwd.addNode(dir);
    return "'" + dirName + "'" + " directory created";
  }

  private String getName() {
    return pwd.getName().equals("/") ? "" : pwd.getName();
  }

  private boolean checkIfPossibleDuplicates(String dirName, Directory pwd) {

    List<SystemNode> children = pwd.list();
    boolean hasRepeatedName =
        !children.stream().filter(child -> child.getName().equals(dirName)).toList().isEmpty();

    return hasRepeatedName;
  }
}
