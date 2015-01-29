package dk.kalhauge.html;

import dk.kalhauge.bean.Mapper;

public class Attribute extends Node {
  private final String name;
  private final String value;
  
  public Attribute(String name, String value) {
    this.name = name;
    this.value = value;
    }

  @Override
  public void print(StringBuffer buffer) {
    buffer.append(name).append("='").append(value).append("'");
    }
  
  @Override
  protected void print(StringBuffer buffer, Object bean) {
    buffer.append(name).append("='");
    Mapper.map(buffer, bean, value).append("'");
    }

  }
