package com.devcortes.repository_test;

import com.devcortes.RedisApplication;
import com.devcortes.first_configuration.components.enity.Address;
import com.devcortes.first_configuration.components.enity.Human;
import com.devcortes.first_configuration.components.repository.HumanRepository;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cortes on 25.11.18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
public class HumanRepositorySecondIntegrationTest {

	@Autowired
	private HumanRepository humanRepository;

	@Autowired
	private RedisOperations<Object, Object> operations;

	private static RedisServer redisServer;

	private static final Charset CHARSET = StandardCharsets.UTF_8;

	/*
 	* Set of test users
 	*/
	Human eddard = new Human("eddard", "stark", Human.Gender.MALE);
	Human robb = new Human("robb", "stark", Human.Gender.MALE);
	Human sansa = new Human( "sansa", "stark", Human.Gender.FEMALE);
	Human arya = new Human( "arya", "stark", Human.Gender.FEMALE);
	Human bran = new Human("bran", "stark", Human.Gender.MALE);
	Human rickon = new Human( "rickon", "stark", Human.Gender.MALE);
	Human jon = new Human("jon", "snow", Human.Gender.MALE);

	@Before
	@After
	public void setUp() {

		operations.execute((RedisConnection connection) -> {
			connection.flushDb();
			return "OK";
		});
	}

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
	public void saveSingleEntity() {
		humanRepository.save(eddard);
		Assertions.assertThat(operations
				.execute((RedisConnection connection) -> connection.exists(("Human:" + eddard.getId()).getBytes(CHARSET))))
				.isTrue();
	}

	@Test
	public void findBySingleProperty() {
		flushTestUsers();
		List<Human> starks = humanRepository.findByLastname(eddard.getLastname());
		Assertions.assertThat(starks).contains(eddard, robb, sansa, arya, bran, rickon).doesNotContain(jon);
	}

	@Test
	public void findByMultipleProperties() {
		flushTestUsers();
		List<Human> aryaStark = humanRepository.findByNameAndLastname(arya.getName(), arya.getLastname());
		Assertions.assertThat(aryaStark).containsOnly(arya);
	}

	@Test
	public void findByMultiplePropertiesUsingOr() {
		flushTestUsers();
		List<Human> aryaAndJon = humanRepository.findByNameOrLastname(arya.getName(), jon.getLastname());
		Assertions.assertThat(aryaAndJon).containsOnly(arya, jon);
	}

	@Test
	public void findByReturningPage() {
		flushTestUsers();
		Page<Human> page1 = humanRepository.findPersonByLastname(eddard.getLastname(), PageRequest.of(0, 5));
		Assertions.assertThat(page1.getNumberOfElements()).isEqualTo(5);
		Assertions.assertThat(page1.getTotalElements()).isEqualTo(6);
		Page<Human> page2 = humanRepository.findPersonByLastname(eddard.getLastname(), PageRequest.of(1, 5));
		Assertions.assertThat(page2.getNumberOfElements()).isEqualTo(1);
		Assertions.assertThat(page2.getTotalElements()).isEqualTo(6);
	}

	@Test
	public void findByEmbeddedProperty() {
		Address winterfell = new Address();
		winterfell.setCountry("the north");
		winterfell.setCity("winterfell");
		eddard.setAddress(winterfell);
		flushTestUsers();
		List<Human> eddardStark = humanRepository.findByAddressCity(winterfell.getCity());
		Assertions.assertThat(eddardStark).containsOnly(eddard);
	}

	@Test
	public void findByGeoLocationProperty() {
		Address winterfell = new Address();
		winterfell.setCountry("the north");
		winterfell.setCity("winterfell");
		winterfell.setLocation(new Point(52.9541053, -1.2401016));
		eddard.setAddress(winterfell);
		Address casterlystein = new Address();
		casterlystein.setCountry("Westerland");
		casterlystein.setCity("Casterlystein");
		casterlystein.setLocation(new Point(51.5287352, -0.3817819));
		robb.setAddress(casterlystein);
		flushTestUsers();
		Circle innerCircle = new Circle(new Point(51.8911912, -0.4979756), new Distance(50, Metrics.KILOMETERS));
		List<Human> eddardStark = humanRepository.findByAddressLocationWithin(innerCircle);
		Assertions.assertThat(eddardStark).containsOnly(robb);
		Circle biggerCircle = new Circle(new Point(51.8911912, -0.4979756), new Distance(200, Metrics.KILOMETERS));
		List<Human> eddardAndRobbStark = humanRepository.findByAddressLocationWithin(biggerCircle);
		Assertions.assertThat(eddardAndRobbStark).hasSize(2).contains(robb, eddard);
	}

	@Test
	public void useReferencesToStoreDataToOtherObjects() {
		flushTestUsers();
		eddard.setChildren(Arrays.asList(jon, robb, sansa, arya, bran, rickon));
		humanRepository.save(eddard);
		Assertions.assertThat(humanRepository.findById(eddard.getId())).hasValueSatisfying(it -> {
			Assertions.assertThat(it.getChildren()).contains(jon, robb, sansa, arya, bran, rickon);
		});

		humanRepository.deleteAll(Arrays.asList(robb, jon));
		Assertions.assertThat(humanRepository.findById(eddard.getId())).hasValueSatisfying(it -> {
			Assertions.assertThat(it.getChildren()).contains(sansa, arya, bran, rickon);
			Assertions.assertThat(it.getChildren()).doesNotContain(robb, jon);
		});
	}

	private void flushTestUsers() {
		humanRepository.saveAll(Arrays.asList(eddard, robb, sansa, arya, bran, rickon, jon));
	}
}
