package ru.itfbgroup.survey.service.abstr;

import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.util.JSONParse;

import java.util.List;

public interface AnswerService {

	void saveAnswers(Answer answer, List<JSONParse> jsonParses);

	void saveOptionsUserAnswer(Answer answer, List<JSONParse> jsonParses);

	void saveAdditionalInfo(Answer answer, List<JSONParse> jsonParses);

	Answer getAnswerById(Long id);

	void saveAnswer(Answer answer);

	List<JSONParse> getUserAnswerForJSON(Long userId);
}
