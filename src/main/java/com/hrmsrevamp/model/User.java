package com.hrmsrevamp.model;

import lombok.*;

import java.util.List;

/**
 * User Model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private Long userId;
  private String email;
  private String mobileNumber;
  private String fullName;
  private List<String> roles;
  private String status;
  private String createdOn;
  private Long createdBy;
  private String updatedOn;
  private Long updatedBy;
  private Boolean userRegistered;
  private String updatedToken;
}
