package dk.kalhauge.qed;

import dk.kalhauge.http.Request;
import dk.kalhauge.http.Response;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;



public class QedHandlerTest {
  final Mockery context = new JUnitRuleMockery();
  
  @Test
  public void testBuildScript() throws Exception {
    final Request request = context.mock(Request.class);
    final Response response = context.mock(Response.class);
    final TestFacade facade = new TestFacade();
    
    QedHandler handler = new QedHandler(facade);
    
    context.checking(new Expectations() {{
      oneOf(request).getPath(); will(returnValue("/qed"));
      oneOf(response).addHeader("Content-Type", "text/javascript; charset=utf8");
      oneOf(response).write(with(any(byte[].class)));
      }});
    
    handler.handle(request, response);
    
  
    }
  
  
  
  
}
