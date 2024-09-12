package com.github.PauloFCM19.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.github.PauloFCM19.order.domain.exception.ManufacturerNotFindException;
import com.github.PauloFCM19.order.domain.model.Manufacturer;
import com.github.PauloFCM19.order.domain.repository.ManufacturerRepository;

@Service
public class RegisterManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	public Manufacturer save(Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}
	
	@Transactional
	public void delete(Long manufacturerId) {
		try {
			manufacturerRepository.deleteById(manufacturerId);
			manufacturerRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ManufacturerNotFindException(manufacturerId);
		}
	
	}
	
	public Manufacturer findOrFail(Long manufacturerId) {
		return manufacturerRepository.findById(manufacturerId)
			.orElseThrow(() -> new ManufacturerNotFindException(manufacturerId));
	}
	
}
