package dk.kalhauge.http;

import java.io.IOException;

public interface Response {
  void addHeader(String name, String value);
  void length(int lenght) throws IOException;
  // void write(CharSequence text);
  void write(byte[] buffer) throws IOException;
  }
