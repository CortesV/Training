package com.devcortes.first_configuration.components.interfaces;

import com.devcortes.first_configuration.components.enity.Person;

import java.util.Set;

/**
 * Created by cortes on 24.11.18.
 */
public interface ISetOperation {

	void addFamilyMembers(Person ... persons);

	Set<Object> getFamilyMembers();

	long getNumberOfFamilyMembers();

	long removeFamilyMembers(Person ... persons);
}
