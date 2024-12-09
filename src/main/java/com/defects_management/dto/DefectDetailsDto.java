package com.defects_management.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.defects_management.entity.Resolutions;
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
public class DefectDetailsDto {

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

	private String status;

	private int projectCode;

	private List<Resolutions> resolutions = new ArrayList<>();

}
