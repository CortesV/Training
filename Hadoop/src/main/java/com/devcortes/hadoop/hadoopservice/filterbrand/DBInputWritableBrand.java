package com.devcortes.hadoop.hadoopservice.filterbrand;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBInputWritableBrand implements Serializable, Writable, DBWritable, WritableComparable<DBInputWritableBrand> {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String uuid;

	private Integer yearProduce;

	private String brand;

	private String model;

	private String color;

	private Integer price;

	public DBInputWritableBrand() {

	}

	public DBInputWritableBrand(Integer id, String uuid, Integer yearProduce, String brand, String model, String color,
			Integer price) {
		this.id = id;
		this.uuid = uuid;
		this.yearProduce = yearProduce;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.price = price;
	}

	@Override
	public void write(PreparedStatement statement) throws SQLException {
		statement.setInt(1, this.id);
		statement.setString(2, this.uuid);
		statement.setInt(3, this.yearProduce);
		statement.setString(4, this.brand);
		statement.setString(5, this.model);
		statement.setString(6, this.color);
		statement.setInt(7, this.price);

	}

	@Override
	public void readFields(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt(1);
		this.uuid = resultSet.getString(2);
		this.yearProduce = resultSet.getInt(3);
		this.brand = resultSet.getString(4);
		this.model = resultSet.getString(5);
		this.color = resultSet.getString(6);
		this.price = resultSet.getInt(7);

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.id);
		Text.writeString(out, this.uuid);
		out.writeInt(this.yearProduce);
		Text.writeString(out, this.brand);
		Text.writeString(out, this.model);
		Text.writeString(out, this.color);
		out.writeInt(this.price);

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

	@Override
	public int compareTo(DBInputWritableBrand dbInputWritable) {

		final int EQUAL = 0;

		if (this.equals(dbInputWritable))
			return EQUAL;

		int comparison = this.id.compareTo(dbInputWritable.getId());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.uuid.compareTo(dbInputWritable.getUuid());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.yearProduce.compareTo(dbInputWritable.getYearProduce());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.brand.compareTo(dbInputWritable.getBrand());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.model.compareTo(dbInputWritable.getModel());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.color.compareTo(dbInputWritable.getColor());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.price.compareTo(dbInputWritable.getPrice());
		if (comparison != EQUAL)
			return comparison;
		return EQUAL;
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
}
