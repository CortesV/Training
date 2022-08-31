package com.devcortes.first_configuration.components.enity;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.data.redis.core.index.Indexed;

/**
 * Created by cortes on 25.11.18.
 */
public class Address {

	private @Indexed String city;

	private @Indexed String country;

	private @GeoIndexed Point location;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
}
