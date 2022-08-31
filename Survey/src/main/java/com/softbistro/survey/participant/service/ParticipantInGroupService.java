package com.softbistro.survey.participant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbistro.survey.participant.component.entity.Group;
import com.softbistro.survey.participant.component.entity.Participant;
import com.softbistro.survey.participant.component.entity.ParticipantInGroup;
import com.softbistro.survey.participant.component.interfaces.IParticipantInGroup;

/**
 * Service for ParticipantInGroup entity
 * 
 * @author af150416
 *
 */
@Service
public class ParticipantInGroupService {

	@Autowired
	private IParticipantInGroup iParticipantInGroup;

	/**
	 * Method for getting all participant by group
	 * 
	 * @param groupId
	 * @return ResponseEntity
	 */
	public List<Participant> getParticipantsByGroupId(Integer groupId) {

		return iParticipantInGroup.getParticipantsByGroup(groupId);
	}

	/**
	 * Method for adding participants in group
	 * 
	 * @param groupId
	 * @param participantId
	 * @return ResponseEntity
	 */
	public void addParticipantsInGroup(ParticipantInGroup participantInGoup) {

		iParticipantInGroup.addParticipantsInGroup(participantInGoup);
	}

	/**
	 * Method for deleting participant from group
	 * 
	 * @param groupId
	 * @param participantId
	 * @return ResponseEntity
	 */
	public void deletingParticipantfromGroup(Integer groupId, Integer participantId) {

		iParticipantInGroup.deletingParticipantfromGroup(groupId, participantId);
	}

	/**
	 * Method for getting all participant groups
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	public List<Group> getParticipantGroups(Integer participantId) {

		return iParticipantInGroup.getParticipantGroups(participantId);
	}
}
