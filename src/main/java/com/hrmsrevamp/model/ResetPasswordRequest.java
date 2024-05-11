package com.hrmsrevamp.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
  @NotEmpty(message = "newPassword must not be empty")
  @Size(message = "Password should have at least 8 characters")
  private String newPassword;
  @NotEmpty(message = "confirmPassword must not be empty")
  @Size(message = "Password should have at least 8 characters")
  private String confirmPassword;
}

