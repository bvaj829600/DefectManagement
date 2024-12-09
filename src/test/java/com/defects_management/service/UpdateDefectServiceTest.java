package com.defects_management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.defects_management.dto.UpdateDefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.entity.Resolutions;
import com.defects_management.exception.ResourceNotfoundException;
import com.defects_management.repository.DefectRepository;

public class UpdateDefectServiceTest {

    @Mock
    private DefectRepository defectRepository;

    @InjectMocks
    private UpdateDefectService updateDefectService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateDefect() {
        // Arrange
        UpdateDefectDto updateDefectDto = new UpdateDefectDto();
        updateDefectDto.setStatus("Fixed");
        updateDefectDto.setResolution("Test resolution");
        updateDefectDto.setResolutionDate(LocalDate.now());

        Defect defect = new Defect();
        defect.setId(1);
        defect.setStatus("Open");
        List<Resolutions> resolutions = new ArrayList<>();
        defect.setResolutions(resolutions);

        when(defectRepository.findById(1)).thenReturn(Optional.of(defect));
        when(defectRepository.save(defect)).thenReturn(defect);

        // Act
        Defect updatedDefect = updateDefectService.update(1, updateDefectDto);

        // Assert
        assertNotNull(updatedDefect);
        assertEquals("Fixed", updatedDefect.getStatus());
        assertEquals(1, updatedDefect.getId());
        assertEquals(1, updatedDefect.getResolutions().size());
        assertEquals("Test resolution", updatedDefect.getResolutions().get(0).getResolution());
        assertEquals(LocalDate.now(), updatedDefect.getResolutions().get(0).getResolutionDate());
    }

    @Test
    public void testUpdateDefectNotFound() {
        // Arrange
        UpdateDefectDto updateDefectDto = new UpdateDefectDto();
        updateDefectDto.setStatus("Fixed");
        updateDefectDto.setResolution("Test resolution");
        updateDefectDto.setResolutionDate(LocalDate.now());

        when(defectRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotfoundException.class, () -> {
            updateDefectService.update(1, updateDefectDto);
        });
    }
}
