package dk.kalhauge.html;

public class HtmlBuilder {
  
  public static Element html(Element head, Element body) {
    Element html = new Element("html");
    html.add(head, body);
    return html;
    }
  
  public static Element head(String title, Node... nodes) {
    return new Element("head")
        .add(new Element("title", title))
        .add(new Element("script").add(new Attribute("src", "/qed-query")))
        .add(nodes);
    }
  
  public static Element stylesheet(String href) {
    return new Element("style")
        .add(
            new Attribute("href", href),
            new Attribute("type", "text/css"),
            new Attribute("rel", "stylesheet")
        );
    }
  
  public static Element body(Node... nodes) {
    Element body = new Element("body");
    body.add(nodes);
    return body;
    }
  
  public static Element h(int number, String text) {
    Element headline = new Element("h"+number, text);
    return headline;
    }
  
  public static Element p(String text) {
    Element paragraph = new Element("p", text);
    return paragraph;
    }
  
  public static Element table(Element... rows) {
    Element table = new Element("table");
    table.add(new Attribute("border", "1"));
    table.add(rows);
    return table;
    }
  
  public static Element row(Element... cells) {
    Element row = new Element("tr");
    row.add(cells);
    return row;
    }
  
  public static Element cell(String text) {
    return new Element("td", text);
    }

  }
