package com.example.photousers.data;

import com.example.photousers.ui.models.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws", fallback = AlbumFallback.class)
public interface AlbumServiceClients {
  @GetMapping("/users/{id}/albums")
  public List<AlbumResponseModel> getAlbums(@PathVariable String id);
}
@Component
class AlbumFallback implements AlbumServiceClients{

  @Override
  public List<AlbumResponseModel> getAlbums(String id) {
    return new ArrayList<>();
  }
}
