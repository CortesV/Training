package com.devcortes.first_configuration.components.interfaces;

/**
 * Created by cortes on 24.11.18.
 */
public interface IStringOperation {

	void addUserName(String uname);

	void updateUserName(String uname);

	String getUserName(String key);

	void deleteUser(String key);
}
