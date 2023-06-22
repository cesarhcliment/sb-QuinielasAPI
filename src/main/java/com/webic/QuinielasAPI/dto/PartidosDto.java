package com.webic.QuinielasAPI.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PartidosDto {

	private int jornada;
	private LocalDate fecha;
	private int orden;
	private int idlocal;
	private int idvisitante;
	private int goleslocal;
	private int golesvisitante;

}
