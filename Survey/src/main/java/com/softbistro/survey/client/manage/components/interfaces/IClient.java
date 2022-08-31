package com.softbistro.survey.client.manage.components.interfaces;

import java.util.List;

import com.softbistro.survey.client.manage.components.entity.Client;
import com.softbistro.survey.client.manage.components.entity.ClientForSending;
import com.softbistro.survey.notification.db.entity.NotificationSurveySending;

public interface IClient {

	/**
	 * Find client in database by id of client
	 * 
	 * @param id
	 *            id - id of client
	 * @return return - client's information
	 */

	public Client findClient(Integer id);

	/**
	 * Save client to database
	 * 
	 * @param client
	 *            client - all information about client that will write to
	 *            database
	 * @return return - information about of client
	 */
	public Integer saveClient(Client client);

	/**
	 * Delete client from database by email of client
	 * 
	 * @param id
	 *            id - id of client
	 * @return return - information about of client
	 */
	public void deleteClient(Integer id);

	/**
	 * Update information of client
	 * 
	 * @param client
	 *            client - all information about client that will write to
	 *            database
	 * @param id
	 *            id - id of client
	 * 
	 * @return return - information about of client
	 */
	public void updateClient(Client client, Integer id);

	/**
	 * Update client's password
	 * 
	 * @param client
	 *            client - all information about client that will write to
	 *            database
	 * @param id
	 *            id - id of client
	 * 
	 * @return return - information about of client
	 */
	public void updatePassword(Client client, Integer id);

	/**
	 * Save information about client that authorized with help of social
	 * networks
	 * 
	 * @param client
	 * @return
	 */
	public Client saveSocialClient(Client client);

	/**
	 * Find client by email and client name
	 * 
	 * @param client
	 * @return
	 */
	public Client findClientByLoginAndEmail(Client client);

	/**
	 * Find client by email, facebookId or googleId
	 * 
	 * @param template
	 *            template - email, facebookId or googleId
	 * @param value
	 *            value - value of template
	 * @return return - information about of client
	 */
	public Client findByTemplate(String template, String value);

	/**
	 * Method that add social data from social networks to exist client
	 * 
	 * @param token
	 * @return
	 */
	public void addSocialInfo(Client socialClient);

	/**
	 * Get mails of clients that change password
	 * 
	 * @return - list of mails
	 * 
	 * @author alex_alokhin
	 */
	public List<ClientForSending> getClientUpdatePassword();

	/**
	 * Get clients that have registration process
	 * 
	 * @return - list of clients
	 * 
	 * @author alex_alokhin
	 */
	public List<ClientForSending> getNewClients();

	/**
	 * Get clients that started the survey
	 * 
	 * @return - list of clients
	 * 
	 * @author alex_alokhin
	 */
	public List<NotificationSurveySending> getClientsForSendingSurvey();

	/**
	 * Update status of client
	 * 
	 * @return - list of mails
	 * 
	 * @author alex_alokhin
	 */
	public void updateStatusOfNewClients();

	/**
	 * Update status of clients that update password
	 * 
	 * @author alex_alokhin
	 */
	public void updateStatusOfUpdatePassword();

	/**
	 * Update status of survey
	 * 
	 * @author alex_alokhin
	 */
	public void updateStatusOfSurvey();

}
