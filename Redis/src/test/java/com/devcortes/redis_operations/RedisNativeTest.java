package com.devcortes.redis_operations;

import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by cortes on 16.12.18.
 */
public class RedisNativeTest {

	@Test public void strings() {
		Jedis jedis = new Jedis();
		jedis.set("events/city/rome", "32,15,223,828");
		String cachedResponse = jedis.get("events/city/rome");
		System.out.println(cachedResponse);
	}

	@Test public void lists() {
		Jedis jedis = new Jedis();
		jedis.lpush("queue#tasks", "firstTask");
		jedis.lpush("queue#tasks", "secondTask");
		String task = jedis.rpop("queue#tasks");
		System.out.println(task);
		task = jedis.rpop("queue#tasks");
		System.out.println(task);
	}

	@Test public void sets() {
		Jedis jedis = new Jedis();
		jedis.sadd("nicknames", "nickname#1");
		jedis.sadd("nicknames", "nickname#2");
		jedis.sadd("nicknames", "nickname#1");

		Set<String> nicknames = jedis.smembers("nicknames");
		boolean exists = jedis.sismember("nicknames", "nickname#1");
		System.out.println(exists);
	}

	@Test public void hashes() {
		Jedis jedis = new Jedis();
		jedis.hset("user#1", "name", "Peter");
		jedis.hset("user#1", "job", "politician");

		String name = jedis.hget("user#1", "name");

		Map<String, String> fields = jedis.hgetAll("user#1");
		String job = fields.get("job");
		System.out.println(job);
	}

	@Test public void sortedSets() {
		Jedis jedis = new Jedis();
		Map<String, Double> scores = new HashMap<>();

		scores.put("PlayerOne", 3000.0);
		scores.put("PlayerTwo", 1500.0);
		scores.put("PlayerThree", 8200.0);

		scores.entrySet().forEach(playerScore -> {
			jedis.zadd("ranking", playerScore.getValue(), playerScore.getKey());
		});

		String player = jedis.zrevrange("ranking", 0, 1).iterator().next();
		long rank = jedis.zrevrank("ranking", "PlayerOne");
		System.out.println(player);
		System.out.println(rank);
	}

	@Test
	public void transactions() {
		Jedis jedis = new Jedis();
		String friendsPrefix = "friends#";
		String userOneId = "4352523";
		String userTwoId = "5552321";

		Transaction t = jedis.multi();
		t.sadd(friendsPrefix + userOneId, userTwoId);
		t.sadd(friendsPrefix + userTwoId, userOneId);
		t.exec();

		String watch = jedis.watch("friends#deleted#" + userOneId);
		System.out.println(watch);
	}

	@Test
	public void pipelines() {
		Jedis jedis = new Jedis();
		String userOneId = "4352523";
		String userTwoId = "4849888";

		Pipeline p = jedis.pipelined();
		p.sadd("searched#" + userOneId, "paris");
		p.zadd("ranking", 126, userOneId);
		p.zadd("ranking", 325, userTwoId);
		Response<Boolean> pipeExists = p.sismember("searched#" + userOneId, "paris");
		Response<Set<String>> pipeRanking = p.zrange("ranking", 0, -1);
		p.sync();

		Boolean exists = pipeExists.get();
		Set<String> ranking = pipeRanking.get();
		System.out.println(exists);
		ranking.forEach(System.out::println);
	}

	@Test
	public void cluster() {
		try (JedisCluster jedisCluster = new JedisCluster(new HostAndPort("localhost", 6379))) {
			jedisCluster.set("cluster", "localhost");
			String cluster = jedisCluster.get("cluster");
			System.out.println(cluster);
		} catch (IOException e) {}
	}

	@Test
	public void givenAPoolConfiguration_thenCreateAJedisPool() {
		final JedisPoolConfig poolConfig = buildPoolConfig();

		try (JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379); Jedis jedis = jedisPool.getResource()) {

			String key = "key";
			String value = "value";

			jedis.set(key, value);
			String value2 = jedis.get(key);

			System.out.println(value);
			System.out.println(value2);

			jedis.flushAll();
		}
	}

	private JedisPoolConfig buildPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128);
		poolConfig.setMaxIdle(128);
		poolConfig.setMinIdle(16);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
		poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);
		return poolConfig;
	}
}
