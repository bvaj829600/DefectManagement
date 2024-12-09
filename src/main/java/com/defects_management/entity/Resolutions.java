package com.defects_management.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Resolutions")
public class Resolutions {

	@Id
	@Column(name = "Resolution_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String resolution;

	private LocalDate resolutionDate = LocalDate.now();

}
