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
  public com.hrmsrevamp.model.CustomResponse getAllUser(String role) {
    java.util.List<com.hrmsrevamp.model.UserModel> userModels = userRepository.findByRoles_Name(role)
        .stream().map(user -> com.hrmsrevamp.model.UserModel.builder()
            .email(user.getEmail())
            .id(user.getId())
            .role(role)
            .fullName(user.getFullName()).build()).toList();

    return com.hrmsrevamp.model.CustomResponse.setAndGetCustomResponse(true,
        String.valueOf(com.hrmsrevamp.constant.MessageEnum.DATA_RETRIEVED), userModels);
  }
}
