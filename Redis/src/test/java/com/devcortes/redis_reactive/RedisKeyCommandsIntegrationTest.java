/*
package com.devcortes.redis_reactive;

import com.devcortes.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.ReactiveKeyCommands;
import org.springframework.data.redis.connection.ReactiveStringCommands;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.ByteBuffer;

*/
/**
 * Created by cortes on 25.11.18.
 *//*


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisApplication.class)
public class RedisKeyCommandsIntegrationTest {

	@Autowired
	private ReactiveKeyCommands keyCommands;

	@Autowired
	private ReactiveStringCommands stringCommands;

	@Test
	public void givenFluxOfKeys_whenPerformOperations_thenPerformOperations() {
		Flux<String> keys = Flux.just("key1", "key2", "key3", "key4");

		Flux<ReactiveStringCommands.SetCommand> generator = keys.map(String::getBytes)
				.map(ByteBuffer::wrap)
				.map(key -> ReactiveStringCommands.SetCommand.set(key)
						.value(key));

		StepVerifier.create(stringCommands.set(generator))
				.expectNextCount(4L)
				.verifyComplete();

		Mono<Long> keyCount = keyCommands.keys(ByteBuffer.wrap("key*".getBytes()))
				.flatMapMany(Flux::fromIterable)
				.count();

		StepVerifier.create(keyCount)
				.expectNext(4L)
				.verifyComplete();

	}
}
*/
