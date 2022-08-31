package com.devcortes.second_configuration;

import redis.clients.jedis.Jedis;

import java.util.Scanner;

/**
 * Created by cortes on 16.12.18.
 */
public class RedisPub {
	public static void main(String args[]) {
		Jedis jedis = new Jedis("localhost");
		Scanner scanner = new Scanner(System.in);
		String channel = "channel";
		System.out.println("Starting publisher for channel " + channel);

		while (true) {
			System.out.println("Enter the string to Publish:");
			String msg = scanner.nextLine();
			jedis.publish(channel, msg);

		}
	}
}
