package com.example.photousers.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
@Component
public class FeignErrorDecoder implements ErrorDecoder {
  Environment env;

  @Autowired
  public FeignErrorDecoder(Environment env) {
    this.env = env;
  }

  @Override
  public Exception decode(String s, Response response) {



    switch (response.status()) {
      case 404: {
        if (s.contains("getAlbums")) {
          return new ResponseStatusException(HttpStatus.valueOf(response.status()),env.getProperty("albums.exceptions.albums.not_found"));
        }
        break;
      }
      case 400: {
        break;
      }
      default: {
        return new Exception(response.reason());
      }
    }
    return null;
  }


  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }


}
