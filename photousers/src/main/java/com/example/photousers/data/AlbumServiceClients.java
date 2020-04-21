package com.example.photousers.data;

import com.example.photousers.ui.models.AlbumResponseModel;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@FeignClient(name = "albums-ws", fallbackFactory = AlbumFallbackFactory.class)
public interface AlbumServiceClients {
  @GetMapping("/users/{id}/albums")
  public List<AlbumResponseModel> getAlbums(@PathVariable String id);
}
@Component
class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClients>{
/*
  @Override
  public List<AlbumResponseModel> getAlbums(String id) {
    return new ArrayList<>();
  }*/

  @Override
  public AlbumServiceClients create(Throwable throwable) {

    return new AlbumServiceClientFallback(throwable);
  }
}

class AlbumServiceClientFallback implements  AlbumServiceClients {
  Logger logger = LoggerFactory.logger(this.getClass());
  private final Throwable cause;

  public AlbumServiceClientFallback(Throwable cause) {
    this.cause = cause;
  }

  @Override
  public List<AlbumResponseModel> getAlbums(String id) {
    if(cause instanceof FeignException &&((FeignException) cause).status()==404){
      logger.error(" id error" + id+ "error: "+ cause.getLocalizedMessage());
    }else{
      logger.error("the error:"+cause.getLocalizedMessage());
    }
    return new ArrayList<>();
  }
}
