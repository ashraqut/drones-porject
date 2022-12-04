package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.model.Medication;
import com.example.demo.model.MedicationDTO;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
	MedicationDTO modelToDTO(Medication med);

	List<MedicationDTO> modelsToDTO(List<Medication> meds);
}
