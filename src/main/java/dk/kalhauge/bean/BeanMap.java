package dk.kalhauge.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanMap {
  private static final Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
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

  private static String get(Object data, int index, String[] parts) {
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
  
  
  private static String get(Object data, String key) {
    String[] parts = key.split("\\.");
    return get(data, 0, parts);
    }

  private void mapSingle(StringBuffer buffer, Object data, CharSequence template) {
    Matcher matcher = pattern.matcher(template);
    while (matcher.find()) matcher.appendReplacement(buffer, get(data, matcher.group(1)));
    matcher.appendTail(buffer);
    }
  
  public String map(CharSequence template) {
    StringBuffer buffer = new StringBuffer();
    if (bean.getClass().isArray()) for (Object i : (Object[])bean) mapSingle(buffer, i, template);
    else if (bean instanceof Iterable) for (Object i : (Iterable)bean) mapSingle(buffer, i, template);
    else mapSingle(buffer, bean, template);
    return buffer.toString();
    }
  
//  public static <N extends Node> N map(Object bean, N template) {
//    StringBuffer buffer = new StringBuffer();
//    if (bean.getClass().isArray()) for (Object i : (Object[])bean) mapSingle(buffer, i, template);
//    else if (bean instanceof Iterable) for (Object i : (Iterable)bean) mapSingle(buffer, i, template);
//    else mapSingle(buffer, bean, template);
//    return buffer.toString();
//    }
  
  }
