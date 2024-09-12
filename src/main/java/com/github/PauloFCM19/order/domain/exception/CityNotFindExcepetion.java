package com.github.PauloFCM19.order.domain.exception;

public class CityNotFindExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CityNotFindExcepetion(String message) {
		super(message);
	
	}

	public CityNotFindExcepetion(Long cityId) {
		this(String.format("Não existe um cadastro de Fabricante com código %d", cityId));
	
	}
	
	
}
