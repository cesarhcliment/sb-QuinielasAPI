package com.webic.QuinielasAPI.excepciones;

public class ImagesException extends RuntimeException {

	public ImagesException(String mensaje) {
		super(mensaje);
	}

	public ImagesException(String mensaje, Throwable excepcion) {
		super(mensaje, excepcion);
	}

}
