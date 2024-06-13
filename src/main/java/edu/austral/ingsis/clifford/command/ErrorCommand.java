package edu.austral.ingsis.clifford.command;

public class ErrorCommand implements Command {

  private final String message;

  public ErrorCommand(String message) {
    this.message = message;
  }

  @Override
  public String execute() {
    return message;
  }
}
