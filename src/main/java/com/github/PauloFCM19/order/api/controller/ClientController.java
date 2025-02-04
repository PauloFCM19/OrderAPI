package com.github.PauloFCM19.order.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.PauloFCM19.order.api.assembler.ClientModelAssembler;
import com.github.PauloFCM19.order.api.disassembler.ClientDisassembler;
import com.github.PauloFCM19.order.api.input.ClientInput;
import com.github.PauloFCM19.order.api.model.ClientModel;
import com.github.PauloFCM19.order.domain.exception.BusinessException;
import com.github.PauloFCM19.order.domain.exception.ManufacturerNotFindException;
import com.github.PauloFCM19.order.domain.model.Client;
import com.github.PauloFCM19.order.domain.repository.ClientRepository;
import com.github.PauloFCM19.order.domain.service.RegisterClientService;

@RestController
@RequestMapping(value = "/clientes")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RegisterClientService registerClientService;
	
	@Autowired
	private ClientModelAssembler clientModelAssembler;
	@Autowired
	private ClientDisassembler clientDisassembler;
	
	
	@GetMapping
	public List<ClientModel> findAll(){
		return clientModelAssembler
				.toColletionModel(clientRepository.findAll());
	}
	
	@GetMapping("/{manufacturerId}")
	public ClientModel findById(@PathVariable Long manufacturerId) {
		Client client = registerClientService.findOrFail(manufacturerId);
		
		return clientModelAssembler.toModel(client);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientModel create(@RequestBody ClientInput clientInput) {
		try {
			
			Client client = clientDisassembler
					.toDomainModel(clientInput);
		
			client = registerClientService.save(client);
			
			ClientModel clientModel = clientModelAssembler.toModel(client);
			
			return clientModel;
		}catch(ManufacturerNotFindException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PutMapping("/{manufacturerId}")
	public ClientModel update(@PathVariable Long manufacturerId,
							  @RequestBody ClientInput clientInput) {
		Client clientCurrency = registerClientService.findOrFail(manufacturerId);
		
		clientDisassembler.copyToDomainObject(clientInput, clientCurrency);
		
		clientCurrency = registerClientService.save(clientCurrency);
		
		
		return clientModelAssembler.toModel(clientCurrency);
	}
	
	@DeleteMapping("/{manufacturerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long manufacturerId) {
		 registerClientService.delete(manufacturerId);
	}

}