package io.vertx.ext.web.client;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class URITemplateTest {

  @Test
  public void testSimpleParam() {
    URITemplate template = URITemplate.parse("/{x}");
    assertEquals(
      "/1",
      template.expand(URIParameters.create().param("x", 1))
    );
  }

  @Test
  public void testArraySimpleParam() {
    URITemplate template = URITemplate.parse("/{x}");
    assertEquals(
      "/1,2,3",
      template.expand(
        URIParameters.create().param("x", Arrays.asList("1", "2", "3"))
      )
    );
  }

  @Test
  public void testArrayExpandSimpleParam() {
    URITemplate template = URITemplate.parse("/{x*}");
    assertEquals(
      "/1,2,3",
      template.expand(
        URIParameters.create().param("x", Arrays.asList("1", "2", "3"))
      )
    );
  }

  @Test
  public void testObjectSimpleParam() {
    URITemplate template = URITemplate.parse("/{x}");
    assertEquals(
      "/a,1,b,2",
      template.expand(
        URIParameters.create().param("x", new JsonObject().put("a", 1).put("b", 2))
      )
    );
  }

  @Test
  public void testObjectExpandSimpleParam() {
    URITemplate template = URITemplate.parse("/{x*}");
    assertEquals(
      "/a=1,b=2",
      template.expand(
        URIParameters.create().param("x", new JsonObject().put("a", 1).put("b", 2))
      )
    );
  }

  @Test
  public void testAllSimpleParam() {
    URITemplate template = URITemplate.parse("/{hello,coordinates,color*}");
    assertEquals(
      "/francesco,10,20,r=1,g=2,b=1",
      template.expand(
        URIParameters.create()
          .param("hello", "francesco")
          .param("coordinates", new JsonArray().add("10").add("20"))
          .param("color", new JsonObject().put("r", 1).put("g", 2).put("b", 1))
      )
    );
  }

  @Test
  public void testPathParam() {
    URITemplate template = URITemplate.parse("{/x}");
    assertEquals(
      "/1",
      template.expand(URIParameters.create().param("x", 1))
    );
  }

  @Test
  public void testPrependPostpendPathParam() {
    URITemplate template = URITemplate.parse("/api{/x}/bla");
    assertEquals(
      "/api/1/bla",
      template.expand(URIParameters.create().param("x", 1))
    );
  }

  @Test
  public void testArrayPathParam() {
    URITemplate template = URITemplate.parse("{/x}");
    assertEquals(
      "/1,2,3",
      template.expand(
        URIParameters.create().param("x", Arrays.asList("1", "2", "3"))
      )
    );
  }

  @Test
  public void testArrayExpandPathParam() {
    URITemplate template = URITemplate.parse("{/x*}");
    assertEquals(
      "/1/2/3",
      template.expand(
        URIParameters.create().param("x", Arrays.asList("1", "2", "3"))
      )
    );
  }

  @Test
  public void testObjectPathParam() {
    URITemplate template = URITemplate.parse("{/x}");
    assertEquals(
      "/a,1,b,2",
      template.expand(
        URIParameters.create().param("x", new JsonObject().put("a", 1).put("b", 2))
      )
    );
  }

  @Test
  public void testObjectExpandPathParam() {
    URITemplate template = URITemplate.parse("{/x*}");
    assertEquals(
      "/a=1/b=2",
      template.expand(
        URIParameters.create().param("x", new JsonObject().put("a", 1).put("b", 2))
      )
    );
  }

  @Test
  public void testAllPathParam() {
    URITemplate template = URITemplate.parse("{/hello,coordinates,color*}");
    assertEquals(
      "/francesco/10,20/r=1/g=2/b=1",
      template.expand(
        URIParameters.create()
          .param("hello", "francesco")
          .param("coordinates", new JsonArray().add("10").add("20"))
          .param("color", new JsonObject().put("r", 1).put("g", 2).put("b", 1))
      )
    );
  }

  @Test
  public void testQueryParam() {
    URITemplate template = URITemplate.parse("/path{?x}");
    assertEquals(
      "/path?x=1",
      template.expand(URIParameters.create().param("x", 1))
    );
  }

  @Test
  public void testPostpendQueryParam() {
    URITemplate template = URITemplate.parse("/api/bla{?x}");
    assertEquals(
      "/api/bla?x=1",
      template.expand(URIParameters.create().param("x", 1))
    );
  }

  @Test
  public void testArrayQueryParam() {
    URITemplate template = URITemplate.parse("/{?x*}");
    assertEquals(
      "/x=1,2,3",
      template.expand(
        URIParameters.create().param("x", Arrays.asList("1", "2", "3"))
      )
    );
  }

  @Test
  public void testArrayExpandQueryParam() {
    URITemplate template = URITemplate.parse("/{?x*}");
    assertEquals(
      "/?x=1&x=2&x=3",
      template.expand(
        URIParameters.create().param("x", Arrays.asList("1", "2", "3"))
      )
    );
  }

  @Test
  public void testObjectQueryParam() {
    URITemplate template = URITemplate.parse("/{?x}");
    assertEquals(
      "/?x=a,1,b,2",
      template.expand(
        URIParameters.create().param("x", new JsonObject().put("a", 1).put("b", 2))
      )
    );
  }

  @Test
  public void testObjectExpandQueryParam() {
    URITemplate template = URITemplate.parse("/{?x*}");
    assertEquals(
      "/?a=1&b=2",
      template.expand(
        URIParameters.create().param("x", new JsonObject().put("a", 1).put("b", 2))
      )
    );
  }

  @Test
  public void testAllQueryParam() {
    URITemplate template = URITemplate.parse("/{?hello,coordinates,color*}");
    assertEquals(
      "/?hello=francesco&coordinates=10,20&r=1&g=2&b=1",
      template.expand(
        URIParameters.create()
          .param("hello", "francesco")
          .param("coordinates", new JsonArray().add("10").add("20"))
          .param("color", new JsonObject().put("r", 1).put("g", 2).put("b", 1))
      )
    );
  }

  @Test
  public void testAllQueryWithAmpersandParam() {
    URITemplate template = URITemplate.parse("/{?hello,coordinates,color*}&bla=aaa{&secondColor}");
    assertEquals(
      "/?hello=francesco&coordinates=10,20&r=1&g=2&b=1&secondColor=r,255,g,255,b,255",
      template.expand(
        URIParameters.create()
          .param("hello", "francesco")
          .param("coordinates", new JsonArray().add("10").add("20"))
          .param("color", new JsonObject().put("r", 1).put("g", 2).put("b", 1))
          .param("secondColor", new JsonObject().put("r", 255).put("g", 255).put("b", 255))
      )
    );
  }

  @Test
  public void testCompleteURITemplate() {
    URITemplate template = URITemplate.parse("/{a}{/b,c,d}{?hello,coordinates,color*}&bla=aaa{&secondColor}");
    assertEquals(
      "/1/2/3/4,5/?hello=francesco&coordinates=10,20&r=1&g=2&b=1&secondColor=r,255,g,255,b,255",
      template.expand(
        URIParameters.create()
          .param("a", 1)
          .param("b", 2)
          .param("c", 3)
          .param("d", new JsonArray().add(4).add(5))
          .param("hello", "francesco")
          .param("coordinates", new JsonArray().add("10").add("20"))
          .param("color", new JsonObject().put("r", 1).put("g", 2).put("b", 1))
          .param("secondColor", new JsonObject().put("r", 255).put("g", 255).put("b", 255))
      )
    );
  }

}
