package com.devcortes.hadoop.components.interfaces;

import java.util.List;

import com.devcortes.hadoop.components.entity.Car;

public interface ICar {

	public Car getById(Integer id);

	public List<Car> getCars(Integer limit, Integer batch);

	public Integer saveCar(Car car);

	public void updateCar(Integer id, Car car);

	public void deleteCar(Integer id);

	public void generateCars(List<Car> batchCars);
}
