package com.webic.QuinielasAPI.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EquiposDto {

	private String nombre;
	private String rutaImagen;
	private MultipartFile foto;

}
