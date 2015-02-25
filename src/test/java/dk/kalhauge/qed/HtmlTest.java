package dk.kalhauge.qed;

import dk.kalhauge.html.Element;
import dk.kalhauge.html.HtmlBuilder;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class HtmlTest {
  
  @Test
  public void testPTag() throws Exception {
    Element p = HtmlBuilder.p("Hello");
    assertThat(p.toString(), is("<p>Hello</p>"));
    }
  
  }