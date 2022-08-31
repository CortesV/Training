package com.devcortes.repository_test;

import com.devcortes.RedisApplication;
import com.devcortes.first_configuration.components.enity.Human;
import com.devcortes.first_configuration.components.repository.HumanRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cortes on 25.11.18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
public class HumanRepositoryIntegrationTest {

	@Autowired
	private HumanRepository humanRepository;

	private static RedisServer redisServer;

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
	public void whenSavingStudent_thenAvailableOnRetrieval() throws Exception {
		final Human human = new Human("001", "John Doe", Human.Gender.MALE, 1);
		humanRepository.save(human);
		final Human retrievedHuman = humanRepository.findById(human.getId()).get();
		Assert.assertEquals(human.getId(), retrievedHuman.getId());
	}

	@Test
	public void whenUpdatingStudent_thenAvailableOnRetrieval() throws Exception {
		final Human human = new Human("001", "John Doe", Human.Gender.MALE, 1);
		humanRepository.save(human);
		human.setName("Richard Watson");
		humanRepository.save(human);
		final Human retrievedHuman = humanRepository.findById(human.getId()).get();
		Assert.assertEquals(human.getName(), retrievedHuman.getName());
	}

	@Test
	public void whenSavingStudents_thenAllShouldAvailableOnRetrieval() throws Exception {
		final Human engHuman = new Human("001", "John Doe", Human.Gender.MALE, 1);
		final Human medHuman = new Human("002", "Gareth Houston", Human.Gender.MALE, 2);
		humanRepository.save(engHuman);
		humanRepository.save(medHuman);
		List<Human> humans = new ArrayList<>();
		humanRepository.findAll().forEach(humans::add);
		Assert.assertEquals(humans.size(), 2);
	}

	@Test
	public void whenDeletingStudent_thenNotAvailableOnRetrieval() throws Exception {
		final Human human = new Human("001", "John Doe", Human.Gender.MALE, 1);
		humanRepository.save(human);
		humanRepository.deleteById(human.getId());
		final Human retrievedHuman = humanRepository.findById(human.getId()).orElse(null);
		Assert.assertNull(retrievedHuman);
	}
}
