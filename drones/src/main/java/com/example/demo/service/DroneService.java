package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.State;
import com.example.demo.mapper.DroneMapper;
import com.example.demo.mapper.MedicationMapper;
import com.example.demo.model.AvailableDronesForLoadingDTO;
import com.example.demo.model.Drone;
import com.example.demo.model.DroneDTO;
import com.example.demo.model.Medication;
import com.example.demo.model.MedicationDTO;
import com.example.demo.repo.DroneRepo;
import com.example.demo.repo.MedicationRepo;

@Service
public class DroneService {
	@Autowired
	private DroneRepo droneRepo;
	@Autowired
	private MedicationRepo medicationRepo;
	@Autowired
	private DroneMapper droneMapper;
	@Autowired
	private MedicationMapper medMapper;
	public final String medCodeRegex = "[A-Z0-9_]*";
	public final String medNameRegex = "[a-zA-Z0-9_-]*";

	public String droneRegisteration(Drone d) {
		if (d.getWeight() > 500)
			return ("weight can't be more 500");
		d.setWeightLeftToUse(d.getWeight());
		if (d.getState() == null)
			d.setState(State.IDLE);
		if (droneRepo.findBySerialNum(d.getSerialNum()) != null)
			return ("serial number already exist");
		droneRepo.save(d);
		return "Drone added successfully";
	}

	public List<Drone> getAllDrones() {
		return (List<Drone>) droneRepo.findAll();
	}

	public List<MedicationDTO> getMedicationsByDroneId(String droneId) {
		List<Medication> medicationList = medicationRepo.findByDroneId(droneId);
		return medMapper.modelsToDTO(medicationList);
	}

	public DroneDTO loadDrone(Medication m, String droneId) {
		Drone d = droneRepo.findBySerialNum(droneId);
		DroneDTO droneDTO = droneMapper.modelToDroneDTO(d);
		droneDTO.setMessage("Drone can't be loaded by this weight");
		boolean canbeAdded = true;
		boolean goodInput = true;
		List<Medication> medicationList = d.getMedications();
		for (Medication med : medicationList) {
			if (med.getId() == m.getId())
				canbeAdded = false;
		}
		if (!Pattern.matches(medNameRegex, m.getName())) {
			droneDTO.setMessage("incorrect medication name format");
			goodInput = false;
		}
		if (!Pattern.matches(medCodeRegex, m.getCode())) {
			droneDTO.setMessage("incorrect medication code format");
			goodInput = false;
		}
		if (d.getWeightLeftToUse() >= m.getWeight() && canbeAdded && goodInput) {

			medicationList.add(m);
			d.setMedications(medicationList);
			d.setWeightLeftToUse(d.getWeightLeftToUse() - m.getWeight());
			m.setDrone(d);
			medicationRepo.save(m);
			droneDTO = droneMapper.modelToDroneDTO(d);
			droneDTO.setMessage("Drone loaded successfully");
		}
		return droneDTO;
	}

	public AvailableDronesForLoadingDTO getAvailableDronesForLoading() {
		List<Drone> allDrones = getAllDrones();
		List<Drone> result = new ArrayList<Drone>();
		AvailableDronesForLoadingDTO a = new AvailableDronesForLoadingDTO();

		for (Drone d : allDrones) {
			if (d.getBatteryCap().compareTo(25.0) > 0 && d.getWeightLeftToUse() > 0)
				result.add(d);
		}
		a.setDrones(result);
		if (result != null && result.size() > 0) {

			a.setMessage("available drones for loading are " + result.size() + " drones");
		} else {
			a.setMessage("there's no available drones for loading");

		}
		return a;
	}

	public Double getBatteryLevel(String droneId) {
		Drone d = droneRepo.findBySerialNum(droneId);
		return d.getBatteryCap();
	}
}
