
package com.example.demo.periodic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.model.Drone;
import com.example.demo.repo.DroneRepo;

@SpringBootApplication
@EnableScheduling
public class CheckBatteryPeriodic {
	@Autowired
	private DroneRepo droneRepo;
	static final Logger log = LoggerFactory.getLogger(CheckBatteryPeriodic.class);

	@Scheduled(fixedRate = 9000)
	public void checkDroneBattery() throws InterruptedException {
		List<Drone> drones = droneRepo.findAll();
		for (Drone d : drones) {
			log.info("Drone with SerialNumber  " + d.getSerialNum() + "  Battery Level " + d.getBatteryCap() + "%");

		}
		// Thread.sleep(5000);
	}
}
