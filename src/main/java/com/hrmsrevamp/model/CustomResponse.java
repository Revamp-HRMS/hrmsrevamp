package com.hrmsrevamp.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
  private String message;
  private Boolean success;
  private Object data;

  public static CustomResponse setAndGetCustomResponse(boolean success, String message, Object data) {
    return CustomResponse.builder()
            .success(success)
            .message(message)
            .data(data)
            .build();
  }
}