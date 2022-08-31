package com.devcortes.first_configuration.service;

import com.devcortes.first_configuration.components.interfaces.IStringOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cortes on 24.11.18.
 */

@Service
public class StringOperationService {

	@Autowired
	private IStringOperation iStringOperation;

	public void addUserName(String uname) {
		iStringOperation.addUserName(uname);
	}

	public void updateUserName(String uname) {
		iStringOperation.updateUserName(uname);
	}

	public String getUserName(String key) {
		return iStringOperation.getUserName(key);
	}

	public void deleteUser(String key) {
		iStringOperation.deleteUser(key);
	}
}
