package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.model.Drone;
import com.example.demo.model.DroneDTO;

@Mapper(componentModel = "spring")
public interface DroneMapper {

	DroneDTO modelToDroneDTO(Drone d);
}
