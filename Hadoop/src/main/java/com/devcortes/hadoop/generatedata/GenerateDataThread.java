package com.devcortes.hadoop.generatedata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.devcortes.hadoop.components.entity.Car;
import com.devcortes.hadoop.components.interfaces.ICar;

@Service
@Scope("prototype")
public class GenerateDataThread implements Runnable {
	
	private static final Logger LOGGER = Logger.getLogger(GenerateDataThread.class);

	private final static String[] colors;

	private final static String[] brands;

	@Autowired
	private ICar iCar;
	
	static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";

		brands = new String[40];
		brands[0] = "Anasagasti";
		brands[1] = "Autoar";
		brands[2] = "Crespi";
		brands[3] = "Eniak";
		brands[4] = "Hispano-Argentina";
		brands[5] = "IAME";
		brands[6] = "IKA";
		brands[7] = "SIAM Di Tella";
		brands[8] = "Zanella";
		brands[9] = "Bolwell";
		
		brands[10] = "Elfin";
		brands[11] = "FPV";
		brands[12] = "Holden";
		brands[13] = "HSV";
		brands[14] = "Joss";
		brands[15] = "Allard";
		brands[16] = "Bombardier";
		brands[17] = "9ff";
		brands[18] = "Alpina";
		brands[19] = "Artega";
		
		brands[20] = "Bitter";
		brands[21] = "Brabus";
		brands[22] = "Gumpert";
		brands[23] = "Isdera";
		brands[24] = "Multicar";
		brands[25] = "Porsche";
		brands[26] = "Smart";
		brands[27] = "Robur";
		brands[28] = "Wiesmann";
		brands[29] = "MAN";
		
		
	}

	public List<Car> generateBatch() {

		List<Car> batchCars = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			Car randomCar = new Car(getRandomId(), getRandomYear(), getRandomBrand(), getRandomModel(),
					getRandomColor(), getRandomPrice());
			batchCars.add(randomCar);
		}
		return batchCars;
	}

	@Override
	public void run() {
		iCar.generateCars(generateBatch());
		LOGGER.info("DONE");
	}

	private String getRandomId() {
		return UUID.randomUUID().toString();
	}

	private String getRandomModel() {
		return UUID.randomUUID().toString().substring(0, 3);
	}

	private int getRandomYear() {
		return (int) (Math.random() * 50 + 1960);
	}

	private String getRandomColor() {
		return colors[(int) (Math.random() * 10)];
	}

	private String getRandomBrand() {
		return brands[(int) (Math.random() * 10)];
	}

	private int getRandomPrice() {
		return (int) (Math.random() * 1000000);
	}

}
