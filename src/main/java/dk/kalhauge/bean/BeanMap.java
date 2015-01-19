package dk.kalhauge.bean;

import java.util.Collection;

public class BeanMap implements BeanItem {
  private final Object bean;

  public BeanMap(Object bean) {
    this.bean = bean;
    }
  
  @Override
  public BeanItem get(String key) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  @Override
  public int size() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  @Override
  public Collection<BeanItem> children() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  }
