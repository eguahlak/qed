package dk.kalhauge.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper {
  private static final Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");

  private static Method findAccessor(Object data, String name) {
    name = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
    for (Method accessor : data.getClass().getMethods()) 
        if (accessor.getName().equals(name) && accessor.getParameterCount() == 0) return accessor;
    throw new RuntimeException("No such accessor");
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

  public static StringBuffer map(StringBuffer buffer, Object bean, CharSequence template) {
    if (bean == null) buffer.append(template);
    else if (bean.getClass().isArray()) for (Object i : (Object[])bean) map(buffer, i, template);
    else if (bean instanceof Iterable) for (Object i : (Iterable)bean) map(buffer, i, template);
    else {
      Matcher matcher = pattern.matcher(template);
      while (matcher.find()) matcher.appendReplacement(buffer, get(bean, matcher.group(1)));
      matcher.appendTail(buffer);
      }
    return buffer;
    }

  public static String map(Object bean, CharSequence template) {
    return map(new StringBuffer(), bean, template).toString();
    }
  
  public static <T> Iterable<T> iterable(Class<T> type, Object object) {
    if (object == null) return Collections.emptySet();
    if (object instanceof Iterable) return (Iterable<T>)object;
    if (object.getClass().isArray()) return new ArrayIterable<>((T[])object);
    return Collections.singleton((T)object);
    }
  
  private static class ArrayIterable<T> implements Iterable<T> {
    private final T[] array;

    public ArrayIterable(T[] array) {
      this.array = array;
      }

    @Override
    public Iterator<T> iterator() {
      return new Iterator<T>() {
        private int index = 0;
        
        @Override
        public boolean hasNext() { return index < array.length; }

        @Override
        public T next() { return array[index++]; }
        
        };
      }
    
    }
  
  }
