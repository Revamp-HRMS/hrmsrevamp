package com.hrmsrevamp.service.impl;
import com.hrmsrevamp.constant.MessageEnum;
import com.hrmsrevamp.constant.UserStatusEnum;
import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.exception.UnauthorizedException;
import com.hrmsrevamp.repository.UserRepository;
import com.hrmsrevamp.security.UserPrincipal;
import com.hrmsrevamp.util.RegExValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User Details Service Implementation.
 */

@Service
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username != null && !username.isEmpty()) {
      username = username.trim();
    }
    final String email = username;
    RegExValidator.validateEmail(email);
    Optional<User> optionalUser = userRepository.findByEmailAndStatusNot(email, UserStatusEnum.DELETED.name());
    verifyUserStatus(optionalUser);
    return UserPrincipal.create(optionalUser.get());
  }

  private void verifyUserStatus(Optional<User> optionalUser) {
    if (optionalUser.isPresent() && optionalUser.get().getStatus().equals(UserStatusEnum.INACTIVE.name())) {
      throw new UnauthorizedException(MessageEnum.LOGGED_IN_USER_IS_INACTIVE.name());
    } else if (optionalUser.isPresent() && optionalUser.get().getStatus().equals(UserStatusEnum.DELETED.name())) {
      throw new UnauthorizedException(MessageEnum.LOGGED_IN_USER_IS_INVALID.name());
    }
  }
}
