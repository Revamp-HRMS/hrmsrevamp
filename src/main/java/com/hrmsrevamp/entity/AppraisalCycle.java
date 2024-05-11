package com.hrmsrevamp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Entity
@Table(name = "appraisal_cycle")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppraisalCycle {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPRAISAL_CYCLE_SEQ")
  @SequenceGenerator(name = "APPRAISAL_CYCLE_SEQ", sequenceName = "APPRAISAL_CYCLE_SEQ", initialValue = 1, allocationSize = 1)
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
