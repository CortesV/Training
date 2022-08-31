package com.devcortes.redis_operations;

import com.devcortes.RedisApplication;
import com.devcortes.first_configuration.components.queue.RedisMessagePublisher;
import com.devcortes.first_configuration.components.queue.RedisMessageSubscriber;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by cortes on 25.11.18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
public class RedisMessageListenerIntegrationTest {

	private static redis.embedded.RedisServer redisServer;

	@Autowired
	private RedisMessagePublisher redisMessagePublisher;

	@BeforeClass
	public static void startRedisServer() throws IOException {
		redisServer = new RedisServer(6380);
		redisServer.start();
	}

	@AfterClass
	public static void stopRedisServer() throws IOException {
		redisServer.stop();
	}

	@Test
	public void testOnMessage() throws Exception {
		String message = "Hello, world @ " + Instant.now().toString();
		redisMessagePublisher.publish(message);
		Thread.sleep(100);
		Assert.assertTrue(RedisMessageSubscriber.messageList.get(0).contains(message));
	}
}
