package com.github.PauloFCM19.order.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.PauloFCM19.order.api.input.ManufacturerInput;
import com.github.PauloFCM19.order.api.input.StateInput;
import com.github.PauloFCM19.order.domain.model.Manufacturer;
import com.github.PauloFCM19.order.domain.model.State;

@Component
public class StateInputDisassembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public State toDomainModel(StateInput stateIpute) {
		return modelMapper.map(stateIpute, State.class);
	}
	
	public void copyToDomainObject(StateInput stateIpute, State state) {
		modelMapper.map(stateIpute, state);
	}
}
