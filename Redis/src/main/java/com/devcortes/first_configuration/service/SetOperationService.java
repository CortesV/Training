package com.devcortes.first_configuration.service;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.interfaces.ISetOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by cortes on 24.11.18.
 */

@Service
public class SetOperationService {

	@Autowired
	private ISetOperation iSetOperation;

	public void addFamilyMembers(Person... persons) {
		iSetOperation.addFamilyMembers(persons);
	}

	public Set<Object> getFamilyMembers() {
		return iSetOperation.getFamilyMembers();
	}

	public long getNumberOfFamilyMembers() {
		return iSetOperation.getNumberOfFamilyMembers();
	}

	public long removeFamilyMembers(Person ... persons) {
		return iSetOperation.removeFamilyMembers(persons);
	}
}
