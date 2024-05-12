package com.hrmsrevamp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "job_elements")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JobElements {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_ELEMENTS_SEQ")
    @SequenceGenerator(name = "JOB_ELEMENTS_SEQ", sequenceName = "JOB_ELEMENTS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    private Long userId;
    private String jobElement;
    private Long ratingAndCommentsId;
}
