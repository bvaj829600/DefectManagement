package com.defects_management.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateDefectDtoTest {

    @InjectMocks
    private UpdateDefectDto updateDefectDto;

    @Test
    public void testGetSetResolution() {
        // Given
        String resolution = "resolution";

        // When
        updateDefectDto.setResolution(resolution);

        // Then
        assertEquals(resolution, updateDefectDto.getResolution());
    }

    @Test
    public void testGetSetResolutionDate() {
        // Given
        LocalDate resolutionDate = LocalDate.now().minusDays(1);

        // When
        updateDefectDto.setResolutionDate(resolutionDate);

        // Then
        assertEquals(resolutionDate, updateDefectDto.getResolutionDate());
    }

    @Test
    public void testGetSetStatus() {
        // Given
        String status = "status";

        // When
        updateDefectDto.setStatus(status);

        // Then
        assertEquals(status, updateDefectDto.getStatus());
    }
}

