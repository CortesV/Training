package com.softbistro.survey.participant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softbistro.survey.client.auth.service.AuthorizationService;
import com.softbistro.survey.participant.component.entity.Participant;
import com.softbistro.survey.participant.service.ParticipantService;

import io.swagger.annotations.ApiOperation;

/**
 * Controller for participant entity
 * 
 * @author af150416
 *
 */
@RestController
@RequestMapping("/rest/survey/v1/participant")
public class ParticipantController {

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private ParticipantService participantService;

	/**
	 * Method to getting participant from db by id
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Get Participant By Id", notes = "Get participant instanse by participant id", tags = "Participant")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Participant> getParticipantById(@PathVariable Integer id, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(participantService.getParticipantById(id), HttpStatus.OK);
	}

	/**
	 * Method to getting participant from db by attribute value
	 * 
	 * @param attributeId,
	 *            attribute value
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Get Participant By Attribute Value", notes = "Get participant instanse by attribute id and attribute value", tags = "Participant")
	@RequestMapping(value = "attribute/{attribute_id}/{attribute_value}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Participant>> getParticipantByAttributeValue(
			@PathVariable("attribute_id") Integer attributeId, @PathVariable("attribute_value") String attributeValue,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(participantService.getParticipantByAttributeValue(attributeId, attributeValue),
				HttpStatus.OK);
	}

	/**
	 * Method for creating participant
	 * 
	 * @param participant
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Create new Participant", notes = "Create new participant instanse by participant first name, last name and email", tags = "Participant")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> setParticipant(@RequestBody Participant participant, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(participantService.setParticipant(participant), HttpStatus.CREATED);
	}

	/**
	 * Method for updating participant
	 * 
	 * @param participant
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Update Participant By Id", notes = "Update participant email, first name and last name by participant id", tags = "Participant")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> updateParticipant(@PathVariable Integer id, @RequestBody Participant participant,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		participantService.updateParticipant(participant, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Method for deleting participant from db by id
	 * 
	 * @param participantId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Delete Participant By Id", notes = "Delete participant instanse by participant id", tags = "Participant")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> deleteParticipantById(@PathVariable Integer id, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		participantService.deleteParticipantById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get client's participants by client id", notes = "Get client's participant by client id", tags = "Participant")
	@RequestMapping(value = "/all/{clientId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Participant>> selectClientAllParticipants(@PathVariable("clientId") Integer clientId,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(participantService.selectClientAllParticipants(clientId), HttpStatus.OK);
	}
}
