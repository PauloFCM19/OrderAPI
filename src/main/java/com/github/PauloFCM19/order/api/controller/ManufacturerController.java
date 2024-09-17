package com.github.PauloFCM19.order.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.PauloFCM19.order.api.assembler.ManufacturerModelAssembler;
import com.github.PauloFCM19.order.api.model.ManufacturerModel;
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

}