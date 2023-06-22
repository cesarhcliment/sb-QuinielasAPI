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

import com.webic.QuinielasAPI.dto.Mensaje;
import com.webic.QuinielasAPI.dto.PartidosDto;
import com.webic.QuinielasAPI.dto.PartidosListaDto;
import com.webic.QuinielasAPI.entity.Partidos;
import com.webic.QuinielasAPI.service.EquiposService;
import com.webic.QuinielasAPI.service.JornadasService;
import com.webic.QuinielasAPI.service.PartidosService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/partidos")
public class PartidosController {

	@Autowired
	private PartidosService partidosService;
	
	@Autowired
	private JornadasService jornadasService;
	
	@Autowired
	private EquiposService equiposService;
	
	@GetMapping("/list")
	public ResponseEntity<List<PartidosListaDto>> listaPartidos() {
		List<PartidosListaDto> lista = partidosService.listaPartidos();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<?> datosPartido(@PathVariable("id") int id) {
		if (!partidosService.existe(id))
			return new ResponseEntity<>(new Mensaje("No existe el partido "+id), HttpStatus.NOT_FOUND);
		Partidos partido = partidosService.getPartido(id);
		return new ResponseEntity<>(partido, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> nuevo(@RequestBody PartidosDto partidoDto) {
		if (!jornadasService.existeJornada(partidoDto.getJornada())) {
			return new ResponseEntity<>(new Mensaje("No existe ese número de jornada "+partidoDto.getJornada()), HttpStatus.BAD_REQUEST);
		}
		if (!jornadasService.existeFecha(partidoDto.getFecha())) {
			return new ResponseEntity<>(new Mensaje("No existe esa fecha de jornada "+partidoDto.getFecha()), HttpStatus.BAD_REQUEST);
		}
		if (!equiposService.existe(partidoDto.getIdlocal())) {
			return new ResponseEntity<>(new Mensaje("No existe es equipo local "+partidoDto.getIdlocal()), HttpStatus.BAD_REQUEST);
		}
		if (!equiposService.existe(partidoDto.getIdvisitante())) {
			return new ResponseEntity<>(new Mensaje("No existe es equipo visitante "+partidoDto.getIdvisitante()), HttpStatus.BAD_REQUEST);
		}
		
		Partidos nuevoPartido = partidosService.save(0, partidoDto);
		return new ResponseEntity<>(new Mensaje("Partido creado con id "+nuevoPartido.getId()), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> modificar(@PathVariable("id") int id, @RequestBody PartidosDto partidoDto) {
		if (!partidosService.existe(id))
			return new ResponseEntity<>(new Mensaje("No existe el partido "+id), HttpStatus.NOT_FOUND);

		if (!jornadasService.existeJornada(partidoDto.getJornada())) {
			return new ResponseEntity<>(new Mensaje("No existe ese número de jornada "+partidoDto.getJornada()), HttpStatus.BAD_REQUEST);
		}
		if (!jornadasService.existeFecha(partidoDto.getFecha())) {
			return new ResponseEntity<>(new Mensaje("No existe esa fecha de jornada "+partidoDto.getFecha()), HttpStatus.BAD_REQUEST);
		}
		if (!equiposService.existe(partidoDto.getIdlocal())) {
			return new ResponseEntity<>(new Mensaje("No existe es equipo local "+partidoDto.getIdlocal()), HttpStatus.BAD_REQUEST);
		}
		if (!equiposService.existe(partidoDto.getIdvisitante())) {
			return new ResponseEntity<>(new Mensaje("No existe es equipo visitante "+partidoDto.getIdvisitante()), HttpStatus.BAD_REQUEST);
		}
		
		partidosService.save(id, partidoDto);
		return new ResponseEntity<>(new Mensaje("Partido actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
		if (!partidosService.existe(id))
			return new ResponseEntity<>(new Mensaje("No existe el partido "+id), HttpStatus.NOT_FOUND);
		partidosService.delete(id);
		return new ResponseEntity<>(new Mensaje("Partido eliminado"), HttpStatus.OK);
	}

}
