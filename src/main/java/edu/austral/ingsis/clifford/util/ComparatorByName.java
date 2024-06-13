package edu.austral.ingsis.clifford.util;

import edu.austral.ingsis.clifford.structure.SystemNode;
import java.util.Comparator;

public class ComparatorByName implements Comparator<SystemNode> {
  @Override
  public int compare(SystemNode o1, SystemNode o2) {
    return o1.getName().compareTo(o2.getName());
  }
}
