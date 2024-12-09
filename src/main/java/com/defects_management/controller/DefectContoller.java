package com.defects_management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.defects_management.dto.DefectDetailsDto;
import com.defects_management.dto.DefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.service.DefectDetailsDtoService;
import com.defects_management.service.DefectDtoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;





@OpenAPIDefinition(info=@Info(title="DefectManagement",description="This is DefectManagement and all service add-defect, developer-search-defectby-Defectid and projectmanager-geReportByDefectID"))
@RestController
@CrossOrigin(origins = "*")
public class DefectContoller {

	@Autowired
	private DefectDtoService defectDtoService;

	@Autowired
	private DefectDetailsDtoService defectDetailsDtoService;

	@PostMapping("/addDefect")
	private ResponseEntity<DefectDto> add(@RequestBody DefectDto defectDto) {
		DefectDto defect1=defectDtoService.AddDefect(defectDto);
		if(defect1!=null)
		{	
		return new ResponseEntity(("{\"message\":\"" + "Defect Added Succesfully" + "\"}"), HttpStatus.CREATED);
		}
		else
			return new ResponseEntity(("{\"error\":\"" + "per day 5 defects assigned to developer" + "\"}"), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAllDefect")
	private  ResponseEntity<List<DefectDto>> getall() {
		return new ResponseEntity<>(defectDtoService.GetAllDefectDto(), HttpStatus.OK);
	}
	
	@GetMapping("/DefectDetails/{id}")
	private DefectDetailsDto defectDetilsById(@PathVariable("id") int id) {
		return defectDetailsDtoService.GetDetailsId(id);
	}

	@GetMapping("/getDeveloper/{assignedToDeveloperId}")
	private ResponseEntity<List<DefectDto>> getById(
			@PathVariable("assignedToDeveloperId") String assignedToDeveloperId) {
		List<DefectDto> defectDto= defectDtoService.GetAssigendDeveloper(assignedToDeveloperId);
		if(defectDto!=null) {
			return new ResponseEntity<>(defectDto,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(("{\"error\":\"" + "DeveloperID Not found" + "\"}"),HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/project/DefectReport/{projectCode}")
	private ResponseEntity<List<DefectDto>> projectReportById(@PathVariable("projectCode") int projectCode) {
		List<DefectDto> defectDto=defectDtoService.getReport(projectCode);
		if(defectDto!=null)
		{
			return new ResponseEntity<>(defectDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(("{\"error\":\"" + "projectCode Not found" + "\"}"),HttpStatus.NOT_FOUND);
		}
		

	}
	
	

	@DeleteMapping("/deleteDefect/{id}")
	private ResponseEntity<Defect> deleteById(@PathVariable("id") int id) {
		Defect defect=defectDtoService.deleteById(id);
		if(defect!=null)
		{
		return new ResponseEntity<>(defect,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(("{\"error\":\"" + "DefectID Not found" + "\"}"),HttpStatus.NOT_FOUND);
		}
	}

}
