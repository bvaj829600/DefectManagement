package com.defects_management.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ResolutionsTest {

    @Mock
    Resolutions resolutions;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetId() {
        Mockito.when(resolutions.getId()).thenReturn(1);
        assertEquals(1, resolutions.getId());
    }

    @Test
    public void testGetResolution() {
        Mockito.when(resolutions.getResolution()).thenReturn("Fixed");
        assertEquals("Fixed", resolutions.getResolution());
    }

    @Test
    public void testGetResolutionDate() {
        LocalDate date = LocalDate.now();
        Mockito.when(resolutions.getResolutionDate()).thenReturn(date);
        assertEquals(date, resolutions.getResolutionDate());
    }
}
