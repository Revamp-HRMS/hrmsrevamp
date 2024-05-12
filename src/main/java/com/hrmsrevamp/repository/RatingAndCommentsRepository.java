package com.hrmsrevamp.repository;

import com.hrmsrevamp.entity.RatingAndComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingAndCommentsRepository extends JpaRepository<RatingAndComments, Long> {


}
