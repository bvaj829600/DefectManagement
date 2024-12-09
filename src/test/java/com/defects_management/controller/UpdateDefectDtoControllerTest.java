package com.defects_management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.defects_management.dto.UpdateDefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.service.UpdateDefectService;

@ExtendWith(MockitoExtension.class)
public class UpdateDefectDtoControllerTest {
	
	@InjectMocks
	private updateDefectDtoController updateDefectDtoController;
	
	@Mock
	private UpdateDefectService updateDefectService;
	
	@Test
	public void testUpdateDefect() {
		// Arrange
		int id = 1;
		UpdateDefectDto updateDefectDto = new UpdateDefectDto();
		Defect defect = new Defect();
		String jsonResponse = "{\"message\":\"Defect Updated Successfully\"}";
		ResponseEntity<String> expectedResponse = new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
		ResponseEntity<String> actualResponse = null;
		
		
		// Act
		actualResponse = updateDefectDtoController.updateDefect(id, updateDefectDto);
		
		// Assert
		verify(updateDefectService).update(id, updateDefectDto);
		assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
		assertEquals(expectedResponse.getBody(), actualResponse.getBody());
	}
}
