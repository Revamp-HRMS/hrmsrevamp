package com.hrmsrevamp.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JwtAuthenticationResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private String refreshToken;
}
