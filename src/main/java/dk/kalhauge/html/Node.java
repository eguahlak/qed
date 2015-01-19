package dk.kalhauge.html;

public abstract class Node {

  public abstract void print(StringBuilder builder);

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    print(builder);
    return builder.toString();
    }
  
  }
