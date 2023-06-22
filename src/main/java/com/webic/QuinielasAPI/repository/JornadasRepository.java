package com.webic.QuinielasAPI.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webic.QuinielasAPI.entity.Jornadas;

@Repository
public interface JornadasRepository extends JpaRepository<Jornadas, Integer> {
	boolean existsByJornada(int jornada);
	boolean existsByFecha(LocalDate fecha);
}
