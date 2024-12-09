package com.defects_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defects_management.dto.UpdateDefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.service.UpdateDefectService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info=@Info(title="updateDefect",description="This is DefectManagement/developer/updateResolutionsModule"))
@RestController
@CrossOrigin(origins = "*")
public class updateDefectDtoController {

	@Autowired
	private UpdateDefectService updateDefectService;

	@PutMapping("/updateDefect/{id}")
	ResponseEntity<String>  updateDefect(@PathVariable("id") int id, @RequestBody UpdateDefectDto updateDefectDto) {
	     updateDefectService.update(id, updateDefectDto);
	     String jsonResponse = "{\"message\":\"Defect Updated Successfully\"}";
	     return new ResponseEntity(jsonResponse, HttpStatus.ACCEPTED);
	}

}
