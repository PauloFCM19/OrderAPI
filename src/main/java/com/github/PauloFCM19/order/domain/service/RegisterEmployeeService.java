package com.github.PauloFCM19.order.domain.service;

import com.github.PauloFCM19.order.domain.exception.EmployeeNotFindException;
import com.github.PauloFCM19.order.domain.exception.ManufacturerNotFindException;
import com.github.PauloFCM19.order.domain.model.Client;
import com.github.PauloFCM19.order.domain.model.Employee;
import com.github.PauloFCM19.order.domain.repository.ClientRepository;
import com.github.PauloFCM19.order.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public Employee save(Employee employee) {

		return employeeRepository.save(employee);
	}
	
	@Transactional
	public void delete(Long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
			employeeRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ManufacturerNotFindException(employeeId);
		}
	
	}
	
	public Employee findOrFail(Long employeeId) {
		return employeeRepository.findById(employeeId)
			.orElseThrow(() -> new EmployeeNotFindException(employeeId));
	}
	
}