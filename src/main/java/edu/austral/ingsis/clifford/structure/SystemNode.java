package edu.austral.ingsis.clifford.structure;

public interface SystemNode /*extends Visitable*/ {
  String getName();

  String getPath();

  boolean isComposite();
}
