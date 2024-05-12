package com.hrmsrevamp.model;

@lombok.Getter
@lombok.Setter
public class JobElementsModel {

  private Long userId;
  private Long appraisalCycleId;
  private String jobElement;
  private String selfAssessment;
  private String projectLeader;
  private String mentor;
  private String employeeComments;
  private String projectManagerComments;
  private String MentorComments;
}
