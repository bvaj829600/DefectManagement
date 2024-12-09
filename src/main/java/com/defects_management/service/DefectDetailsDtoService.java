package com.defects_management.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defects_management.dto.DefectDetailsDto;
import com.defects_management.entity.Defect;
import com.defects_management.exception.ResourceNotfoundException;
import com.defects_management.repository.DefectRepository;

@Service
public class DefectDetailsDtoService {

	@Autowired 
	public DefectRepository defectRepository;

	@Autowired
	ModelMapper modelMapper;

	public DefectDetailsDto GetDetailsId(int id) {
		Defect defect = defectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Defect", id, "ID"));
		return modelMapper.map(defect, DefectDetailsDto.class);

	}

}
