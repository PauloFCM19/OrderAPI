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

import com.github.PauloFCM19.order.api.assembler.ManufacturerInputDisassembler;
import com.github.PauloFCM19.order.api.assembler.ManufacturerModelAssembler;
import com.github.PauloFCM19.order.api.model.ManufacturerInput;
import com.github.PauloFCM19.order.api.model.ManufacturerModel;
import com.github.PauloFCM19.order.domain.exception.BusinessException;
import com.github.PauloFCM19.order.domain.exception.ManufacturerNotFindException;
import com.github.PauloFCM19.order.domain.model.Manufacturer;
import com.github.PauloFCM19.order.domain.repository.ManufacturerRepository;
import com.github.PauloFCM19.order.domain.service.RegisterManufacturerService;

@RestController
@RequestMapping(value = "/manufacturers")
public class ManufacturerController {
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private RegisterManufacturerService registerManufacturerService;
	
	@Autowired
	private ManufacturerModelAssembler manufacturerModelAssembler;
	@Autowired
	private ManufacturerInputDisassembler manufacturerInputDisassembler;
	
	
	@GetMapping
	public List<ManufacturerModel> findAll(){
		return manufacturerModelAssembler
				.toColletionModel(manufacturerRepository.findAll());
	}
	
	@GetMapping("/{manufacturerId}")
	public ManufacturerModel findById(@PathVariable Long manufacturerId) {
		Manufacturer manufacturer = registerManufacturerService.findOrFail(manufacturerId);
		
		return manufacturerModelAssembler.toModel(manufacturer);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ManufacturerModel create(@RequestBody ManufacturerInput manufacturerInput) {
		try {
			
			Manufacturer manufacturer = manufacturerInputDisassembler
					.toDomainModel(manufacturerInput);
		
			manufacturer = registerManufacturerService.save(manufacturer);
			
			ManufacturerModel manufacturerModel = manufacturerModelAssembler.toModel(manufacturer);
			
			return manufacturerModel;
		}catch(ManufacturerNotFindException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PutMapping("/{manufacturerId}")
	public ManufacturerModel update(@PathVariable Long manufacturerId, 
			@RequestBody ManufacturerInput manufacturerInput) {
		Manufacturer manufacturerCurrency = registerManufacturerService.findOrFail(manufacturerId);
		
		manufacturerInputDisassembler.copyToDomainObject(manufacturerInput, manufacturerCurrency);
		
		manufacturerCurrency = registerManufacturerService.save(manufacturerCurrency);
		
		
		return manufacturerModelAssembler.toModel(manufacturerCurrency);
	}
	
	@DeleteMapping("/{manufacturerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long manufacturerId) {
		 registerManufacturerService.delete(manufacturerId);
	}

}