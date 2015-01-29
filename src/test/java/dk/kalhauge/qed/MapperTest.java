package dk.kalhauge.qed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static dk.kalhauge.bean.Mapper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.StringStartsWith.*;
import static org.junit.Assert.*;

public class MapperTest {
  private static final Calendar calendar = GregorianCalendar.getInstance();
  
  private static Date date(int year, int month, int day) {
    calendar.clear();
    calendar.set(year, month - 1, day);
    return calendar.getTime();
    } 
  
  private final Person person = new Person("Anders", "Kalhauge", date(1959, 11, 14), new Address("Skrænten 5", new Postal(3600, "Frederikssund")));
  
  private final Collection<Person> people = new ArrayList<>();
  
  @Before
  public void setup() throws Exception {
    people.add(new Person("Anders", "Kalhauge", date(1959, 11, 14), new Address("Skrænten 5", new Postal(3600, "Frederikssund"))));
    people.add(new Person("Anna", "Kalhauge", date(1963, 10, 02), new Address("Skrænten 5", new Postal(3600, "Frederikssund"))));
    people.add(new Person("Christian Gram", "Kalhauge", date(1990, 06, 14), new Address("Kollegiebakken 9", new Postal(2800, "Kgs. Lyngby"))));
    people.add(new Person("Kristoffer Gram", "Kalhauge", date(1992, 10, 25), new Address("Rævehøjvej 7", new Postal(2800, "Kgs. Lyngby"))));    
    //people.add(new Person("Anders", "Kalhauge", date(1959, 11, 14), new Address("Skrænten 5", new Postal(3600, "Frederikssund"))));
    }

  @Test
  public void testMapPerson() throws Exception {
    StringBuffer buffer = new StringBuffer();
    String template = "Hello ${firstName}";
    map(buffer, person, template);
    assertThat(buffer.toString(), is("Hello Anders"));
    }
  
  @Test
  public void testMapPersonShort() throws Exception {
    String template = "Hello ${firstName}";
    assertThat(map(person, template), is("Hello Anders"));
    }
  
  @Test
  public void testMapPeople() throws Exception {
    String template = "(${address.postal.code})";
    String result = map(people, template);
    assertThat(result, is("(3600)(3600)(2800)(2800)"));
    }
  
  }
