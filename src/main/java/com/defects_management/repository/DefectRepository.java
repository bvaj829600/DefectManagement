package com.defects_management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.defects_management.entity.Defect;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Integer> {

	@Query("Select D from Defect D WHERE D.assignedToDeveloperId=:assignedToDeveloperId")
	public List<Defect> getDeveloper(@Param("assignedToDeveloperId") String assignedToDeveloperId);

	@Query("Select D from Defect D WHERE D.projectCode=:projectCode")
	public List<Defect> getProject(@Param("projectCode") int projectCode);
	
	@Query("SELECT case When COUNT(assignedToDeveloperId)<5 then TRUE else false end FROM Defect d where d.detectedOn=:detectedOn and d.assignedToDeveloperId=:assignedToDeveloperId")			
	public Boolean isCountIsless(@Param("detectedOn") LocalDate detectedOn,@Param("assignedToDeveloperId") String assignedToDeveloperId);
	
	@Query("SELECT case When COUNT(assignedToDeveloperId)>0 then TRUE else false end FROM Defect d where d.assignedToDeveloperId=:assignedToDeveloperId")
	public Boolean findByDeveloperId(@Param("assignedToDeveloperId") String assignedToDeveloperId);
	
	@Query("SELECT case When COUNT(projectCode)>0 then TRUE else false end FROM Defect d where d.projectCode=:projectCode")
	public Boolean findByProjectId(@Param("projectCode") int projectCode);

}
