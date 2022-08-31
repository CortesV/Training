package com.devcortes.components.interfaces;

import java.util.List;

import com.devcortes.components.entity.Address;
import com.devcortes.components.service.request.AddressRequest;


public interface IAddressDao {
	public List<Address> getAll();
	public void delete(Integer id);
	public void update(Integer id, AddressRequest address);
	public Address getById(Integer id);
	public void add(AddressRequest address);
}
