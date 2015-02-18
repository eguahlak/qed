package dk.kalhauge.http;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class RequestHttpExchangeAdapter implements Request {
  private final HttpExchange exchange;

  public RequestHttpExchangeAdapter(HttpExchange exchange) {
    this.exchange = exchange;
    }

  @Override
  public URI getURI() {
    return exchange.getRequestURI();
    }

  @Override
  public String getPath() {
    return getURI().getPath();
    }

  @Override
  public StringBuffer read() throws IOException {
    StringBuffer buffer = new StringBuffer();
    try (InputStream in = exchange.getRequestBody()) {
      byte[] bytes = new byte[1024];
      for (int n = in.read(bytes); n > 0; n = in.read(bytes))
          buffer.append(new String(bytes, 0, n, "UTF-8"));
      }
    return buffer;
    }
  
  }
