package com.hrmsrevamp.service;


import com.hrmsrevamp.model.CustomResponse;
import com.hrmsrevamp.model.ResetPasswordRequest;
import com.hrmsrevamp.model.SignInRequest;
import com.hrmsrevamp.model.SignUpRequest;

/**
 * Authentication Service.
 */

public interface AuthService {
  CustomResponse userSignUp(SignUpRequest signUpRequest);

  CustomResponse signIn(SignInRequest signInRequest);

  CustomResponse resetAdminPassword(ResetPasswordRequest resetPwdRequest);

  CustomResponse checkUserExistsOrNot(String email);

  CustomResponse refreshTokenForUser(SignInRequest request);

}
