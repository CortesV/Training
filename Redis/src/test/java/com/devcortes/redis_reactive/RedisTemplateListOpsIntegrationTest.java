/*
package com.devcortes.redis_reactive;

import com.devcortes.RedisApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

*/
/**
 * Created by cortes on 25.11.18.
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisApplication.class)
public class RedisTemplateListOpsIntegrationTest {

	private static final String LIST_NAME = "demo_list";

	@Autowired private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

	private ReactiveListOperations<String, String> reactiveListOps;

	@Before public void setup() {
		reactiveListOps = reactiveRedisTemplate.opsForList();
	}

	@Test public void givenListAndValues_whenLeftPushAndLeftPop_thenLeftPushAndLeftPop() {
		Mono<Long> lPush = reactiveListOps.leftPushAll(LIST_NAME, "first", "second").log("Pushed");

		StepVerifier.create(lPush).expectNext(2L).verifyComplete();

		Mono<String> lPop = reactiveListOps.leftPop(LIST_NAME).log("Popped");

		StepVerifier.create(lPop).expectNext("second").verifyComplete();
	}
}*/
