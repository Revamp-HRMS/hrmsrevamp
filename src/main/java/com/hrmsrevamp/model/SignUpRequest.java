package com.hrmsrevamp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
  @NotEmpty(message = "Email must not be empty")
  @Email
  private String email;
  private String fullName;
  private String role;
  @NotNull(message = "Password must not be empty")
  @NotEmpty(message = "Password must not be empty")
  private String password;
}
