package com.defects_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.defects_management.entity.Resolutions;

@Repository
public interface ResolutionsRepository extends JpaRepository<Resolutions, Integer> {

}
