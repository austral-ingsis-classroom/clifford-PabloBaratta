package edu.austral.ingsis.clifford.structure;

public class File implements SystemNode {

  private String name;
  private String path;

  public File(String path, String name) {
    this.name = name;
    this.path = path;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public boolean isComposite() {
    return false;
  }
}
