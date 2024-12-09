package com.defects_management.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import java.util.Arrays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.defects_management.dto.DefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.exception.ResourceNotfoundException;
import com.defects_management.repository.DefectRepository;

public class DefectDtoServiceTest {

    @Mock
    private DefectRepository defectRepository;

    @InjectMocks
    private DefectDtoService defectDtoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDefect() {
        DefectDto defectDto = new DefectDto();
        defectDto.setTitle("Title");
        defectDto.setDefectDetails("Defect Details");
        defectDto.setStepsToReproduce("Steps to Reproduce");
        defectDto.setPriority("High");
        defectDto.setSeverity("Blocker");
        defectDto.setDetectedOn(LocalDate.now());
        defectDto.setReportedByTesterId("Tester123");
        defectDto.setAssignedToDeveloperId("Developer123");
        defectDto.setStatus("Open");
        defectDto.setProjectCode(123);

        Defect defect = new Defect();
        defect.setTitle("Title");
        defect.setDefectDetails("Defect Details");
        defect.setStepsToReproduce("Steps to Reproduce");
        defect.setPriority("High");
        defect.setSeverity("Blocker");
        defect.setDetectedOn(LocalDate.now());
        defect.setExpectedResolution(LocalDate.now().plusDays(2));
        defect.setReportedByTesterId("Tester123");
        defect.setAssignedToDeveloperId("Developer123");
        defect.setStatus("Open");
        defect.setProjectCode(123);

        when(defectRepository.isCountIsless(defectDto.getDetectedOn(), defectDto.getAssignedToDeveloperId())).thenReturn(true);
        when(defectRepository.save(defect)).thenReturn(defect);

        DefectDto savedDefectDto = defectDtoService.AddDefect(defectDto);

        assertNotNull(savedDefectDto);
        assertEquals(defect.getTitle(), savedDefectDto.getTitle());
        assertEquals(defect.getDefectDetails(), savedDefectDto.getDefectDetails());
        assertEquals(defect.getStepsToReproduce(), savedDefectDto.getStepsToReproduce());
        assertEquals(defect.getPriority(), savedDefectDto.getPriority());
        assertEquals(defect.getSeverity(), savedDefectDto.getSeverity());
        assertEquals(defect.getDetectedOn(), savedDefectDto.getDetectedOn());
        assertEquals(defect.getExpectedResolution(), savedDefectDto.getExpectedResolution());
        assertEquals(defect.getReportedByTesterId(), savedDefectDto.getReportedByTesterId());
        assertEquals(defect.getAssignedToDeveloperId(), savedDefectDto.getAssignedToDeveloperId());
        assertEquals(defect.getStatus(), savedDefectDto.getStatus());
        assertEquals(defect.getProjectCode(), savedDefectDto.getProjectCode());
    }
    
    @Test
    public void testGetAllDefectDto() {
        // Create some mock data
        Defect defect1 = new Defect();
        defect1.setId(1);
        defect1.setTitle("Defect 1");
        defect1.setDefectDetails("Details of Defect 1");
        defect1.setStepsToReproduce("Steps to reproduce Defect 1");
        defect1.setPriority("High");
        defect1.setDetectedOn(LocalDate.of(2023, 4, 1));
        defect1.setExpectedResolution(LocalDate.of(2023, 4, 3));
        defect1.setReportedByTesterId("Tester1");
        defect1.setAssignedToDeveloperId("Developer1");
        defect1.setSeverity("Blocking");
        defect1.setStatus("Open");
        defect1.setProjectCode(1234);

        Defect defect2 = new Defect();
        defect2.setId(2);
        defect2.setTitle("Defect 2");
        defect2.setDefectDetails("Details of Defect 2");
        defect2.setStepsToReproduce("Steps to reproduce Defect 2");
        defect2.setPriority("Medium");
        defect2.setDetectedOn(LocalDate.of(2023, 4, 2));
        defect2.setExpectedResolution(LocalDate.of(2023, 4, 5));
        defect2.setReportedByTesterId("Tester2");
        defect2.setAssignedToDeveloperId("Developer2");
        defect2.setSeverity("Major");
        defect2.setStatus("Closed");
        defect2.setProjectCode(5678);

        List<Defect> mockDefects = new ArrayList<>();
        mockDefects.add(defect1);
        mockDefects.add(defect2);

        // Set up the mock behavior of the repository
        when(defectRepository.findAll()).thenReturn(mockDefects);

        // Call the method being tested
        List<DefectDto> result = defectDtoService.GetAllDefectDto();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Defect 1", result.get(0).getTitle());
        assertEquals("Details of Defect 2", result.get(1).getDefectDetails());
    }
    

    @Mock
    private ModelMapper modelMapper;

//    @InjectMocks
//    private DefectDtoService defectDtoService;


//    @Test
//    public void testGetById() {
//        // Given
//        Defect defect = new Defect();
//        defect.setId(1);
//        defect.setTitle("Test Defect");
//        when(defectRepository.findById(anyInt())).thenReturn(Optional.of(defect));
//
//        DefectDto defectDto = new DefectDto();
//        defectDto.setId(1);
//        defectDto.setTitle("Test Defect");
//        when(modelMapper.map(defect, DefectDto.class)).thenReturn(defectDto);
//
//        // When
//        DefectDto result = defectDtoService.getById(1);
//
//        // Then
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isEqualTo(1);
//        assertThat(result.getTitle()).isEqualTo("Test Defect");
//    }
//
//    @Test
//    public void testGetByIdThrowsExceptionWhenDefectNotFound() {
//        // Given
//        when(defectRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        // When / Then
//        ResourceNotfoundException exception = 
//            org.junit.jupiter.api.Assertions.assertThrows(ResourceNotfoundException.class, 
//                () -> defectDtoService.getById(1));
//        assertThat(exception.getMessage()).isEqualTo("Defect not found with ID : '1'");
//    }
    

    @Test
    public void testGetAssigendDeveloper() {
        // Mock the defect repository
        String assignedToDeveloperId = "developer1";
        Defect defect1 = new Defect();
        defect1.setId(1);
        defect1.setTitle("Defect 1");
        defect1.setAssignedToDeveloperId(assignedToDeveloperId);
        Defect defect2 = new Defect();
        defect2.setId(2);
        defect2.setTitle("Defect 2");
        defect2.setAssignedToDeveloperId(assignedToDeveloperId);
        List<Defect> mockDefects = Arrays.asList(defect1, defect2);
        when(defectRepository.getDeveloper(assignedToDeveloperId)).thenReturn(mockDefects);
        when(defectRepository.findByDeveloperId(assignedToDeveloperId)).thenReturn(true);

        // Call the service method
        List<DefectDto> actual = defectDtoService.GetAssigendDeveloper(assignedToDeveloperId);

        // Check the results
        assertEquals(mockDefects.size(), actual.size());
        for (int i = 0; i < mockDefects.size(); i++) {
            Defect mockDefect = mockDefects.get(i);
            DefectDto actualDto = actual.get(i);
            assertEquals(mockDefect.getId(), actualDto.getId());
            assertEquals(mockDefect.getTitle(), actualDto.getTitle());
            assertEquals(mockDefect.getAssignedToDeveloperId(), actualDto.getAssignedToDeveloperId());
        }
    }
    

    @Test
    public void testGetReport() {
        // Setup
        int projectCode = 123;
        LocalDate detectedOn = LocalDate.now();
        String assignedToDeveloperId = "dev-123";
        List<Defect> mockDefects = new ArrayList<>();
        Defect defect1 = new Defect();
        defect1.setId(1);
        defect1.setTitle("Defect 1");
        defect1.setDefectDetails("Defect Details 1");
        defect1.setStepsToReproduce("Steps to Reproduce 1");
        defect1.setPriority("High");
        defect1.setDetectedOn(detectedOn);
        defect1.setExpectedResolution(detectedOn.plusDays(2));
        defect1.setReportedByTesterId("test-123");
        defect1.setAssignedToDeveloperId(assignedToDeveloperId);
        defect1.setSeverity("Blocking");
        defect1.setStatus("New");
        defect1.setProjectCode(projectCode);
        mockDefects.add(defect1);
        when(defectRepository.findByProjectId(projectCode)).thenReturn(true);
        when(defectRepository.getProject(projectCode)).thenReturn(mockDefects);

        // Execution
        List<DefectDto> result = defectDtoService.getReport(projectCode);

        // Assertion
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        DefectDto defectDto1 = result.get(0);
        Assertions.assertEquals(defect1.getId(), defectDto1.getId());
        Assertions.assertEquals(defect1.getTitle(), defectDto1.getTitle());
        Assertions.assertEquals(defect1.getDefectDetails(), defectDto1.getDefectDetails());
        Assertions.assertEquals(defect1.getStepsToReproduce(), defectDto1.getStepsToReproduce());
        Assertions.assertEquals(defect1.getPriority(), defectDto1.getPriority());
        Assertions.assertEquals(defect1.getDetectedOn(), defectDto1.getDetectedOn());
        Assertions.assertEquals(defect1.getExpectedResolution(), defectDto1.getExpectedResolution());
        Assertions.assertEquals(defect1.getReportedByTesterId(), defectDto1.getReportedByTesterId());
        Assertions.assertEquals(defect1.getAssignedToDeveloperId(), defectDto1.getAssignedToDeveloperId());
        Assertions.assertEquals(defect1.getSeverity(), defectDto1.getSeverity());
        Assertions.assertEquals(defect1.getStatus(), defectDto1.getStatus());
        Assertions.assertEquals(defect1.getProjectCode(), defectDto1.getProjectCode());
    }
    

	@Test
	public void deleteById_existingDefect_shouldReturnDefect() {
		// Arrange
		int id = 1;
		Defect defect = new Defect();
		defect.setId(id);
		Optional<Defect> optionalDefect = Optional.of(defect);
		when(defectRepository.findById(id)).thenReturn(optionalDefect);

		// Act
		Defect result = defectDtoService.deleteById(id);

		// Assert
		assertEquals(defect, result);
		verify(defectRepository, times(1)).deleteById(id);
	}

	@Test
	public void deleteById_nonExistingDefect_shouldReturnNull() {
		// Arrange
		int id = 1;
		when(defectRepository.findById(id)).thenReturn(Optional.empty());

		// Act
		Defect result = defectDtoService.deleteById(id);

		// Assert
		assertEquals(null, result);
		verify(defectRepository, times(0)).deleteById(id);
	}

//	@Test
//	public void deleteById_nullId_shouldThrowResourceNotfoundException() {
//		// Arrange
//		int id = 0;
//
//		// Act & Assert
//		ResourceNotfoundException exception = 
//			org.junit.jupiter.api.Assertions.assertThrows(ResourceNotfoundException.class, 
//				() -> defectDtoService.deleteById(id));
//		assertEquals("Defect", exception.getFieldName());
//		assertEquals(id, exception.getFieldValue());
//		assertEquals("ID", exception.getResouceName());
//	}
	
    @Test
    public void testExpected() {
        LocalDate detectedOn = LocalDate.now();
        String priority = "high";
        String severity = "blocking";
        LocalDate expectedResolution = DefectDtoService.expected(priority, severity, detectedOn);
        Assertions.assertNotNull(expectedResolution);
       
    }

}

