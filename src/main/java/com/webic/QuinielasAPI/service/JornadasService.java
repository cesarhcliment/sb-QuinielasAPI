package com.webic.QuinielasAPI.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webic.QuinielasAPI.entity.Jornadas;
import com.webic.QuinielasAPI.repository.JornadasRepository;

@Service
public class JornadasService {

	@Autowired
	private JornadasRepository jornadasRepository;
	
	public List<Jornadas> getJornadas() {
		//return jornadasRepository.findAll();
		return jornadasRepository.findAllByOrderByJornadaAscFechaAsc();
		//Hibernate: select j1_0.id,j1_0.fecha,j1_0.jornada from jornadas j1_0 order by j1_0.jornada,j1_0.fecha
	}

	public Jornadas getJornada(int id) {
		return jornadasRepository.findById(id).orElse(null);
	}
	
	public boolean existe(int id) {
		return jornadasRepository.existsById(id);
	}
	
	public boolean existeJornada(int jornada) {
		return jornadasRepository.existsByJornada(jornada);
	}

	public boolean existeFecha(LocalDate fecha) {
		return jornadasRepository.existsByFecha(fecha);
	}

	public Jornadas save(Jornadas jornada) {
		return jornadasRepository.save(jornada);
	}
	
	public void delete(int id) {
		jornadasRepository.deleteById(id);
	}
}
