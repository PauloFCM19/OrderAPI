package com.github.PauloFCM19.order.domain.exception;

public class StateNotFindExcepetion extends RuntimeException{

		private static final long serialVersionUID = 1L;

		public StateNotFindExcepetion(String message) {
			super(message);
		}

		public StateNotFindExcepetion(Long StateId) {
			this(String.format("Não existe um cadastro de Fabricante com código %d", StateId));
		}

		
}
