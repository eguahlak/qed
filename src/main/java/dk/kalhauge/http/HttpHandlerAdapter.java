package dk.kalhauge.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public abstract class HttpHandlerAdapter implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    RequestHttpExchangeAdapter request = new RequestHttpExchangeAdapter(exchange);
    ResponseHttpExchangeAdapter response = new ResponseHttpExchangeAdapter(exchange);
    handle(request, response);
    }
  
  protected abstract void handle(Request request, Response response) throws IOException;
  
  }
