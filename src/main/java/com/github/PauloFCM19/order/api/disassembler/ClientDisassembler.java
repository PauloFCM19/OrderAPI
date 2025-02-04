package com.github.PauloFCM19.order.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.PauloFCM19.order.api.input.ClientInput;
import com.github.PauloFCM19.order.domain.model.Client;

@Component
public class ClientDisassembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public Client toDomainModel(ClientInput clientInput) {
		return modelMapper.map(clientInput, Client.class);
	}
	
	public void copyToDomainObject(ClientInput clientInput, Client client) {
		modelMapper.map(clientInput, client);
	}
}
