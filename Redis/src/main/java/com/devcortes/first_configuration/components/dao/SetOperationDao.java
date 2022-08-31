package com.devcortes.first_configuration.components.dao;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.enums.RedisKeys;
import com.devcortes.first_configuration.components.interfaces.ISetOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by cortes on 24.11.18.
 */

@Repository
@Transactional
public class SetOperationDao implements ISetOperation {

	private RedisTemplate<String, Object> redisTemplate;

	private SetOperations<String, Object> setOps;

	@Autowired
	public SetOperationDao(RedisTemplate<String, Object> redisTemplate) {
		setOps = redisTemplate.opsForSet();
	}

	@Override
	public void addFamilyMembers(Person ... persons) {
		setOps.add(RedisKeys.FAMILY.name(), persons);
	}

	@Override
	public Set<Object> getFamilyMembers() {
		return setOps.members(RedisKeys.FAMILY.name());
	}

	@Override
	public long getNumberOfFamilyMembers() {
		return setOps.size(RedisKeys.FAMILY.name());
	}

	@Override
	public long removeFamilyMembers(Person ... persons) {
		return setOps.remove(RedisKeys.FAMILY.name(), persons);
	}
}
