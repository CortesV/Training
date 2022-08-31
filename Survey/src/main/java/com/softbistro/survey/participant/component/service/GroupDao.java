package com.softbistro.survey.participant.component.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.cj.api.jdbc.Statement;
import com.softbistro.survey.participant.component.entity.Group;
import com.softbistro.survey.participant.component.interfaces.IGroup;

/**
 * Data access object for group entity
 * 
 * @author af150416
 *
 */
@Repository
public class GroupDao implements IGroup {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_FOR_SETTING_GROUP = "INSERT INTO `group` (`group`.client_id, `group`.group_name) VALUES (?, ?)";

	private static final String SQL_FOR_GETTING_GROUP_BY_ID = "SELECT * FROM `group` AS g WHERE g.id = ? AND g.`delete` = 0";

	private static final String SQL_FOR_GETTING_GROUP_BY_CLIENT = "SELECT * FROM `group` AS g WHERE g.client_id = ? AND g.`delete` = 0";

	private static final String SQL_FOR_UPDATING_GROUP_BY_ID = "UPDATE `group` AS g SET g.group_name = ? WHERE g.id = ?";

	private static final String SQL_FOR_DELETING_GROUP_BY_ID = "UPDATE `group` AS g SET g.delete = 1 WHERE g.id = ?";

	/**
	 * Method to create group
	 * 
	 * @param group
	 * @return ResponseEntity
	 */
	@Override
	public Integer setGroup(Group group) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SETTING_GROUP,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, group.getClientId());
				preparedStatement.setString(2, group.getGroupName());

				return preparedStatement;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	/**
	 * Method to get group from db
	 * 
	 * @param groupId
	 * @return ResponseEntity
	 */
	@Override
	public Group getGroupByid(Integer groupId) {
		return Optional.ofNullable(
				jdbcTemplate.query(SQL_FOR_GETTING_GROUP_BY_ID, new BeanPropertyRowMapper<>(Group.class), groupId))
				.get().stream().findFirst().orElse(null);
	}

	/**
	 * Method to get all clients in group
	 * 
	 * @param clientId
	 * @return ResponseEntity
	 */
	@Override
	public List<Group> getGroupsByClient(Integer clientId) {
		return jdbcTemplate.query(SQL_FOR_GETTING_GROUP_BY_CLIENT, new BeanPropertyRowMapper<>(Group.class), clientId);
	}

	/**
	 * Method to update group
	 * 
	 * @param group
	 * @return ResponseEntity
	 */
	@Override
	public void updateGroupById(Group group, Integer id) {
		jdbcTemplate.update(SQL_FOR_UPDATING_GROUP_BY_ID, group.getGroupName(), id);
	}

	/**
	 * Method for deleting group by id
	 * 
	 * @param groupId
	 * @return ResponseEntity
	 */
	@Override
	public void deleteGroupById(Integer groupId) {
		jdbcTemplate.update(SQL_FOR_DELETING_GROUP_BY_ID, groupId);
	}
}
