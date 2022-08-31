package com.devcortes.first_configuration.components.queue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cortes on 25.11.18.
 */

@Service
public class RedisMessageSubscriber implements MessageListener {

	public static List<String> messageList = new ArrayList<String>();

	public void onMessage(final Message message, final byte[] pattern) {
		messageList.add(message.toString());
		System.out.println("\n");
		System.out.println(String.format("Message received from {%s} : %s", new String(message.getChannel()), new String(message.getBody())));
		System.out.println("\n");
	}
}