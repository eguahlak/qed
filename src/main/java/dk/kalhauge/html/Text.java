package dk.kalhauge.html;

import dk.kalhauge.bean.Mapper;

public class Text extends Node {
  private final String value;
  
  public Text(String value) {
    this.value = value;
    }

  @Override
  public void print(StringBuffer buffer) {
    buffer.append(value);
    }
  
  @Override
  protected void print(StringBuffer buffer, Object bean) {
    Mapper.map(buffer, bean, value);
    }
  
  }
