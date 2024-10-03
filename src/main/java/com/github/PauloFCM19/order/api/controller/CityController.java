package com.github.PauloFCM19.order.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.PauloFCM19.order.api.assembler.CityModelAssembler;
import com.github.PauloFCM19.order.api.model.CityModel;
import com.github.PauloFCM19.order.domain.model.City;
import com.github.PauloFCM19.order.domain.repository.CityRepository;
import com.github.PauloFCM19.order.domain.service.RegisterCityService;

@RestController
@RequestMapping(value = "cities")
public class CityController {

	@Autowired
	private RegisterCityService registerCityService;
	
	@Autowired
	private CityModelAssembler cityModelAssembler;
	
	@Autowired
	private CityRepository cityRepository;
	
	
	@GetMapping
	public List<CityModel> findAll(){
		return cityModelAssembler.toCollectionModel(cityRepository.findAll());
	}
}
