package com.defects_management.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class DefectDto {

	private int id;
	
	private String title;

	private String defectDetails;

	private String stepsToReproduce;

	private String priority;

	private LocalDate detectedOn = LocalDate.now();

	private LocalDate expectedResolution;

	private String reportedByTesterId;

	private String assignedToDeveloperId;

	private String severity;

	private String status = "New";

	private int projectCode;
}
