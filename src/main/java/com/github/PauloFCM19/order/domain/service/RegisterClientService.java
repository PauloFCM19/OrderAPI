package com.github.PauloFCM19.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.github.PauloFCM19.order.domain.exception.ManufacturerNotFindException;
import com.github.PauloFCM19.order.domain.model.Client;
import com.github.PauloFCM19.order.domain.repository.ClientRepository;

@Service
public class RegisterClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client client) {
				return clientRepository.save(client);
	}
	
	@Transactional
	public void delete(Long clientId) {
		try {
			clientRepository.deleteById(clientId);
			clientRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ManufacturerNotFindException(clientId);
		}
	
	}
	
	public Client findOrFail(Long manufacturerId) {
		return clientRepository.findById(manufacturerId)
			.orElseThrow(() -> new ManufacturerNotFindException(manufacturerId));
	}
	
}