package com.defects_management.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefectDtoTest {

	private DefectDto defectDto;

	@BeforeEach
	public void setUp() {
		defectDto = new DefectDto();
		defectDto.setId(1);
		defectDto.setTitle("Sample Defect");
		defectDto.setDefectDetails("Defect Details");
		defectDto.setStepsToReproduce("Steps to Reproduce");
		defectDto.setPriority("High");
		defectDto.setDetectedOn(LocalDate.now());
		defectDto.setExpectedResolution(LocalDate.now().plusDays(7));
		defectDto.setReportedByTesterId("tester123");
		defectDto.setAssignedToDeveloperId("dev456");
		defectDto.setSeverity("Major");
		defectDto.setStatus("New");
		defectDto.setProjectCode(1234);
	}

	@Test
	public void testDefectDto() {
		assertEquals(1, defectDto.getId());
		assertEquals("Sample Defect", defectDto.getTitle());
		assertEquals("Defect Details", defectDto.getDefectDetails());
		assertEquals("Steps to Reproduce", defectDto.getStepsToReproduce());
		assertEquals("High", defectDto.getPriority());
		assertEquals(LocalDate.now(), defectDto.getDetectedOn());
		assertEquals(LocalDate.now().plusDays(7), defectDto.getExpectedResolution());
		assertEquals("tester123", defectDto.getReportedByTesterId());
		assertEquals("dev456", defectDto.getAssignedToDeveloperId());
		assertEquals("Major", defectDto.getSeverity());
		assertEquals("New", defectDto.getStatus());
		assertEquals(1234, defectDto.getProjectCode());
	}

	@Test
	public void testDetectedOnDefault() {
		defectDto.setDetectedOn(LocalDate.now());
		assertEquals(LocalDate.now(), defectDto.getDetectedOn());
	}

	@Test
	public void testStatusDefault() {
		defectDto.setStatus("New");
		assertEquals("New", defectDto.getStatus());
	}

	@Test
	public void testToString() {
		assertEquals(
				"DefectDto(id=1, title=Sample Defect, defectDetails=Defect Details, stepsToReproduce=Steps to Reproduce, priority=High, detectedOn="
						+ LocalDate.now() + ", expectedResolution=" + LocalDate.now().plusDays(7)
						+ ", reportedByTesterId=tester123, assignedToDeveloperId=dev456, severity=Major, status=New, projectCode=1234)",
				defectDto.toString());
	}

	@Test
	public void testGetId() {
		assertEquals(1, defectDto.getId());
	}

	@Test
	public void testSetTitle() {
		defectDto.setTitle("New Title");
		assertEquals("New Title", defectDto.getTitle());
	}

	@Test
	public void testSetDefectDetails() {
		defectDto.setDefectDetails("New Defect Details");
		assertEquals("New Defect Details", defectDto.getDefectDetails());
	}
}

