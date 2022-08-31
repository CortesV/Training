package com.devcortes.second_configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Scanner;

/**
 * Created by cortes on 16.12.18.
 */
public class RedisSub {

	public static void main(String args[]) {
		Jedis jedis = new Jedis("localhost");
		String channel = "channel";
		System.out.println("Starting subscriber for channel " + channel);

		while (true) {
			jedis.subscribe(new JedisPubSub() {
				@Override
				public void onMessage(String channel, String message) {
					super.onMessage(channel, message);
					System.out.println("Received message:" + message);
				}
			}, channel);
		}
	}
}

