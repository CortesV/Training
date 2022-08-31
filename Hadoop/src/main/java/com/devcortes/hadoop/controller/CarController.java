package com.devcortes.hadoop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcortes.hadoop.components.entity.Car;
import com.devcortes.hadoop.generatedata.StartGeneration;
import com.devcortes.hadoop.service.CarService;

@RestController
@RequestMapping(value = "/rest/hadoop/v1/car/")
public class CarController {
	
	

	@Autowired
	private CarService carService;
	
	@Autowired
	private StartGeneration startGeneration;

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	public Car getById(@PathVariable("id") Integer id) {

		return carService.getById(id);
	}

	@RequestMapping(value = "{batch}/{limit}", method = RequestMethod.GET, produces = "application/json")
	public List<Car> getCars(@PathVariable("batch") Integer limit, @PathVariable("limit") Integer batch) {

		return carService.getCars(limit, batch);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public Integer saveCar(@RequestBody Car car) {

		return carService.saveCar(car);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
	public void updateCar(@PathVariable("id") Integer id, @RequestBody Car car) {

		carService.updateCar(id, car);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void deleteCar(@PathVariable("id") Integer id) {

		carService.deleteCar(id);
	}
	
	@RequestMapping(value = "generate", method = RequestMethod.POST, produces = "application/json")
	public void generateCar(@RequestBody List<Car> cars) {

		carService.generateCars(cars);
	}

	@RequestMapping(value = "/random", method = RequestMethod.GET, produces = "application/json")
	public void random() {

		startGeneration.runGeneration();
		
	}

}
