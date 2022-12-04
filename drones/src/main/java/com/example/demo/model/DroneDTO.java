package com.example.demo.model;

import com.example.demo.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneDTO {

	private String serialNum;
	private Double batteryCap;
	private State state;
	private String message;
	private Long weightLeftToUse;
}
