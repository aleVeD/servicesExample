package com.example.photousers.service;

import com.example.photousers.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto userDetails);
  UserDto getUserDetailsByEmail(String email);
  UserDto getUserById(String userId);
}
