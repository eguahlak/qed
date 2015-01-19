package dk.kalhauge.html;

import java.io.PrintWriter;

public class Text extends Node {
  private final String value;
  
  public Text(String value) {
    this.value = value;
    }

  @Override
  public void print(StringBuilder builder) {
    builder.append(value);
    }
  
  }
