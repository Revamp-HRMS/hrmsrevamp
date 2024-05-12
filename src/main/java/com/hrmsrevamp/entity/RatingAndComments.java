package com.hrmsrevamp.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "rating_and_comments")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RatingAndComments {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_AND_COMMENTS_CYCLE_SEQ")
  @SequenceGenerator(name = "RATING_AND_COMMENTS_CYCLE_SEQ", sequenceName = "RATING_AND_COMMENTS_CYCLE_SEQ", initialValue = 1, allocationSize = 1)
  private Long id;
  private Long appraisalId;
  private String jobElement;
  private String selfAssessment;
  private String projectLeader;
  private String mentor;
  private String employeeComments;
  private String projectManagerComments;
  private String MentorComments;
}
