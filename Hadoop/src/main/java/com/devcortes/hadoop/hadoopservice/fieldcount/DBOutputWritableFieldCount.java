package com.devcortes.hadoop.hadoopservice.fieldcount;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBOutputWritableFieldCount implements Serializable, Writable, DBWritable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String uuid;

	private Integer yearProduce;

	private String brand;

	private String model;

	private String color;

	private Integer price;

	private Integer count;
	
	

	public DBOutputWritableFieldCount() {

	}

	public DBOutputWritableFieldCount(String name, int count) {
		this.brand = name;
		this.count = count;
	}

	public DBOutputWritableFieldCount(Integer id, String uuid, Integer yearProduce, String brand, String model, String color,
			Integer price) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.yearProduce = yearProduce;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getYearProduce() {
		return yearProduce;
	}

	public void setYearProduce(Integer yearProduce) {
		this.yearProduce = yearProduce;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public void write(PreparedStatement statement) throws SQLException {
		//statement.setInt(1, this.id);
		//statement.setString(2, this.uuid);
		//statement.setInt(3, this.yearProduce);
		statement.setString(2, this.brand);
		//statement.setString(5, this.model);
		//statement.setString(6, this.color);
		//statement.setInt(7, this.price);
		statement.setInt(1, this.count);

	}

	@Override
	public void readFields(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt(1);
		this.uuid = resultSet.getString(2);
		this.yearProduce = resultSet.getInt(3);
		this.brand = resultSet.getString(2);
		this.model = resultSet.getString(5);
		this.color = resultSet.getString(6);
		this.price = resultSet.getInt(7);

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(this.id);
		Text.writeString(out, this.uuid);
		out.writeLong(this.yearProduce);
		Text.writeString(out, this.brand);
		Text.writeString(out, this.model);
		Text.writeString(out, this.color);
		out.writeLong(this.price);

	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.id = in.readInt();
		this.uuid = Text.readString(in);
		this.yearProduce = in.readInt();
		this.brand = Text.readString(in);
		this.model = Text.readString(in);
		this.color = Text.readString(in);
		this.price = in.readInt();
	}
}
