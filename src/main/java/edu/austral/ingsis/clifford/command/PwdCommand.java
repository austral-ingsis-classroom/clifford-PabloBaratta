package edu.austral.ingsis.clifford.command;

public class PwdCommand implements Command {

  private final String message;

  public PwdCommand(String message) {
    this.message = message;
  }

  @Override
  public String execute() {
    return message;
  }
}
