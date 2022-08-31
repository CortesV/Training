package com.devcortes.first_configuration.components.dao;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.enums.RedisKeys;
import com.devcortes.first_configuration.components.interfaces.IListOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cortes on 18.11.18.
 */

@Repository
@Transactional
public class ListOperationDao implements IListOperation {

	private RedisTemplate<String, Object> redisTemplate;

	private ListOperations<String, Object> listOps;

	@Autowired
	public ListOperationDao(RedisTemplate<String, Object> redisTemplate) {
		listOps = redisTemplate.opsForList();
	}

	@Override
	public long getNumberOfFriends() {
		return listOps.size(RedisKeys.FRIEND.name());
	}

	@Override
	public Person getFriendAtIndex(Integer index) {
		return (Person) listOps.index(RedisKeys.FRIEND.name(), index);
	}

	@Override
	public void removeFriend(Person person) {
		listOps.remove(RedisKeys.FRIEND.name(), 1, person);
	}

	@Override
	public void addFriend(Person person) {
		listOps.leftPush(RedisKeys.FRIEND.name(), person);
	}
}
