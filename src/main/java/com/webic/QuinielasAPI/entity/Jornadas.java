package com.webic.QuinielasAPI.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jornadas")
@Data
@NoArgsConstructor
public class Jornadas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int jornada;
	private LocalDate fecha;
}
