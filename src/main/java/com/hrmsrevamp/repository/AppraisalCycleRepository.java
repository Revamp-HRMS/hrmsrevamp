package com.hrmsrevamp.repository;

import com.hrmsrevamp.entity.AppraisalCycle;

@org.springframework.stereotype.Repository
public interface AppraisalCycleRepository extends org.springframework.data.jpa.repository.JpaRepository<AppraisalCycle, Long> {
  java.util.List<AppraisalCycle> findByUserId(long userId);
}
