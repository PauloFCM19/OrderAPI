package com.github.PauloFCM19.order.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.PauloFCM19.order.domain.model.State;
import com.github.PauloFCM19.order.domain.repository.StateRepository;
import com.github.PauloFCM19.order.domain.service.RegisterStateService;

@RestController
@RequestMapping(value ="/states")
public class StateController {
	
	@Autowired
	private RegisterStateService registerStateService;
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping
	private List<State> findAll(){
		return stateRepository.findAll();
	}

}
