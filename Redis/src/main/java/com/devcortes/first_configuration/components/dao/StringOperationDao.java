package com.devcortes.first_configuration.components.dao;

import com.devcortes.first_configuration.components.enums.RedisKeys;
import com.devcortes.first_configuration.components.interfaces.IStringOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cortes on 24.11.18.
 */

@Repository
@Transactional
public class StringOperationDao implements IStringOperation {

	@Autowired
	private StringRedisTemplate redisTemplate;

	private ValueOperations<String, String> opsForValue;

	@Autowired
	public StringOperationDao(StringRedisTemplate redisTemplate) {
		opsForValue = redisTemplate.opsForValue();
	}

	@Override
	public void addUserName(String uname) {
		opsForValue.setIfAbsent(RedisKeys.USER.name(), uname);
	}

	@Override
	public void updateUserName(String uname) {
		opsForValue.set(RedisKeys.USER.name(), uname);
	}

	@Override
	public String getUserName(String key) {
		return opsForValue.get(key);
	}

	@Override
	public void deleteUser(String key) {
		redisTemplate.delete(key);
	}
}
