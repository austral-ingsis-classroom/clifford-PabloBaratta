package edu.austral.ingsis.clifford.structure;

import java.util.*;

public class Directory implements SystemNode {

  private String name;
  private String path;
  private final Map<String, SystemNode> nodes;

  public Directory(String path, String name) {
    this.name = name;
    this.path = path;
    this.nodes = new HashMap<>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getPath() {
    return this.path;
  }

  @Override
  public boolean isComposite() {
    return true;
  }

  public void addNode(SystemNode node) {
    nodes.put(node.getName(), node);
  }

  public void deleteNode(String nodeName) {
    nodes.remove(nodeName);
  }

  public Optional<SystemNode> getChild(String name) {
    return Optional.ofNullable(nodes.get(name));
  }

  public List<SystemNode> list() {
    return new ArrayList<>(nodes.values());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Directory directory = (Directory) o;
    return Objects.equals(name, directory.name) && Objects.equals(path, directory.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, path);
  }
}
