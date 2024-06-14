package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.structure.Directory;
import edu.austral.ingsis.clifford.structure.SystemNode;
import java.util.Optional;

public class RemoveCommand implements Command {

  public static final String NOT_EXIST = "This file does not exist";
  private final String nodeName;
  private final boolean recursive;
  private final Directory currentDir;

  public RemoveCommand(Directory currentDir, String nodeName, boolean recursive) {
    this.nodeName = nodeName;
    this.recursive = recursive;
    this.currentDir = currentDir;
  }

  @Override
  public String execute() {
    Optional<SystemNode> optionalChild = currentDir.getChild(nodeName);
    if (optionalChild.isEmpty()) {
      return NOT_EXIST;
    }

    SystemNode child = optionalChild.get();
    boolean isComposite = child.isComposite();

    if ((recursive && isComposite) || (!recursive && !isComposite)) {
      currentDir.deleteNode(nodeName);
      return "'" + nodeName + "' removed";
    }

    return "cannot remove '" + nodeName + "', is a " + (isComposite ? "directory" : "file");
  }
}
