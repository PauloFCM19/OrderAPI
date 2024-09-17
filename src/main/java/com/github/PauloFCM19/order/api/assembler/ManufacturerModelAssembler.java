package com.github.PauloFCM19.order.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.PauloFCM19.order.api.model.ManufacturerModel;
import com.github.PauloFCM19.order.domain.model.Manufacturer;

@Component
public class ManufacturerModelAssembler {
	
		
		@Autowired
		private ModelMapper modelMapper;
		
		public ManufacturerModel toModel(Manufacturer manufacturer) {
			return modelMapper.map(manufacturer, ManufacturerModel.class);
		}
		
		public List<ManufacturerModel> toColletionModel(List<Manufacturer> manufacturers){
			return manufacturers.stream()
					.map(manufacturer -> toModel(manufacturer))
					.collect(Collectors.toList());
		}
		
}
