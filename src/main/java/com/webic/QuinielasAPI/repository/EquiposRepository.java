package com.webic.QuinielasAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webic.QuinielasAPI.entity.Equipos;

@Repository
public interface EquiposRepository extends JpaRepository<Equipos, Integer> {
	boolean existsByNombre(String nombre);
	List<Equipos> findAllByOrderByNombreAsc();
}
