package com.hrmsrevamp.service.impl;

import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.repository.UserRepository;
import com.hrmsrevamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> getAllUser(String role) {
    return userRepository.findAll();
  }
}
