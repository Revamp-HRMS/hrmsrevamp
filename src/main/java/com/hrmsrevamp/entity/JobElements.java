package com.hrmsrevamp.entity;

import com.hrmsrevamp.entity.RatingAndComments;
import jakarta.persistence.OneToOne;

public class JobElements {
  private Long id;
  private Long userId;
  private Long appraisalCycleId;
  private String jobElement;
  @OneToOne
  private RatingAndComments ratingAndComments;
}
