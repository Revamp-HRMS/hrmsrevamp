package com.hrmsrevamp.model;

@lombok.Setter
@lombok.Getter
public class AppraisalCycleModel {

  private Long Id;
  private Long userId;
  private String name;
  private String employeeCode;
  private String designation;
  private String reportingManager;
  private String reviewingManager;
  private String appraisalCycle; //1 March 2024 // 2024-04-10
  private String status;

}
