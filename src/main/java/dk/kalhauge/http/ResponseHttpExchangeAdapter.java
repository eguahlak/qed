package dk.kalhauge.http;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class ResponseHttpExchangeAdapter implements Response {
  private final HttpExchange exchange;
  private boolean headerSent = false;
  private StringBuffer buffer = null;
          
  public ResponseHttpExchangeAdapter(HttpExchange exchange) {
    this.exchange = exchange;
    }

  @Override
  public void addHeader(String name, String value) {
    exchange.getResponseHeaders().add(name, value);
    }

  @Override
  public void length(int length) throws IOException {
    exchange.sendResponseHeaders(200, length);
    headerSent = true;
    }

//  @Override
//  public void write(CharSequence text) {
//    if (buffer == null) buffer = new StringBuffer(text);
//    else buffer.append(text);
//    }
//
  @Override
  public void write(byte[] buffer) throws IOException {
    if (!headerSent) {
      length(buffer.length);
      exchange.getResponseBody().write(buffer);
      exchange.getResponseBody().close();
      }
    else exchange.getResponseBody().write(buffer);
    }

  public void send() throws IOException {
    exchange.getResponseBody().close();
    }
  
  }
