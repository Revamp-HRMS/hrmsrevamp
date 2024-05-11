package com.hrmsrevamp.model;

import lombok.*;

/**
 * SignInRequest model.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
  private String email;
  private String password;
  private String refreshToken;
}
