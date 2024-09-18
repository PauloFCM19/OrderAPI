package com.github.PauloFCM19.order.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.PauloFCM19.order.api.input.ManufacturerInput;
import com.github.PauloFCM19.order.domain.model.Manufacturer;

@Component
public class ManufacturerInputDisassembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public Manufacturer toDomainModel(ManufacturerInput manufacturerInput) {
		return modelMapper.map(manufacturerInput, Manufacturer.class);
	}
	
	public void copyToDomainObject(ManufacturerInput manufacturerInput, Manufacturer manufacturer) {
		modelMapper.map(manufacturerInput, manufacturer);
	}
}
