package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.structure.Directory;
import edu.austral.ingsis.clifford.structure.FileSystem;
import edu.austral.ingsis.clifford.structure.SystemNode;
import java.util.Arrays;
import java.util.Optional;

public class ChangeDirectoryCommand implements Command {
  private final String[] path;
  private final FileSystem fs;
  private final boolean fromRoot;

  private static final String MESSAGE = "moved to directory ";

  public ChangeDirectoryCommand(String[] path, FileSystem fs, boolean fromRoot) {
    this.path = path;
    this.fs = fs;
    this.fromRoot = fromRoot;
  }

  @Override
  public String execute() {
    return navigate(path, fs, fromRoot);
  }

  private String navigate(String[] path, FileSystem fs, boolean fromRoot) {
    Directory from = fromRoot ? fs.getRoot() : fs.getPwd();
    if (path.length == 0) {
      fs.setPwd(fs.getRoot());
    }
    for (String s : path) {
      switch (s) {
        case ".":
          return MESSAGE + from.getName();
        case "..":
          Optional<Directory> directory = findDirectory(from.getPath(), fs.getRoot());
          if (directory.isEmpty()) {
            return "Couldn't find dir";
          }
          fs.setPwd(directory.get());
          break;
        default:
          Optional<SystemNode> child = fs.getPwd().getChild(s);
          if (child.isEmpty() || !child.get().isComposite()) {
            return "'" + s + "'" + " directory does not exist";
          }
          fs.setPwd((Directory) child.get());
          break;
      }
    }
    return MESSAGE + "'" + fs.getPwd().getName() + "'";
  }

  private Optional<Directory> findDirectory(String path, Directory root) {
    if (path.isEmpty() || path.equals("/")) {
      return Optional.of(root);
    }
    String[] split = path.split("/");
    String childName = split[0];
    Optional<SystemNode> child = root.getChild(childName);
    if (child.isEmpty() || !child.get().isComposite()) {
      return Optional.empty();
    }
    String[] subArray = Arrays.copyOfRange(split, 1, split.length);
    return findDirectory(String.join(" ", subArray), (Directory) child.get());
  }
}
