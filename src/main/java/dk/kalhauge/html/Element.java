package dk.kalhauge.html;

import java.io.PrintWriter;
import java.util.*;

public class Element extends Node {
  private String name;
  private List<Attribute> attributes = new ArrayList<>();
  private List<Node> nodes = new ArrayList<>();
  
  public Element(String name) {
    this.name = name;
    }

  public Element(String name, String text) {
    this(name);
    nodes.add(new Text(text));
    }
  
  public Element add(Node... nodes) {
    for (Node node : nodes) {
      if (node instanceof Attribute) attributes.add((Attribute)node);
      else this.nodes.add(node);
      }
    return this;
    }
  
  @Override
  public void print(StringBuilder builder) {
    builder.append("<"+name);
    for (Attribute attribute : attributes) {
      builder.append(" ");
      attribute.print(builder);
      }
    builder.append(">");
    for (Node node : nodes) {
      node.print(builder);
      }
    builder.append("</").append(name).append(">");
    }
  
  }
