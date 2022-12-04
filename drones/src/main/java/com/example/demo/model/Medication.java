package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class Medication {
	@Id
	private Long id;

	private String name;
	private Long weight;
	private String code;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "drone_id", nullable = false)
	private Drone drone;
}
