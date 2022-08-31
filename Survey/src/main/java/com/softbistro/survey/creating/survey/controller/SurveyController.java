package com.softbistro.survey.creating.survey.controller;

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
import com.softbistro.survey.creating.survey.component.entity.Group;
import com.softbistro.survey.creating.survey.component.entity.Survey;
import com.softbistro.survey.creating.survey.service.SurveyService;
import com.softbistro.survey.notification.db.service.SurveyMessageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/rest/survey/v1/survey")
public class SurveyController {

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private SurveyMessageService surveyMessageService;

	/**
	 * Writing new survey into database.
	 * 
	 * @param survey
	 *            - parsed JSON with information about survey.
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Create new Survey", notes = "Create new survey instanse by client id, name, theme, start time, finish time", tags = "Survey")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Integer> create(@RequestBody Survey survey, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<Integer>(surveyService.create(survey), HttpStatus.CREATED);
	}

	/**
	 * Update name of survey in database
	 * 
	 * @param newNameOfSurve
	 * @param surveyId
	 *            - id of survey that will be changed
	 */
	@ApiOperation(value = "Update Survey", notes = "Update survey instanse by client id, name, theme, start time, finish time and survey id", tags = "Survey")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> update(@RequestBody Survey survey, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

		surveyService.update(survey);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * Get all surveys from one client
	 * 
	 * @param clientId
	 * @return
	 */
	@ApiOperation(value = "Get all Surveys of Client", notes = "Get all Surveys of Client by client id", tags = "Survey")
	@RequestMapping(value = "/client/{client_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Survey>> getAllSurveysByClient(@PathVariable(name = "client_id") Integer clientId,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<List<Survey>>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<List<Survey>>(surveyService.getAllSurveysByClient(clientId), HttpStatus.OK);
	}

	/**
	 * Adding groups that will be in survey
	 * 
	 * @param groups
	 * @return
	 */
	@ApiOperation(value = "Add Groups to Survey", notes = "Add groups to survey by list of groups", tags = "Survey")
	@RequestMapping(value = "/groups", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> addGroups(@RequestBody List<Group> groups, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

		surveyService.addGroups(groups);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * Get all groups that has client
	 * 
	 * @param clientId
	 * @return
	 */
	@ApiOperation(value = "Get all Groups of Client", notes = "Get all groups of Client by client id", tags = "Survey")
	@RequestMapping(value = "/client/{client_id}/groups", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Group>> getGroupsClient(@PathVariable(value = "client_id") Integer clientId,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<List<Group>>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<List<Group>>(surveyService.getGroupsClient(clientId), HttpStatus.OK);
	}

	/**
	 * Get all groups that has survey
	 * 
	 * @param surveyId
	 */
	@ApiOperation(value = "Get all Groups of Survey", notes = "Get all Groups of Survey by survey id", tags = "Survey")
	@RequestMapping(value = "/{survey_id}/groups", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Group>> getGroupsSurvey(@PathVariable(value = "survey_id") Integer surveyId,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<List<Group>>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<List<Group>>(surveyService.getGroupsSurvey(surveyId), HttpStatus.OK);
	}

	/**
	 * 
	 * Delete survey from database
	 * 
	 * @param surveyId
	 */
	@ApiOperation(value = "Delete Survey By Id", notes = "Delete survey from data base by survey id", tags = "Survey")
	@RequestMapping(value = "/{survey_id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(value = "survey_id") Integer surveyId,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

		surveyService.delete(surveyId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * Start working time URL for vote and functions of survey
	 * 
	 * @param surveyId
	 * @return
	 */
	@ApiOperation(value = "Start Survey By Id", notes = "Start survey by survey id", tags = "Survey")
	@RequestMapping(value = "/start/{survey_id}/", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> start(@PathVariable(value = "survey_id") Integer surveyId,
			@RequestHeader String token) {
		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

		surveyService.start(surveyId);
		surveyMessageService.send();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * Stop working time URL for vote and functions of survey
	 * 
	 * @param surveyId
	 * @return
	 */
	@ApiOperation(value = "Stop Survey By Id", notes = "Stop survey  by survey id", tags = "Survey")
	@RequestMapping(value = "/stop/{survey_id}/", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> stop(@PathVariable(value = "survey_id") Integer surveyId,
			@RequestHeader String token) {
		if (!authorizationService.checkAccess(token)) {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}

		surveyService.stop(surveyId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
