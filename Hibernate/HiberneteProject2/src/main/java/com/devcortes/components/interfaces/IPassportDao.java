package com.devcortes.components.interfaces;

import java.util.List;

import com.devcortes.components.entity.Passport;
import com.devcortes.components.service.request.PassportRequest;

public interface IPassportDao {
	public List<Passport> getAll();
	public void delete(Integer id);
	public void update(Integer id, PassportRequest passport);
	public Passport getById(Integer id);
	public void add(PassportRequest passport);
}
