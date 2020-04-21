package com.example.photousers.ui.controllers;


import com.example.photousers.service.UserService;
import com.example.photousers.shared.UserDto;
import com.example.photousers.ui.models.AlbumResponseModel;
import com.example.photousers.ui.models.CreateUserRequestModel;
import com.example.photousers.ui.models.CreateUserResponseModel;
import com.example.photousers.ui.models.UserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
  @Autowired
  private Environment env;

  @Autowired
  UserService userService;

  @GetMapping("/status/check")
  public String status() {
    return "working" + env.getProperty("local.server.port")+ " token= " + env.getProperty("token.secret");
  }

  @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
               produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails){
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    UserDto userDto = modelMapper.map(userDetails, UserDto.class);
    UserDto createdUser= userService.createUser(userDto);
    CreateUserResponseModel userResponse = modelMapper.map(createdUser, CreateUserResponseModel.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }

  @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId){
    UserDto userDto = userService.getUserById(userId);
    UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
    return ResponseEntity.status(HttpStatus.OK).body(returnValue);
  }
}