package com.devcortes.redis_operations;

import com.devcortes.RedisApplication;
import com.devcortes.first_configuration.components.enity.Person;
import com.devcortes.first_configuration.components.enums.RedisKeys;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cortes on 25.11.18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
public class RedisOperationTest {

	@Autowired
	private RedisOperations<Object, Object> operations;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChannelTopic topic;

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

	@Before
	@After
	public void setUp() {

		operations.execute((RedisConnection connection) -> {
			connection.flushDb();
			return "OK";
		});
	}

	@Test
	public void listOperationTest() throws Exception {
		ListOperations<Object, Object> listOperationService = operations.opsForList();

		Person p1 = new Person(1, "Viktor", 30);
		listOperationService.leftPush(RedisKeys.FRIEND.name(), p1);
		Person p2 = new Person(2, "Oleg", 35);
		listOperationService.leftPush(RedisKeys.FRIEND.name(), p2);
		Assert.assertEquals(2, listOperationService.size(RedisKeys.FRIEND.name()).longValue());
		Assert.assertEquals(p1, listOperationService.index(RedisKeys.FRIEND.name(), 1));
		listOperationService.remove(RedisKeys.FRIEND.name(), 1, p2);
		Assert.assertEquals(null, listOperationService.index(RedisKeys.FRIEND.name(), 1));
	}

	@Test
	public void setOperationTest() throws Exception {
		SetOperations<Object, Object> setOperationService = operations.opsForSet();

		Person p1 = new Person(101, "Den", 30);
		Person p2 = new Person(102, "Bhargav", 25);
		Person p3 = new Person(103, "Amir", 35);
		setOperationService.add(RedisKeys.FAMILY.name(), p1, p2, p3);
		Assert.assertEquals(3, setOperationService.size(RedisKeys.FAMILY.name()).longValue());
		Set<Object> familyMembers = setOperationService.members(RedisKeys.FAMILY.name());
		Assertions.assertThat(familyMembers).contains(p1, p2, p3);
		setOperationService.remove(RedisKeys.FAMILY.name(), p1, p2);
		Set<Object> lastFamilyMembers = setOperationService.members(RedisKeys.FAMILY.name());
		Assertions.assertThat(lastFamilyMembers).contains(p3);
	}

	@Test
	public void hashOperationTest() throws Exception {
		HashOperations<Object, Object, Object> hashOperationService = operations.opsForHash();

		Person emp1 = new Person(11, "Den", 45);
		Person emp2 = new Person(12, "Bhargav", 35);
		Person emp3 = new Person(13, "Amir", 25);
		hashOperationService.putIfAbsent(RedisKeys.PERSON.name(), emp1.getId(), emp1);
		hashOperationService.putIfAbsent(RedisKeys.PERSON.name(), emp2.getId(), emp2);
		hashOperationService.putIfAbsent(RedisKeys.PERSON.name(), emp3.getId(), emp3);
		Assert.assertEquals(3, hashOperationService.size(RedisKeys.PERSON.name()).longValue());
		Map<Object, Object> allEmployees = hashOperationService.entries(RedisKeys.PERSON.name());
		List<Person> personList = allEmployees.entrySet()
				.stream()
				.map(integerPersonEntry -> (Person) integerPersonEntry.getValue())
				.collect(Collectors.toList());
		Assertions.assertThat(personList).contains(emp1, emp2, emp3);
		emp2.setAge(20);
		hashOperationService.put(RedisKeys.PERSON.name(), emp2.getId(), emp2);
		Assert.assertEquals(emp2, hashOperationService.get(RedisKeys.PERSON.name(), emp2.getId()));
	}

	@Test
	public void stringValueOperationTest() throws Exception {
		ValueOperations<Object, Object> stringOperationService = operations.opsForValue();

		stringOperationService.setIfAbsent(RedisKeys.USER.name(), "Viktor");
		Assert.assertEquals("Viktor", stringOperationService.get(RedisKeys.USER.name()));
		stringOperationService.set(RedisKeys.USER.name(), "blabla");
		Assert.assertEquals("blabla", stringOperationService.get(RedisKeys.USER.name()));
		redisTemplate.delete(RedisKeys.USER.name());
		Assert.assertEquals(null, stringOperationService.get(RedisKeys.USER.name()));
	}

	@Test
	public void geoLocationTest() {
		GeoOperations<Object, Object> geoOperations = operations.opsForGeo();

		geoOperations.add("Sicily", new Point(13.361389, 38.1155556), "Arigento");
		geoOperations.add("Sicily", new Point(15.087269, 37.502669), "Catania");
		geoOperations.add("Sicily", new Point(13.583333, 37.316667), "Palermo");

		System.out.println("\n\nRadius:");
		Circle circle = new Circle(new Point(13.583333, 37.316667), new Distance(100, RedisGeoCommands.DistanceUnit.KILOMETERS));
		GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults = geoOperations.radius("Sicily", circle);
		geoResults.forEach(System.out::println);

		System.out.println("\n\nDistance:");
		Distance distance = geoOperations.distance("Sicily", "Arigento", "Palermo");
		System.out.println(distance);

		System.out.println("\n\nPosition:");
		List<Point> position = geoOperations.position("Sicily", "Arigento");
		position.forEach(System.out::println);

		System.out.println("\n\nHash:");
		List<String> hash = geoOperations.hash("Sicily", "Palermo");
		hash.forEach(System.out::println);

		System.out.println("\n\nRemove location:");
		geoOperations.remove("Sicily", "Arigento");
		circle = new Circle(new Point(13.583333, 37.316667), new Distance(100, RedisGeoCommands.DistanceUnit.KILOMETERS));
		geoResults = geoOperations.radius("Sicily", circle);
		geoResults.forEach(System.out::println);
	}

	@Test
	public void pubsubTest() throws Exception {
		String message = "Hello, world @ " + Instant.now().toString();
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}
}
