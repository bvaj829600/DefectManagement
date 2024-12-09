package com.defects_management.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Defect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@Column(name = "Title")
	private String title;

//	@Column(name="DefectDetails")
	private String defectDetails;

//	@Column(name="StepsToReproduce")
	private String stepsToReproduce;

//	@Column(name="Priority")
	@Pattern(regexp = "^(High|Medium|Low)$", message="Invalid priority")
	private String priority;

//	@Column(name="DetectedOn")
	private LocalDate detectedOn = LocalDate.now();

//	@Column(name="ExpectedResolution")
	private LocalDate expectedResolution;

//	@Column(name="ReportedByTesterId")
	private String reportedByTesterId;

//	@Column(name="AssignedToDeveloperId")
	private String assignedToDeveloperId;

//	@Column(name="Severity")
	@Pattern(regexp = "^(Blocker|Critical|Major|Minor|Low)$", message="Invalid severity")
	private String severity;

//	@Column(name="Status")
	@Pattern(regexp = "^(New|Inprogress|Completed)$", message="Invalid Status")
	private String status="New";

//	@Column(name="ProjectCode")
	private int projectCode;

	@OneToMany(targetEntity = Resolutions.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Defect_Resolution", referencedColumnName = "id")
	private List<Resolutions> resolutions = new ArrayList<>();

}
