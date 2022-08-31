package com.devcortes.hadoop.components.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.devcortes.hadoop.components.entity.Car;
import com.devcortes.hadoop.components.interfaces.ICar;
import com.mysql.cj.api.jdbc.Statement;

@Repository
public class CarDao implements ICar {

	private final static String SQL_GET_CARS = "SELECT * FROM vechicle.car LIMIT ? OFFSET ?";
	private final static String SQL_FOR_GETTING_CAR_BY_ID = "SELECT * FROM car  WHERE car.id = ? AND car.delete = 0";
	private final static String SQL_SAVE_CAR = "INSERT INTO car (uuid, year_produce, brand, model, color, price) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_CAR = "UPDATE car SET yearProduce = ?, brand = ?, model = ?, color = ?, price = ? WHERE id = ?";
	private static final String DELETE_CAR = "UPDATE car as c SET c.`delete` = '1' WHERE c.id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Car getById(Integer idCar) {
		return Optional
				.ofNullable(
						jdbcTemplate.query(SQL_FOR_GETTING_CAR_BY_ID, new BeanPropertyRowMapper<>(Car.class), idCar))
				.get().stream().findFirst().orElse(null);
	}

	@Override
	public List<Car> getCars(Integer limit, Integer batch) {
		List<Car> findCars = jdbcTemplate.query(SQL_GET_CARS, new BeanPropertyRowMapper<Car>(Car.class), limit,
				(batch - 1) * limit);
		return findCars.isEmpty() ? new ArrayList<>() : findCars;
	}

	@Override
	public Integer saveCar(Car car) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_CAR,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, UUID.randomUUID().toString());
				preparedStatement.setInt(2, car.getYearProduce());
				preparedStatement.setString(3, car.getBrand());
				preparedStatement.setString(4, car.getModel());
				preparedStatement.setString(5, car.getColor());
				preparedStatement.setInt(6, car.getPrice());
				return preparedStatement;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	@Override
	public void updateCar(Integer id, Car car) {
		jdbcTemplate.update(UPDATE_CAR, car.getYearProduce(), car.getBrand(), car.getModel(), car.getColor(),
				car.getPrice(), id);
	}

	@Override
	public void deleteCar(Integer id) {
		jdbcTemplate.update(DELETE_CAR, id);
	}

	@Override
	public void generateCars(List<Car> batchCars) {

		jdbcTemplate.batchUpdate(SQL_SAVE_CAR, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				preparedStatement.setString(1, UUID.randomUUID().toString());
				preparedStatement.setInt(2, batchCars.get(i).getYearProduce());
				preparedStatement.setString(3, batchCars.get(i).getBrand());
				preparedStatement.setString(4, batchCars.get(i).getModel());
				preparedStatement.setString(5, batchCars.get(i).getColor());
				preparedStatement.setInt(6, batchCars.get(i).getPrice());
			}

			@Override
			public int getBatchSize() {
				return batchCars.size();
			}
		});
	}

}
