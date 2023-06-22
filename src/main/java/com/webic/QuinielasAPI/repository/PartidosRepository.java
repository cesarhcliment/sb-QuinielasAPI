package com.webic.QuinielasAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webic.QuinielasAPI.dto.PartidosListaDto;
import com.webic.QuinielasAPI.entity.Partidos;

@Repository
public interface PartidosRepository extends JpaRepository<Partidos, Integer> {

	@Query(value = "SELECT p.*, el.nombre AS local, ev.nombre AS visitante "
			+ "FROM partidos p "
			+ "INNER JOIN equipos el ON p.idlocal = el.id "
			+ "INNER JOIN equipos ev ON p.idvisitante = ev.id "
			+ "ORDER BY p.jornada DESC, p.orden ASC", nativeQuery = true)
	List<PartidosListaDto> listaPartido();
}
