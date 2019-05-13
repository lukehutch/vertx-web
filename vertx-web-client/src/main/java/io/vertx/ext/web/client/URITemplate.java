package io.vertx.ext.web.client;

/**
 * Syntax:
 * /api/{name}?R={r}&G={g}&B={b}
 * /api/my_{id}{?color*}{&x}
 * /api{/coordinates*,list*}
 * /
 *
 */
public interface URITemplate {

  String expand(URIParameters parameters);

  static URITemplate parse(String s) {
    return null;
  }

  //TODO usage:
  // URITemplate.parse("/{a}/{b}")

}
