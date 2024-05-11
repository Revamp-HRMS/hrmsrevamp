package com.hrmsrevamp.constant;
/**
 * SortDirection_Enum.
 */

public enum SortDirection {
    ASC("ASC"), DESC("DESC");

  private final String directionCode;

  SortDirection(String direction) {
    this.directionCode = direction;
  }

  public String getDirectionCode() {
    return this.directionCode;
  }

}

