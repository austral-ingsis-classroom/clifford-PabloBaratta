package edu.austral.ingsis.clifford.structure;

public interface Visitor<T> {

  T visit(Directory dir);

  T visit(File dir);
}
