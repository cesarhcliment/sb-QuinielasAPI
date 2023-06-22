package com.webic.QuinielasAPI.controller;

import java.util.List;

import org.hibernate.type.descriptor.DateTimeUtils;
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

import com.webic.QuinielasAPI.dto.JornadasDto;
import com.webic.QuinielasAPI.dto.Mensaje;
import com.webic.QuinielasAPI.entity.Jornadas;
import com.webic.QuinielasAPI.service.JornadasService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jornadas")
public class JornadasController {

	@Autowired
	private JornadasService jornadasService;

	@GetMapping("/list")
	public ResponseEntity<List<Jornadas>> listaJornadas() {
		List<Jornadas> lista = jornadasService.getJornadas();
		return new ResponseEntity<List<Jornadas>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<?> datosJornada(@PathVariable int id) {
		if (!jornadasService.existe(id)) {
			return new ResponseEntity<>(new Mensaje("No existe la jornada "+id), HttpStatus.NOT_FOUND); 
		}
		Jornadas jornada = jornadasService.getJornada(id);
		return new ResponseEntity<>(jornada, HttpStatus.OK);
	}

	@PostMapping("/new")
	public ResponseEntity<?> nuevo (@RequestBody JornadasDto jornadasDto) {
		if (jornadasService.existeJornada(jornadasDto.getJornada())) {
			return new ResponseEntity<>(new Mensaje("Ya existe ese número de jornada "+jornadasDto.getJornada()), HttpStatus.BAD_REQUEST);
		}
		if (jornadasService.existeFecha(jornadasDto.getFecha())) {
			return new ResponseEntity<>(new Mensaje("Ya existe esa fecha de jornada "+jornadasDto.getFecha()), HttpStatus.BAD_REQUEST);
		}
		Jornadas jornada = new Jornadas();
		jornada.setJornada(jornadasDto.getJornada());
		jornada.setFecha(jornadasDto.getFecha());
		Jornadas nuevaJornada = jornadasService.save(jornada);
		return new ResponseEntity<>(new Mensaje("Jornada creada con id "+nuevaJornada.getId()), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> modificar (@PathVariable("id") int id, @RequestBody JornadasDto jornadasDto) {
		if (!jornadasService.existe(id)) {
			return new ResponseEntity<>(new Mensaje("No existe la jornada "+id), HttpStatus.NOT_FOUND); 
		}
		// Leer la jornada a modificar
		// Si hay cambio en la jornada, comprobar que no existe una jornada con ese numero
		Jornadas OLDJornada = jornadasService.getJornada(id);
		if (OLDJornada.getJornada() != jornadasDto.getJornada()) {
			if (jornadasService.existeJornada(jornadasDto.getJornada())) {
				return new ResponseEntity<>(new Mensaje("Ya existe ese número de jornada "+jornadasDto.getJornada()), HttpStatus.BAD_REQUEST);
			}
		}
		// Si hay cambio en la fecha de la jornada, comprobar que no existe una jornada con esa fecha
		if (!OLDJornada.getFecha().equals(jornadasDto.getFecha())) {
			if (jornadasService.existeFecha(jornadasDto.getFecha())) {
				return new ResponseEntity<>(new Mensaje("Ya existe esa fecha de jornada "+jornadasDto.getFecha()), HttpStatus.BAD_REQUEST);
			}
		}
		Jornadas jornada = new Jornadas();
		jornada.setId(id);
		jornada.setJornada(jornadasDto.getJornada());
		jornada.setFecha(jornadasDto.getFecha());
		jornadasService.save(jornada);
		return new ResponseEntity<>(new Mensaje("Jornada modificada"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminar (@PathVariable("id") int id) {
		if (!jornadasService.existe(id)) {
			return new ResponseEntity<>(new Mensaje("No existe la jornada "+id), HttpStatus.NOT_FOUND); 
		}
		jornadasService.delete(id);
		return new ResponseEntity<>(new Mensaje("Jornada eliminada"), HttpStatus.OK);
	}
}
