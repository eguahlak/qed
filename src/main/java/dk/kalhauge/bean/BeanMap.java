package dk.kalhauge.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanMap {
  private final Object bean;

  private static Method findAccessor(Object data, String name) {
    name = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
    for (Method accessor : data.getClass().getMethods()) 
        if (accessor.getName().equals(name) && accessor.getParameterCount() == 0) return accessor;
    throw new RuntimeException("No such accessor");
    }
  
  public BeanMap(Object bean) {
    this.bean = bean;
    }
  
  public String get(String key) {
    String[] parts = key.split("\\.");
    return get(bean, 0, parts);
    }

  private String get(Object data, int index, String[] parts) {
    try {
      Method accessor = findAccessor(data, parts[index]);
      Object result = accessor.invoke(data);
      index++;
      if (index == parts.length) return result.toString();
      return get(result, index, parts);
      }
    catch (IllegalAccessException | InvocationTargetException e) {
      return e.getMessage();
      }
    }
  
  public String map(String template) {
    throw new UnsupportedOperationException();
    }
  
  }
