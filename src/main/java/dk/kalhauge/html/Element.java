package dk.kalhauge.html;

import java.util.*;
import static dk.kalhauge.bean.Mapper.*;

public class Element extends Node {
  private Object bean = null;
  private String name;
  private final List<Attribute> attributes = new ArrayList<>();
  private final List<Node> nodes = new ArrayList<>();
  
  public Element(String name) {
    this.name = name;
    }

  public Element(String name, String text) {
    this(name);
    nodes.add(new Text(text));
    }
  
  public Element id(String value) {
    attributes.add(new Attribute("id", value));
    return this;
    }
  
  public Element klass(String value) {
    attributes.add(new Attribute("class", value));
    return this;
    }
  
  public Element add(Node... nodes) {
    for (Node node : nodes) {
      if (node instanceof Attribute) attributes.add((Attribute)node);
      else this.nodes.add(node);
      }
    return this;
    }
  
  public Element map(Object bean) {
    this.bean = bean;
    return this;
    }

  @Override
  public void print(StringBuffer buffer) {
    if (bean != null) for (Object data : iterable(Object.class, bean)) print(buffer, data);
    else {
      buffer.append("<").append(name);
      for (Attribute attribute : attributes) {
        buffer.append(" ");
        attribute.print(buffer);
        }
      buffer.append(">");
      for (Node node : nodes) {
        node.print(buffer);
        }
      buffer.append("</").append(name).append(">");
      }
    }
  
  @Override
  protected void print(StringBuffer buffer, Object data) {
    buffer.append("<").append(name);
    for (Attribute attribute : attributes) {
      buffer.append(" ");
      attribute.print(buffer, data);
      }
    buffer.append(">");
    for (Node node : nodes) {
      node.print(buffer, data);
      }
    buffer.append("</").append(name).append(">");
    }

  }
