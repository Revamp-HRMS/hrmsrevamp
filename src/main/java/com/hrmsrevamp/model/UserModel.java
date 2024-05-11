package com.hrmsrevamp.model;

@lombok.Data
@lombok.Builder
public class UserModel {

  private Long id;
  private String fullName;
  private String role;
  private String email;
}
