package com.defects_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defects_management.dto.DefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.exception.ResourceNotfoundException;
import com.defects_management.repository.DefectRepository;

@Service
public class DefectDtoService {

	@Autowired
	ModelMapper modelMapper;
	

	@Autowired
	private DefectRepository defectRepository;

	public DefectDto AddDefect(DefectDto defectDto) {
		Defect defect = new Defect();
		defect.setTitle(defectDto.getTitle());
		defect.setDefectDetails(defectDto.getDefectDetails());
		defect.setStepsToReproduce(defectDto.getStepsToReproduce());
		defect.setPriority(defectDto.getPriority());
		defect.setDetectedOn(defectDto.getDetectedOn());
		defect.setExpectedResolution(expected(defectDto.getPriority(), defectDto.getSeverity(), defectDto.getDetectedOn()));
		defect.setReportedByTesterId(defectDto.getReportedByTesterId());
		defect.setAssignedToDeveloperId(defectDto.getAssignedToDeveloperId());
		defect.setSeverity(defectDto.getSeverity());
		defect.setStatus(defectDto.getStatus());
		defect.setProjectCode(defectDto.getProjectCode());

		LocalDate detectedOn = defectDto.getDetectedOn();
		String assignedToDeveloperId = defectDto.getAssignedToDeveloperId();
		Boolean b = defectRepository.isCountIsless(detectedOn, assignedToDeveloperId);
		if (b == true) {
			Defect savedDefect = defectRepository.save(defect);
			DefectDto savedDefectDto = new DefectDto();
			savedDefectDto.setId(savedDefect.getId());
			savedDefectDto.setTitle(savedDefect.getTitle());
			savedDefectDto.setDefectDetails(savedDefect.getDefectDetails());
			savedDefectDto.setStepsToReproduce(savedDefect.getStepsToReproduce());
			savedDefectDto.setPriority(savedDefect.getPriority());
			savedDefectDto.setDetectedOn(savedDefect.getDetectedOn());
			savedDefectDto.setExpectedResolution(savedDefect.getExpectedResolution());
			savedDefectDto.setReportedByTesterId(savedDefect.getReportedByTesterId());
			savedDefectDto.setAssignedToDeveloperId(savedDefect.getAssignedToDeveloperId());
			savedDefectDto.setSeverity(savedDefect.getSeverity());
			savedDefectDto.setStatus(savedDefect.getStatus());
			savedDefectDto.setProjectCode(savedDefect.getProjectCode());
			return savedDefectDto;
			
		} else 
		{
			return null;
		}
	}
	
	public List<DefectDto> GetAllDefectDto() {
		return defectRepository.findAll().stream().map(this::convertEntityDto).collect(Collectors.toList());
	}

	private DefectDto convertEntityDto(Defect defect) {
		DefectDto defectDto = new DefectDto();
		defectDto.setId(defect.getId());
		defectDto.setTitle(defect.getTitle());
		defectDto.setDefectDetails(defect.getDefectDetails());
		defectDto.setStepsToReproduce(defect.getStepsToReproduce());
		defectDto.setPriority(defect.getPriority());
		defectDto.setDetectedOn(defect.getDetectedOn());
		defectDto.setExpectedResolution(defect.getExpectedResolution());
		defectDto.setReportedByTesterId(defect.getReportedByTesterId());
		defectDto.setAssignedToDeveloperId(defect.getAssignedToDeveloperId());
		defectDto.setSeverity(defect.getSeverity());
		defectDto.setStatus(defect.getStatus());
		defectDto.setProjectCode(defect.getProjectCode());
		return defectDto;
	}
	
//	public DefectDto getById(int id) {
//		Defect defect = defectRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotfoundException("Defect", id, "ID"));
//		return convertEntityDto(defect);
//	}

	public List<DefectDto> GetAssigendDeveloper(String assignedToDeveloperId) {
		Boolean b=defectRepository.findByDeveloperId(assignedToDeveloperId);
		if(b==true)
		return defectRepository.getDeveloper(assignedToDeveloperId).stream().map(this::convertEntityDto)
				.collect(Collectors.toList());
		else
		{
			return null;
		}
	}

	public List<DefectDto> getReport(int projectCode) {
		Boolean b=defectRepository.findByProjectId(projectCode);
		if(b==true)
		{
		return defectRepository.getProject(projectCode).stream().map(this::convertEntityDto)
				.collect(Collectors.toList());
		}
		else
		{
			return null;
		}
	}

	public Defect deleteById(int id) {
		Optional<Defect> b=defectRepository.findById(id);
		if(b.isPresent()) {
			Defect defect1=defectRepository.findById(id).get();
			 defectRepository.deleteById(id);
			 return defect1;
		}
		else
		{
			return null;
		}
	}
	
	public static LocalDate expected(String priority, String severity, LocalDate detectedOn) {
	    int daysToAdd;
	    if (severity.equalsIgnoreCase("Blocker")) {
	        switch (priority.toLowerCase()) {
	            case "high":
	                daysToAdd = 2;
	                break;
	            case "medium":
	                daysToAdd = 3;
	                break;
	            default:
	                daysToAdd = 5;
	        }
	    } else if (severity.equalsIgnoreCase("critical")) {
	        switch (priority.toLowerCase()) {
	            case "high":
	                daysToAdd = 1;
	                break;
	            case "medium":
	                daysToAdd = 2;
	                break;
	            default:
	                daysToAdd = 5;
	        }
	    } else if (severity.equalsIgnoreCase("major")) {
	        daysToAdd = 5;
	    } else if (severity.equalsIgnoreCase("minor")) {
	        daysToAdd = 8;
	    } else {
	        daysToAdd = 10;
	    }
	    return detectedOn.plusDays(daysToAdd);
	}

}
