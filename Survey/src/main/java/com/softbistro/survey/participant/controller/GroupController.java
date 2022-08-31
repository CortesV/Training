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
import com.softbistro.survey.participant.component.entity.Group;
import com.softbistro.survey.participant.service.GroupService;

import io.swagger.annotations.ApiOperation;

/**
 * Controller for group entity
 * 
 * @author af150416
 *
 */
@RestController
@RequestMapping("/rest/survey/v1/group")
public class GroupController {

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private GroupService groupService;

	/**
	 * Method to create group
	 * 
	 * @param group
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Create new Group", notes = "Create new group instanse by client id and group name", tags = "Participant Group")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> setGroup(@RequestBody Group group, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(groupService.setGroup(group), HttpStatus.CREATED);
	}

	/**
	 * Method to get group from db
	 * 
	 * @param groupId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Get Group By Id", notes = "Get group instanse by group id", tags = "Participant Group")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Group> getGroupById(@PathVariable Integer id, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.OK);
	}

	/**
	 * Method to get all client groups
	 * 
	 * @param clientId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Get Group By Client", notes = "Get group instanse by group id", tags = "Participant Group")
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Group>> getGroupByClientId(@PathVariable Integer id, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(groupService.getGroupsByClient(id), HttpStatus.OK);
	}

	/**
	 * Method to update group
	 * 
	 * @param group
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Update Group Name By Id", notes = "Update group instanse by group name and group id", tags = "Participant Group")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> updateGroup(@PathVariable Integer id, @RequestBody Group group,
			@RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		groupService.updateGroupById(group, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Method for deleting group by id
	 * 
	 * @param groupId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Delete Group By Id", notes = "Delete group instanse by group id", tags = "Participant Group")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> deleteGroupById(@PathVariable Integer id, @RequestHeader String token) {

		if (!authorizationService.checkAccess(token)) {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		groupService.deleteGroupById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
