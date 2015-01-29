package dk.kalhauge.html;

public abstract class Node {

  protected abstract void print(StringBuffer buffer, Object bean);

  public abstract void print(StringBuffer buffer);
  
  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    print(buffer);
    return buffer.toString();
    }

  }
