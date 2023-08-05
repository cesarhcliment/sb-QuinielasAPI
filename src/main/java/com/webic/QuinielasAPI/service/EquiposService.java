package com.webic.QuinielasAPI.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webic.QuinielasAPI.entity.Equipos;
import com.webic.QuinielasAPI.excepciones.ImagesException;
import com.webic.QuinielasAPI.repository.EquiposRepository;

import jakarta.annotation.PostConstruct;

@Service
public class EquiposService {

	@Value("${storage.location}")
	private String storageLocation;

	@Autowired
	private EquiposRepository equiposRepository;

	@PostConstruct
	public void iniciarAssets() {
		try {
			String[] parts = storageLocation.split("/"); // assets/img/equipos/
			String ruta = "";
			for (int i = 0; i < parts.length; i++) {
				ruta = ruta + parts[i];
				Files.createDirectories(Paths.get(ruta));
				ruta = ruta + "/";
        		}
		} catch (IOException excepcion) {
			throw new ImagesException("Error al iniciar el almacen de archivos");
		}
	}
	
	public String almacenarArchivo(MultipartFile archivo) {
		String nombreArchivo = archivo.getOriginalFilename();
		if (archivo.isEmpty()) {
			throw new ImagesException("No se puede almacenar un archivo vacio");
		}
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);

        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif")) {
        	throw new ImagesException("El archivo debe ser de tipo PNG, JPG o GIF");
        }
		
		try {
			InputStream inputStream  = archivo.getInputStream();
			Files.copy(inputStream,Paths.get(storageLocation).resolve(nombreArchivo),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException excepcion) {
			throw new ImagesException("Error al almacenar el archivo " + nombreArchivo,excepcion);
		}
		return nombreArchivo;
	}
	
	
	public List<Equipos> getEquipos() {
		//return equiposRepository.findAll(); 
			//Hibernate: select e1_0.id,e1_0.nombre from equipos e1_0
		//return equiposRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
			//Hibernate: select e1_0.id,e1_0.nombre from equipos e1_0 order by e1_0.nombre
		return equiposRepository.findAllByOrderByNombreAsc();
			//Hibernate: select e1_0.id,e1_0.nombre from equipos e1_0 order by e1_0.nombre
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
