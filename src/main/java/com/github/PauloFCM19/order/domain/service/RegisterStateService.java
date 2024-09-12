package com.github.PauloFCM19.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.PauloFCM19.order.domain.exception.ManufacturerNotFindException;
import com.github.PauloFCM19.order.domain.model.State;
import com.github.PauloFCM19.order.domain.repository.StateRepository;

@Service
public class RegisterStateService {

	@Autowired
	private StateRepository stateRepository;
	
	
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	
	@Transactional
	public void delete(Long stateId) {
		try {
			stateRepository.deleteById(stateId);
			stateRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ManufacturerNotFindException(stateId);
		}
	
	}
	
	public State findOrFail(Long stateId) {
		return stateRepository.findById(stateId)
			.orElseThrow(() -> new ManufacturerNotFindException(stateId));
	}
}
