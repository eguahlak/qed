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
  
  @Test
  public void testHTag() throws Exception {
    Element p = HtmlBuilder.h(2, "Hello");
    assertThat(p.toString(), is("<h2>Hello</h2>"));
    }
  
  @Test
  public void testCellTag() throws Exception {
    Element p = HtmlBuilder.cell("Hello");
    assertThat(p.toString(), is("<td>Hello</td>"));
    }
  
  }
