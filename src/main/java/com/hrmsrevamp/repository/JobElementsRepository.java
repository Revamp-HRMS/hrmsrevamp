package com.hrmsrevamp.repository;

import com.hrmsrevamp.entity.JobElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobElementsRepository extends JpaRepository<JobElements, Long> {
}
