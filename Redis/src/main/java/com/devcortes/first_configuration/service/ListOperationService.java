package com.devcortes.first_configuration.service;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.interfaces.IListOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cortes on 24.11.18.
 */

@Service
public class ListOperationService {

	@Autowired
	private IListOperation iListOperation;

	public long getNumberOfFriends() {
		return iListOperation.getNumberOfFriends();
	}

	public Person getFriendAtIndex(Integer index) {
		return iListOperation.getFriendAtIndex(index);
	}

	public void removeFriend(Person person) {
		iListOperation.removeFriend(person);
	}

	public void addFriend(Person person) {
		iListOperation.addFriend(person);
	}
}
