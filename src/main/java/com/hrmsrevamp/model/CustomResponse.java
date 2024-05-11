package com.hrmsrevamp.model;

@lombok.Getter
@lombok.Setter
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
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