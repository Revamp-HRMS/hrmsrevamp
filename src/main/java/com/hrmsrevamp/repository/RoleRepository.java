package com.hrmsrevamp.repository;


import com.hrmsrevamp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleRepository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
  List<Role> findByNameIn(List<String> names);
}
