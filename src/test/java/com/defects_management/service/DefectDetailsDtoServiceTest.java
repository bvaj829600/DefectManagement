package com.defects_management.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.defects_management.dto.DefectDetailsDto;
import com.defects_management.entity.Defect;
import com.defects_management.exception.ResourceNotfoundException;
import com.defects_management.repository.DefectRepository;

@ExtendWith(MockitoExtension.class)
public class DefectDetailsDtoServiceTest {

    private DefectDetailsDtoService defectService;

    @Mock
    private DefectRepository defectRepository;

    @BeforeEach
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        defectService = new DefectDetailsDtoService();
        defectService.defectRepository = defectRepository;
        defectService.modelMapper = modelMapper;
    }

    @Test
    public void testGetDetailsId() {
        Defect defect = new Defect();
        defect.setId(1);
        defect.setTitle("Test Defect");
        defect.setDefectDetails("This is a test defect");

        when(defectRepository.findById(anyInt())).thenReturn(Optional.of(defect));

        DefectDetailsDto defectDetailsDto = defectService.GetDetailsId(1);

        Assertions.assertNotNull(defectDetailsDto);
        Assertions.assertEquals(defect.getId(), defectDetailsDto.getId());
        Assertions.assertEquals(defect.getTitle(), defectDetailsDto.getTitle());
        Assertions.assertEquals(defect.getDefectDetails(), defectDetailsDto.getDefectDetails());
    }

    @Test
    public void testGetDetailsIdWhenDefectNotFound() {
        when(defectRepository.findById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotfoundException.class, () -> {
            defectService.GetDetailsId(1);
        });
    }
}
