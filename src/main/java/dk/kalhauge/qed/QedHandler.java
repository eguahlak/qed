package dk.kalhauge.qed;

import dk.kalhauge.http.HttpHandlerAdapter;
import dk.kalhauge.http.Request;
import dk.kalhauge.http.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QedHandler extends HttpHandlerAdapter {
  public final JsonWrapper wrapper;
  
  public QedHandler(Object facade) {
    this.wrapper = new JsonWrapper(facade);
    }
  
  private void appendResource(StringBuilder result, String name) throws IOException {
    String path = getClass().getResource(name).getPath();
    try (BufferedReader in = new BufferedReader(new FileReader(path))) {
      String line = in.readLine();
      while (line != null) {
        result.append(line).append("\n");
        line = in.readLine();
        }
      }
    }
  
  private void buildScript(Response response, boolean jQuery) throws IOException {
    StringBuilder result = new StringBuilder("// version 0.9.1\n");
    // Class contract = wrapper.getClass();
    if (jQuery) appendResource(result, "/jquery.js");
    appendResource(result, "/qed.js");
    for (JsonWrapper.Operation method : wrapper.getOperations()) {
      result.append(method.toJavaScriptDefinition());
      result.append(" { return new Executor");
      result.append(method.toExecutorDefinition());
      result.append("; }\n");
      }
    response.addHeader("Content-Type", "text/javascript; charset=utf8");
    byte[] buffer = result.toString().getBytes("UTF-8");
    response.write(buffer);
    }
  
  @Override
  protected void handle(Request request, Response response) throws IOException {
    String resource = request.getPath();
    StringBuilder result = new StringBuilder();
    if ("/qed".equals(resource)) buildScript(response, false);
    else if ("/qed-query".equals(resource)) buildScript(response, true);
    else {
      String name = resource.substring(5);
      System.out.println(name);
      String line = request.read().toString();

      System.out.println("  <- "+line);
      String json = wrapper.call(name, line);
      System.out.println("  -> "+json);
      result.append(json);
      response.addHeader("Content-Type", "application/json; charset=utf8");
      byte[] buffer = result.toString().getBytes("UTF-8");
      response.write(buffer);
      }
    }
  
  }
