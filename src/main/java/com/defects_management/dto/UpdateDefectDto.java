package com.defects_management.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDefectDto {
	
	private String resolution;
	private LocalDate resolutionDate = LocalDate.now();
	private String status;

}
