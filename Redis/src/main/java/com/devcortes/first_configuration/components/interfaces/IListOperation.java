package com.devcortes.first_configuration.components.interfaces;

import com.devcortes.first_configuration.components.enity.Person;

/**
 * Created by cortes on 18.11.18.
 */
public interface IListOperation {

	long getNumberOfFriends();

	Person getFriendAtIndex(Integer index);

	void removeFriend(Person person);

	void addFriend(Person person);
}
