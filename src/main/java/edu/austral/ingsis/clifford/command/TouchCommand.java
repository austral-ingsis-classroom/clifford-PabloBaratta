package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.structure.Directory;
import edu.austral.ingsis.clifford.structure.File;

public class TouchCommand implements Command {

  private static final String REPEATED_NAME = "There is already a file with that name";
  private final Directory pwd;
  private final String fileName;

  public TouchCommand(String fileName, Directory pwd) {
    this.pwd = pwd;
    this.fileName = fileName;
  }

  @Override
  public String execute() {
    /*
    if (checkIfPossibleDuplicates(fileName, pwd)) {
      return REPEATED_NAME;
    }*/
    File file = new File(pwd.getPath() + pwd.getName() + "/", fileName);
    pwd.deleteNode(fileName);
    pwd.addNode(file);
    return "'" + fileName + "'" + " file created";
  }
  // apparently file has to be overwritten?
  /*
  private boolean checkIfPossibleDuplicates(String fileName, Directory pwd) {

    List<SystemNode> children = pwd.list();
    boolean hasRepeatedName =
        !children.stream().filter(child -> child.getName().equals(fileName)).toList().isEmpty();

    return hasRepeatedName;
  }*/
}
