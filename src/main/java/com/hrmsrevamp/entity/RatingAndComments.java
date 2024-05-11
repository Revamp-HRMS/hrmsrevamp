package com.hrmsrevamp.entity;


import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingAndComments {

  @OneToOne
  private Long jobElementsId;
  private String selfAssessment;
  private String projectLeader;
  private String mentor;
  private String employeeComments;
  private String projectManagerComments;
  private String MentorComments;
}
