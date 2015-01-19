package dk.kalhauge.html;

public class Attribute extends Node {
  private final String name;
  private final String value;
  
  public Attribute(String name, String value) {
    this.name = name;
    this.value = value;
    }
  
  @Override
  public void print(StringBuilder builder) {
    builder.append(name).append("='").append(value).append("'");
    }
  
  }
