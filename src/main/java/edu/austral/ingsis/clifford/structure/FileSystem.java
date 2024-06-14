package edu.austral.ingsis.clifford.structure;

public class FileSystem {
  private Directory root;

  public Directory getRoot() {
    return root;
  }

  public void setRoot(Directory root) {
    this.root = root;
  }

  public Directory getPwd() {
    return pwd;
  }

  public void setPwd(Directory pwd) {
    this.pwd = pwd;
  }

  private Directory pwd;

  public FileSystem(Directory root) {
    this.root = root;
    this.pwd = root;
  }
}
