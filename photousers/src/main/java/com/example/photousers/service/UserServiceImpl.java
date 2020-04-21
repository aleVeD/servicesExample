package com.example.photousers.service;

import com.example.photousers.data.AlbumServiceClients;
import com.example.photousers.data.UserEntity;
import com.example.photousers.data.UsersRepository;
import com.example.photousers.shared.UserDto;
import com.example.photousers.ui.models.AlbumResponseModel;
import feign.FeignException;
import feign.Logger;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  UsersRepository usersRepository;
  BCryptPasswordEncoder bCryptPasswordEncoder;
 // RestTemplate restTemplate;
  AlbumServiceClients albumServiceClients;
  Environment env;
  //Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
  public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env, AlbumServiceClients albumServiceClients) {
    this.usersRepository = usersRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    //this.restTemplate = restTemplate;
  this.albumServiceClients = albumServiceClients;
    this.env = env;
  }

  @Override
  public UserDto createUser(UserDto userDetails) {
    // TODO Auto-generated method stub

    userDetails.setUserId(UUID.randomUUID().toString());
    userDetails.setEncriptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

    usersRepository.save(userEntity);

    UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

    return returnValue;
  }

  @Override
  public UserDto getUserDetailsByEmail(String email) {
    UserEntity userEntity = usersRepository.findByEmail(email);

    if(userEntity == null) throw new UsernameNotFoundException(email);


    return new ModelMapper().map(userEntity, UserDto.class);
  }


  @Override
  public UserDto getUserById(String userId) {
    UserEntity userEntity = usersRepository.findByUserId(userId);
    if(userEntity==null) throw new UsernameNotFoundException(userId + "not found");
    UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
  //  String albumsUrl = String.format(env.getProperty("albums.url"), userId);
   // ResponseEntity<List<AlbumResponseModel>> albumListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
 //   });

   // List<AlbumResponseModel> albumList = albumListResponse.getBody();
    List<AlbumResponseModel> albumList = albumServiceClients.getAlbums(userId);
    userDto.setAlbums(albumList);
    return userDto;
  }

  @Override
  public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
    UserEntity userEntity = usersRepository.findByEmail(user);
    if(userEntity==null) throw new UsernameNotFoundException(user);
    return new User(userEntity.getEmail(), userEntity.getEncriptedPassword(), true, true, true, true, new ArrayList<>());
  }
}
