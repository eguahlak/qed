package dk.kalhauge.bean;

import java.util.Collection;

public interface BeanItem {
  BeanItem get(String key);
  int size();
  Collection<BeanItem> children();
  
  }
