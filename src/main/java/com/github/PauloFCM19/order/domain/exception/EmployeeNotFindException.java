package com.github.PauloFCM19.order.domain.exception;

public class EmployeeNotFindException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public EmployeeNotFindException(Long employeeId) {
		this(String.format("Não existe um cadastro de Funcionário com código %d", employeeId));
	}

	public EmployeeNotFindException(String message) {
		super(message);
		
	}

	
	
	

}
