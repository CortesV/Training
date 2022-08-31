package com.devcortes.first_configuration.components.interfaces;

import com.devcortes.first_configuration.components.enity.Person;

import java.util.Map;

/**
 * Created by cortes on 18.11.18.
 */
public interface IHashOperation {

	void addEmployee(Person person);

	void updateEmployee(Person person) ;

	Person getEmployee(Integer id);

	long getNumberOfEmployees();

	Map<Integer, Person> getAllEmployees();

	long deleteEmployees(Integer... ids);
}
