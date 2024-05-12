package com.hrmsrevamp.service;

import com.hrmsrevamp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  com.hrmsrevamp.model.CustomResponse getAllUser(String role);
}
