package com.softbistro.survey.confirm.url.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbistro.survey.confirm.url.component.entity.Answer;
import com.softbistro.survey.confirm.url.component.entity.VotePage;
import com.softbistro.survey.confirm.url.component.interfacee.IVote;

@Service
public class VoteService {
	
	@Autowired
	private IVote iVote;

	/**
	 * Writing answers to questions in the database
	 * 
	 * @param uuid
	 * @return
	 */
	public void answerOnSurvey(String uuid, List<Answer> answers) {
		
		iVote.answerOnSurvey(uuid, answers);
	}

	/**
	 * Response for site with information about questions
	 * 
	 * @param uuid
	 * @return
	 */
	public List<VotePage> getVotePage(String uuid) {
		
		return iVote.getVotePage(uuid);
	}
}
