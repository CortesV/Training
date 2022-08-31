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
import com.softbistro.survey.participant.component.entity.Participant;
import com.softbistro.survey.participant.component.interfaces.IParticipant;

/**
 * Data access object for participant entity
 * 
 * @author af150416
 *
 */
@Repository
public class ParticipantDao implements IParticipant {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_FOR_SETTING_PARTICIPANT = "INSERT INTO participant (client_id, first_name, "
			+ "last_name, email) VALUES (?, ?, ?, ?)";

	private static final String SQL_FOR_UPDATING_PARTICIPANT = "UPDATE participant AS p "
			+ "SET p.first_name= ?, p.last_name= ?, p.email = ? WHERE p.id= ?";

	private static final String SQL_FOR_DELETING_PARTICIPANT = "UPDATE participant AS p SET p.delete = 1 WHERE p.id = ?";

	private static final String SQL_FOR_GETTING_PARTICIPANT_BY_ID = "SELECT * FROM participant WHERE participant.id= ? "
			+ "AND participant.delete = 0";

	private static final String SQL_FOR_GETTING_PARTICIPANT_BY_ATTRIBUTE_VALUE = "SELECT  p.id, p.client_id, p.email, p.first_name, "
			+ "p.last_name FROM participant AS p "
			+ "LEFT JOIN attribute_values AS av ON av.participant_id=p.id LEFT JOIN attributes AS at "
			+ "ON at.id=av.attribute_id WHERE at.id = ? AND av.attribute_value = ? AND p.delete = 0 AND av.delete = 0 AND at.delete = 0";

	private static final String SELECT_CLIENT_ALL_CLIENT_PARTICIPANTS = "SELECT * FROM participant WHERE client_id = ? "
			+ "AND participant.delete = 0";

	/**
	 * Method for creating participant
	 * 
	 * @param participant
	 * @return ResponseEntity
	 */
	@Override
	public Integer setParticipant(Participant participant) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_SETTING_PARTICIPANT,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, participant.getClientId());
				preparedStatement.setString(2, participant.getFirstName());
				preparedStatement.setString(3, participant.getLastName());
				preparedStatement.setString(4, participant.geteMail());

				return preparedStatement;
			}
		}, holder);

		return holder.getKey().intValue();
	}

	/**
	 * Method for updating participant
	 * 
	 * @param participant
	 * @return ResponseEntity
	 */
	@Override
	public void updateParticipant(Participant participant, Integer id) {
		jdbcTemplate.update(SQL_FOR_UPDATING_PARTICIPANT, participant.getFirstName(), participant.getLastName(),
				participant.geteMail(), id);
	}

	/**
	 * Method for deleting participant from db by id
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	@Override
	public void deleteParticipantById(Integer participantId) {
		jdbcTemplate.update(SQL_FOR_DELETING_PARTICIPANT, participantId);
	}

	/**
	 * Method to getting participant from db by id
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	@Override
	public Participant getParticipantById(Integer participantId) {
		return Optional
				.ofNullable(jdbcTemplate.query(SQL_FOR_GETTING_PARTICIPANT_BY_ID,
						new BeanPropertyRowMapper<>(Participant.class), participantId))
				.get().stream().findFirst().orElse(null);
	}

	/**
	 * Method to getting participant from db by attribute value
	 * 
	 * @param attributeId,
	 *            attributeValue
	 * @return ResponseEntity
	 */
	@Override
	public List<Participant> getParticipantByAttributeValue(Integer attributeId, String attributeValue) {
		return jdbcTemplate.query(SQL_FOR_GETTING_PARTICIPANT_BY_ATTRIBUTE_VALUE,
				new BeanPropertyRowMapper<>(Participant.class), attributeId, attributeValue);
	}

	/**
	 * Method to getting participant from database by client id
	 * 
	 * @param clientId
	 * @return
	 */
	@Override
	public List<Participant> selectClientAllParticipants(Integer cliectId) {
		return jdbcTemplate.query(SELECT_CLIENT_ALL_CLIENT_PARTICIPANTS, new BeanPropertyRowMapper<>(Participant.class),
				cliectId);
	}
}
