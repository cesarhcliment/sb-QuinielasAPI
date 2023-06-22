package com.webic.QuinielasAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webic.QuinielasAPI.entity.Equipos;
import com.webic.QuinielasAPI.repository.EquiposRepository;

@Service
public class EquiposService {

	@Autowired
	private EquiposRepository equiposRepository;
	
	public List<Equipos> getEquipos() {
		return equiposRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}
	
	public Equipos getEquipo(int id) {
		return equiposRepository.findById(id).orElse(null);
	}
	
	public boolean existe(int id) {
		return equiposRepository.existsById(id);
	}

	public boolean existeNombre(String nombre) {
		return equiposRepository.existsByNombre(nombre);
	}
	
	public Equipos save(Equipos equipo) {
		return equiposRepository.save(equipo);
	}
	
	public void delete(int id) {
		equiposRepository.deleteById(id);
	}
}
