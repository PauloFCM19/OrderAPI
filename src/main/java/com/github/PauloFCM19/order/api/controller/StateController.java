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

import com.github.PauloFCM19.order.api.assembler.StateModelAssembler;
import com.github.PauloFCM19.order.api.disassembler.StateInputDisassembler;
import com.github.PauloFCM19.order.api.input.StateInput;
import com.github.PauloFCM19.order.api.model.StateModel;
import com.github.PauloFCM19.order.domain.exception.BusinessException;
import com.github.PauloFCM19.order.domain.exception.StateNotFindExcepetion;
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
	
	@Autowired
	private StateInputDisassembler stateInputDisassembler;
	
	@Autowired
	private StateModelAssembler stateModelAssembler;
	
	@GetMapping
	public List<StateModel> findAll(){
		return stateModelAssembler.toColletionModel(stateRepository.findAll());
	}
	
	@GetMapping("/{stateId}")
	public StateModel findById(@PathVariable Long stateId) {
		State state = registerStateService.findOrFail(stateId);
		
		return stateModelAssembler.toModel(state);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateModel create(@RequestBody StateInput stateInput ) {
		try {
			State state = stateInputDisassembler.toDomainModel(stateInput);
			
			state = registerStateService.save(state);
			
			StateModel stateModel = stateModelAssembler.toModel(state);
			
			return stateModel;
		}catch(StateNotFindExcepetion e) {
			throw new BusinessException(e.getMessage());
		}
		
		
	}
	
	@PutMapping("/{stateId}")
	public StateModel update(@PathVariable Long stateId,@RequestBody StateInput stateInput) {
		State stateCurrency = registerStateService.findOrFail(stateId);
		
		stateInputDisassembler.copyToDomainObject(stateInput, stateCurrency);
		
		stateCurrency = registerStateService.save(stateCurrency);
		
		StateModel stateModel = stateModelAssembler.toModel(stateCurrency);
		
		return stateModel;
	}
	
	
	@DeleteMapping("/{stateId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long stateId) {
		registerStateService.delete(stateId);
	}
}
