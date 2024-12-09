package com.defects_management.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class DefectReportDtoTest {
    
    @InjectMocks
    private DefectReportDto defectReportDto;
    
    @Test
    public void testDefectReportDto() {
        int id = 1;
        String title = "Sample Title";
        String defectDetails = "Sample Defect Details";
        String stepsToReproduce = "Sample Steps to Reproduce";
        String priority = "High";
        LocalDate detectedOn = LocalDate.now();
        LocalDate expectedResolution = LocalDate.now().plusDays(7);
        String reportedByTesterId = "Tester123";
        String assignedToDeveloperId = "Developer456";
        String severity = "Critical";
        String status = "Open";
        int projectCode = 123;
        
        defectReportDto.setId(id);
        defectReportDto.setTitle(title);
        defectReportDto.setDefectDetails(defectDetails);
        defectReportDto.setStepsToReproduce(stepsToReproduce);
        defectReportDto.setPriority(priority);
        defectReportDto.setDetectedOn(detectedOn);
        defectReportDto.setExpectedResolution(expectedResolution);
        defectReportDto.setReportedByTesterId(reportedByTesterId);
        defectReportDto.setAssignedToDeveloperId(assignedToDeveloperId);
        defectReportDto.setSeverity(severity);
        defectReportDto.setStatus(status);
        defectReportDto.setProjectCode(projectCode);
        
        assertEquals(id, defectReportDto.getId());
        assertEquals(title, defectReportDto.getTitle());
        assertEquals(defectDetails, defectReportDto.getDefectDetails());
        assertEquals(stepsToReproduce, defectReportDto.getStepsToReproduce());
        assertEquals(priority, defectReportDto.getPriority());
        assertEquals(detectedOn, defectReportDto.getDetectedOn());
        assertEquals(expectedResolution, defectReportDto.getExpectedResolution());
        assertEquals(reportedByTesterId, defectReportDto.getReportedByTesterId());
        assertEquals(assignedToDeveloperId, defectReportDto.getAssignedToDeveloperId());
        assertEquals(severity, defectReportDto.getSeverity());
        assertEquals(status, defectReportDto.getStatus());
        assertEquals(projectCode, defectReportDto.getProjectCode());
    }
}
