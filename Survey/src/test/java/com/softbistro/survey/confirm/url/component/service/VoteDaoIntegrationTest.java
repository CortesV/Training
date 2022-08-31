package com.softbistro.survey.confirm.url.component.service;

import static org.junit.Assert.assertNotEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softbistro.survey.confirm.url.component.entity.Answer;
import com.softbistro.survey.standalone.SurveySoftBistroApplication;

/**
 * Test VoteDao class
 * 
 * @author zviproject
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SurveySoftBistroApplication.class)
@WebAppConfiguration
@Transactional
@Rollback
public class VoteDaoIntegrationTest {

	@Autowired
	private VoteDao voteDao;

	private static final String UUID_FOR_TEST = "51695b96-21df-4c95-9585-222cdec18556";

	/**
	 * Get page for vote by url
	 */
	@Test
	public void getVotePageTest() {
		assertNotEquals(voteDao.getVotePage(UUID_FOR_TEST).size(), 0);

	}

	/**
	 * Answer on question
	 */
	@Test
	public void answerOnQuestion() {
		Answer answer = new Answer();

		answer.setAnswerValue("IntegrationTestAnswerValue");
		answer.setQuestionId(1);
		answer.setAnswerType("RATE1-10");
		answer.setComment("RATE1-10");
		answer.setAnswerValue("2");

		List<Answer> answers = new LinkedList<>();
		answers.add(answer);

		voteDao.answerOnSurvey(UUID_FOR_TEST, answers);

	}

}
