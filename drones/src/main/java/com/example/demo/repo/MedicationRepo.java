package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Medication;

public interface MedicationRepo extends JpaRepository<Medication, String> {

	@Query(value = "select * from Medication m where m.drone_id = :droneId",nativeQuery = true)
	List<Medication> findByDroneId(String droneId);
}
