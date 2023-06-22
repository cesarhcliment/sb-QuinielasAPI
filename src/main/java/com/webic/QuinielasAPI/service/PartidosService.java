package com.webic.QuinielasAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webic.QuinielasAPI.dto.PartidosDto;
import com.webic.QuinielasAPI.dto.PartidosListaDto;
import com.webic.QuinielasAPI.entity.Partidos;
import com.webic.QuinielasAPI.repository.PartidosRepository;

@Service
public class PartidosService {

	@Autowired
	private PartidosRepository partidosRepository;
	
	public List<Partidos> getPartidos() {
		return partidosRepository.findAll();
	}
	
	public List<PartidosListaDto> listaPartidos() {
		return partidosRepository.listaPartido();
	}

	public Partidos getPartido(int id) {
		return partidosRepository.findById(id).orElse(null);
	}

	public boolean existe(int id) {
		return partidosRepository.existsById(id);
	}
	
	// Crear y modificar
	public Partidos save(int id, PartidosDto partidoDto) {
		
		Partidos partido = new Partidos();
		if (id != 0) {
			partido.setId(id);
		}
		partido.setJornada(partidoDto.getJornada());
		partido.setFecha(partidoDto.getFecha());
		partido.setOrden(partidoDto.getOrden());
		partido.setIdlocal(partidoDto.getIdlocal());
		partido.setIdvisitante(partidoDto.getIdvisitante());
		partido.setGoleslocal(partidoDto.getGoleslocal());
		partido.setGolesvisitante(partidoDto.getGolesvisitante());
		
		return partidosRepository.save(partido);
	}
	
	public void delete(int id) {
		partidosRepository.deleteById(id);
	}
	
}
