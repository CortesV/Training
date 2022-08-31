package com.devcortes.hadoop.generatedata;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class StartGeneration {

	@Resource
	private ApplicationContext context;

	public void runGeneration() {
		for (int i = 0; i < 40; i++) {
			new Thread(context.getBean(GenerateDataThread.class)).start();
		}
	}
}
