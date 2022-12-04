package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.enums.Model;
import com.example.demo.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Drone {

	@Id
	@Column(name = "SERIAL_NUMBER", unique = true, nullable = false, length = 100)
	private String serialNum;
	@Column(name = "MODEL")
	@Enumerated(EnumType.ORDINAL)
	private Model model;
	@Column(name = "WEIGHT")
	private Long weight;
	@Column(name = "BATTERY_CAPACITY", precision = 3, scale = 2)
	private Double batteryCap;
	@Column(name = "STATE")
	@Enumerated(EnumType.ORDINAL)
	private State state;
	@OneToMany(mappedBy = "drone")
	private List<Medication> medications;
	@Column(name = "WEIGHT_LEFT_TO_USE")
	private Long weightLeftToUse;

}
