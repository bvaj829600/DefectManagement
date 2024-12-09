package com.defects_management.service;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defects_management.dto.UpdateDefectDto;
import com.defects_management.entity.Defect;
import com.defects_management.entity.Resolutions;
import com.defects_management.exception.ResourceNotfoundException;
import com.defects_management.repository.DefectRepository;

@Service
public class UpdateDefectService {

	@Autowired
	private DefectRepository defectRepository;
	
	public Defect update(int defectId, UpdateDefectDto updateDefectDto) {
        Defect defect = defectRepository.findById(defectId).orElseThrow(() -> new ResourceNotfoundException("Defect",defectId,"defectId"));

        defect.setStatus(updateDefectDto.getStatus());
        List<Resolutions> resolutions = defect.getResolutions();
        if (resolutions == null) {
            resolutions = new ArrayList<>();
        }
        Resolutions resolution = new Resolutions();
        resolution.setResolution(updateDefectDto.getResolution());
        resolution.setResolutionDate(updateDefectDto.getResolutionDate());
        resolutions.add(resolution);
        defect.setResolutions(resolutions);

        return defectRepository.save(defect);
    }
	
	
	
//	public Defect update(int defectid, UpdateDefectDto updateDefectDto) {
//		Defect defect=defectRepository.findById(defectid).orElseThrow(() -> new ResourceNotfoundException("Defect",defectid,"defectid"));
//		Defect defect2=defect;
//		defect2.setStatus(updateDefectDto.getStatus());
//		List<Resolutions> listobj=getlist(updateDefectDto.getResolution(),updateDefectDto.getResolutionDate(),defect2.getResolutions());
//		defect2.setResolutions(listobj);
//		return defectRepository.save(defect2);
//	}
//	
//	public List<Resolutions> getlist(String resolutions,LocalDate gdate,List<Resolutions> listobj1){
//		Resolutions obj=new Resolutions();
//		obj.setResolution(resolutions);
//		obj.setResolutionDate(gdate);
//		List<Resolutions> listobj = listobj1;
//		listobj.add(obj);
//		return listobj;
//	}

}
