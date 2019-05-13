package io.vertx.ext.web.client;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface URIParameters {

  default URIParameters param(String key, Object value) {
    return param(key, value.toString());
  }

  URIParameters param(String key, String value);

  default URIParameters param(String key, JsonArray array) {
    return param(key, array.stream().map(Object::toString).collect(Collectors.toList()));
  }

  URIParameters param(String key, List<String> array);

  default URIParameters param(String key, JsonObject object) {
    return param(key, object.stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString())));
  }

  URIParameters param(String key, Map<String, String> object);

  static URIParameters create() {
    return null;
  }

}
