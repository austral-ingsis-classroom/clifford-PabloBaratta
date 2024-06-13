package edu.austral.ingsis.clifford.structure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {

  private final Comparator<SystemNode> cmp;

  public Sorter(Comparator<SystemNode> cmp) {
    this.cmp = cmp;
  }

  public List<SystemNode> sort(List<SystemNode> nodes) {
    ArrayList<SystemNode> systemNodes = new ArrayList<>(nodes);
    systemNodes.sort(cmp);
    return systemNodes;
  }
}
