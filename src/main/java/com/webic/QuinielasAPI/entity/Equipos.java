package com.webic.QuinielasAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="equipos")
@Data
@NoArgsConstructor
public class Equipos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, length = 100)
	private String nombre;
	//Hibernate: alter table if exists equipos alter column nombre set data type varchar(100)

	private String rutaImagen;
}
