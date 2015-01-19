package dk.kalhauge.html;

public class HtmlBuilder {
  
  public static Element html(Element head, Element body) {
    Element html = new Element("html");
    html.add(head, body);
    return html;
    }
  
  public static Element head(String title) {
    Element head = new Element("head");
    head.add(new Element("title", title));
    return head;
    }
  
  public static Element body(Node... nodes) {
    Element body = new Element("body");
    body.add(nodes);
    return body;
    }
  
  public static Element headline(int number, String text) {
    Element headline = new Element("h"+number, text);
    return headline;
    }
  
  public static Element paragraph(String text) {
    Element paragraph = new Element("p", text);
    return paragraph;
    }
  
  }
