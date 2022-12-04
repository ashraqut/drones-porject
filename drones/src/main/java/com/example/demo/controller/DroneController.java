package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AvailableDronesForLoadingDTO;
import com.example.demo.model.Drone;
import com.example.demo.model.DroneDTO;
import com.example.demo.model.Medication;
import com.example.demo.model.MedicationDTO;
import com.example.demo.service.DroneService;

@RestController
@RequestMapping("/drones")
public class DroneController {
	@Autowired
	DroneService droneService;

	@PostMapping(value = "/register-drone")
	public ResponseEntity<String> registerDrone(@RequestBody Drone drone) {
		String d = droneService.droneRegisteration(drone);
		return new ResponseEntity<>(d, org.springframework.http.HttpStatus.OK);
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Drone>> getAll() {
		List<Drone> dronesList = droneService.getAllDrones();
		return new ResponseEntity<>(dronesList, org.springframework.http.HttpStatus.OK);
	}

	@PostMapping(value = "/loadDrone")
	public ResponseEntity<DroneDTO> loadDrone(@RequestBody Medication medication, @RequestParam String droneId) {
		DroneDTO d = droneService.loadDrone(medication, droneId);
		return new ResponseEntity<>(d, org.springframework.http.HttpStatus.OK);
	}

	@GetMapping(value = "/getMedicationsByDroneId")
	public ResponseEntity<List<MedicationDTO>> getMedicationsByDroneId(@RequestParam String droneId) {
		List<MedicationDTO> medications = droneService.getMedicationsByDroneId(droneId);
		return new ResponseEntity<>(medications, org.springframework.http.HttpStatus.OK);
	}

	@GetMapping(value = "/availableDronesForLoading")
	public ResponseEntity<AvailableDronesForLoadingDTO> getAvailableDronesForLoading() {
		return new ResponseEntity<>(droneService.getAvailableDronesForLoading(),
				org.springframework.http.HttpStatus.OK);
	}

	@GetMapping(value = "/batteryLevel")
	public ResponseEntity<Double> getBatteryLevelForDrone(@RequestParam String droneId) {
		return new ResponseEntity<>(droneService.getBatteryLevel(droneId), org.springframework.http.HttpStatus.OK);
	}
}
