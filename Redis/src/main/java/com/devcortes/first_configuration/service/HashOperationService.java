package com.devcortes.first_configuration.service;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.interfaces.IHashOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by cortes on 18.11.18.
 */

@Service
public class HashOperationService {

	@Autowired
	private IHashOperation iHashOperation;

	public void addEmployee(Person person) {
		iHashOperation.addEmployee(person);
	}

	public void updateEmployee(Person person) {
		iHashOperation.updateEmployee(person);
	}

	public Person getEmployee(Integer id) {
		return iHashOperation.getEmployee(id);
	}

	public long getNumberOfEmployees() {
		return iHashOperation.getNumberOfEmployees();
	}

	public Map<Integer, Person> getAllEmployees() {
		return iHashOperation.getAllEmployees();
	}

	public long deleteEmployees(Integer... ids) {
		return iHashOperation.deleteEmployees(ids);
	}
}
