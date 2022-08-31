package com.devcortes.first_configuration.configuration;

import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.queue.MessagePublisher;
import com.devcortes.first_configuration.components.queue.RedisMessagePublisher;
import com.devcortes.first_configuration.components.queue.RedisMessageSubscriber;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Created by cortes on 17.11.18.
 */

@Configuration
@ComponentScan("com.devcortes")
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass(RedisOperations.class)
@EnableRedisRepositories
@EnableRedisHttpSession
public class RedisConfiguration {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(128);
		jedisPoolConfig.setMaxIdle(128);
		jedisPoolConfig.setMinIdle(16);
		jedisPoolConfig.setMaxWaitMillis(-1);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		jedisPoolConfig.setNumTestsPerEvictionRun(3);
		jedisPoolConfig.setBlockWhenExhausted(true);

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);

		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration
				.builder()
				.usePooling()
				.poolConfig(jedisPoolConfig)
				.and()
				.connectTimeout(Duration.ofSeconds(60))
				.build();

		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
				jedisClientConfiguration);

		return connectionFactory;
	}

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	@Bean
	@ConditionalOnMissingBean(StringRedisTemplate.class)
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory());
		stringRedisTemplate.setEnableTransactionSupport(true);
		return stringRedisTemplate;
	}

	@Bean
	public RedisOperations<Object, Object> operations(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	public RedisTemplate<String, Object> redisDataTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		RedisSerializer<Object> values = new Jackson2JsonRedisSerializer<Object>(Object.class);
		RedisSerializer keys = new StringRedisSerializer();


		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setValueSerializer(values);
		redisTemplate.setKeySerializer(keys);
		redisTemplate.setHashValueSerializer(values);
		redisTemplate.setHashKeySerializer(keys);
		return redisTemplate;
	}

	@Bean
	public MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new RedisMessageSubscriber());
	}

	@Bean
	public RedisMessageListenerContainer redisContainer() {
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory());
		container.addMessageListener(messageListener(), topic());
		return container;
	}

	@Bean
	public MessagePublisher redisPublisher() {
		return new RedisMessagePublisher(redisTemplate(), topic());
	}

	@Bean
	public ChannelTopic topic() {
		return new ChannelTopic("pubsub:queue");
	}
}
