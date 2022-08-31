package com.devcortes.first_configuration.components.dao;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.enums.RedisKeys;
import com.devcortes.first_configuration.components.interfaces.IHashOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by cortes on 18.11.18.
 */

@Repository
@Transactional
public class HashOperationDao implements IHashOperation {

	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations hashOperations;

	@Autowired
	public HashOperationDao(RedisTemplate<String, Object> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}

	public void addEmployee(Person person) {
		hashOperations.putIfAbsent(RedisKeys.PERSON.name(), person.getId(), person);
	}
	public void updateEmployee(Person person) {
		hashOperations.put(RedisKeys.PERSON.name(), person.getId(), person);
	}
	public Person getEmployee(Integer id) {
		return (Person) hashOperations.get(RedisKeys.PERSON.name(), id);
	}
	public long getNumberOfEmployees() {
		return hashOperations.size(RedisKeys.PERSON.name());
	}
	public Map<Integer, Person> getAllEmployees() {
		return hashOperations.entries(RedisKeys.PERSON.name());
	}
	public long deleteEmployees(Integer... ids) {
		return hashOperations.delete(RedisKeys.PERSON.name(), (Object)ids);
	}
}
