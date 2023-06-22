package com.webic.QuinielasAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webic.QuinielasAPI.dto.EquiposDto;
import com.webic.QuinielasAPI.dto.Mensaje;
import com.webic.QuinielasAPI.entity.Equipos;
import com.webic.QuinielasAPI.service.EquiposService;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/equipos")
public class EquiposController {
	
	@Autowired
	private EquiposService equiposService;

	@GetMapping("/list")
	public ResponseEntity<List<Equipos>> listaEquipos() {
		List<Equipos> lista = equiposService.getEquipos();
		return new ResponseEntity<List<Equipos>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<?> datosEquipo(@PathVariable("id") int id) {
		if (!equiposService.existe(id)) {
			return new ResponseEntity<>(new Mensaje("No existe el id "+id), HttpStatus.NOT_FOUND);
		}
		Equipos equipo = equiposService.getEquipo(id);
		return new ResponseEntity<Equipos>(equipo, HttpStatus.OK);
	}

	@PostMapping("/new")
	public ResponseEntity<?> nuevo (@RequestBody EquiposDto equiposDto) {
		if (equiposService.existeNombre(equiposDto.getNombre())) {
			return new ResponseEntity<>(new Mensaje("Ese equipo ya existe"), HttpStatus.BAD_REQUEST);
		}
		Equipos equipo = new Equipos();
		equipo.setNombre(equiposDto.getNombre());
		Equipos nuevoEquipo = equiposService.save(equipo);
		return new ResponseEntity<>(new Mensaje("Equipo creado con id "+nuevoEquipo.getId()), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> modificar (@PathVariable("id") int id, @RequestBody EquiposDto equiposDto) {
		if (!equiposService.existe(id)) {
			return new ResponseEntity<>(new Mensaje("No existe el id "+id), HttpStatus.NOT_FOUND);
		}
		Equipos equipo = new Equipos();
		equipo.setId(id);
		equipo.setNombre(equiposDto.getNombre());
		equiposService.save(equipo);
		return new ResponseEntity<>(new Mensaje("Equipo modificado"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> borrar (@PathVariable("id") int id) {
		if (!equiposService.existe(id)) {
			return new ResponseEntity<>(new Mensaje("No existe el id "+id), HttpStatus.NOT_FOUND);
		}
		equiposService.delete(id);
		return new ResponseEntity<>(new Mensaje("Equipo eliminado"), HttpStatus.OK);
	}
	
}
