package dk.kalhauge.qed;

import dk.kalhauge.bean.BeanMap;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.StringStartsWith.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class BeanMapTest {
  private BeanMap map;
  private static Calendar calendar = GregorianCalendar.getInstance();
  
  public BeanMapTest() { }
  
  @Before
  public void setUp() throws Exception {
    calendar.set(1990, 02, 27);
    map = new BeanMap(
        new Person(
            "Sonja",
            "Hansen",
            calendar.getTime(),
            new Address(
                "Hjemmesiden 7",
                new Postal(9999, "Kragernes")
                )
            )
        );
    }
  
  @Test
  public void testSimpleGet() throws Exception {
    String result = map.get("name");
    assertThat(result, is("Sonja Hansen"));
    }
  
  @Test
  public void testSimpleGetOther() throws Exception {
    String result = map.get("dateOfBirth");
    assertThat(result, startsWith("Tue Mar 27"));
    }

  @Test
  public void testSemiComplexGet() throws Exception {
    String result = map.get("address");
    assertThat(result, is("Hjemmesiden 7, 9999  Kragernes"));
    }

  @Test
  public void testMoreComplexGet() throws Exception {
    String result = map.get("address.postal");
    assertThat(result, is("9999  Kragernes"));
    }

  @Test
  public void testComplexGet() throws Exception {
    String result = map.get("address.postal.district");
    assertThat(result, is("Kragernes"));
    }

  }
