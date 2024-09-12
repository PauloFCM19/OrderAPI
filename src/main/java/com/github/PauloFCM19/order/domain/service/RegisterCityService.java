package com.github.PauloFCM19.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import com.github.PauloFCM19.order.domain.exception.CityNotFindExcepetion;
import com.github.PauloFCM19.order.domain.exception.EntityInUseException;
import com.github.PauloFCM19.order.domain.model.City;
import com.github.PauloFCM19.order.domain.model.State;
import com.github.PauloFCM19.order.domain.repository.CityRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisterCityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterStateService registerStateService;
	
	private static final String MSG_CIDADE_EM_USO 
	= "Cidade de código %d não pode ser removida, pois está em uso";
	
	@Transactional
	private City save(City city) {
		Long stateId = city.getState().getId();
		
		State state = registerStateService.findOrFail(stateId);
		
		city.setState(state);
		
		return cityRepository.save(city);
			
	}
	
	@Transactional
	public void excluir(Long cityId) {
		try {
			cityRepository.deleteById(cityId);
			cityRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFindExcepetion(cityId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_CIDADE_EM_USO, cityId));
		}
	}
	
	public City findOrFail(Long cityId) {
		return cityRepository.findById(cityId)
			.orElseThrow(() -> new CityNotFindExcepetion(cityId));
	}

}
