package com.github.PauloFCM19.order.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.PauloFCM19.order.api.model.ClientModel;
import com.github.PauloFCM19.order.domain.model.Client;

@Component
public class ClientModelAssembler {
	
		
		@Autowired
		private ModelMapper modelMapper;
		
		public ClientModel toModel(Client client) {
			return modelMapper.map(client, ClientModel.class);
		}
		
		public List<ClientModel> toColletionModel(List<Client> clients){
			return clients.stream()
					.map(manufacturer -> toModel(manufacturer))
					.collect(Collectors.toList());
		}
		
}
