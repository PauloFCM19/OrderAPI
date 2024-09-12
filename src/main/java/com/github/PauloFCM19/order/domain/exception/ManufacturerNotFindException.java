package com.github.PauloFCM19.order.domain.exception;

public class ManufacturerNotFindException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public ManufacturerNotFindException(Long manufacturerId) {
		this(String.format("Não existe um cadastro de Fabricante com código %d", manufacturerId));
	}

	public ManufacturerNotFindException(String message) {
		super(message);
		
	}

	
	
	

}
