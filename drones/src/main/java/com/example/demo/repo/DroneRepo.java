package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Drone;

public interface DroneRepo extends JpaRepository<Drone, String> {
	Drone findBySerialNum(String serialNumber);

}
