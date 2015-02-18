package dk.kalhauge.http;

import java.io.IOException;
import java.net.URI;

public interface Request {
  URI getURI();
  String getPath();
  StringBuffer read() throws IOException;
  }
