package com.defects_management.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DefectTest {

    @Mock
    private Resolutions mockResolution;

    @InjectMocks
    private Defect defect;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        List<Resolutions> resolutions = new ArrayList<>();
        resolutions.add(mockResolution);
        defect = new Defect(1, "Test Defect", "Defect Details", "Steps to Reproduce", "High", 
                LocalDate.of(2023, 4, 19), LocalDate.of(2023, 4, 23), "Tester1", "Developer1", 
                "Blocker", "New", 123, resolutions);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, defect.getId());
        assertEquals("Test Defect", defect.getTitle());
        assertEquals("Defect Details", defect.getDefectDetails());
        assertEquals("Steps to Reproduce", defect.getStepsToReproduce());
        assertEquals("High", defect.getPriority());
        assertEquals(LocalDate.of(2023, 4, 19), defect.getDetectedOn());
        assertEquals(LocalDate.of(2023, 4, 23), defect.getExpectedResolution());
        assertEquals("Tester1", defect.getReportedByTesterId());
        assertEquals("Developer1", defect.getAssignedToDeveloperId());
        assertEquals("Blocker", defect.getSeverity());
        assertEquals("New", defect.getStatus());
        assertEquals(123, defect.getProjectCode());

        List<Resolutions> resolutions = new ArrayList<>();
        resolutions.add(mockResolution);
        defect.setResolutions(resolutions);
        assertEquals(resolutions, defect.getResolutions());
    }

//    @Test
//    public void testDefectStringRepresentation() {
//        String expectedString = "Defect [id=1, title=Test Defect, defectDetails=Defect Details, stepsToReproduce=Steps to Reproduce, "
//                + "priority=High, detectedOn=2023-04-19, expectedResolution=2023-04-23, reportedByTesterId=Tester1, "
//                + "assignedToDeveloperId=Developer1, severity=Blocker, status=New, projectCode=123, resolutions=[Resolutions [id=0]]]";
//        assertEquals(expectedString, defect.toString());
//    }
}

