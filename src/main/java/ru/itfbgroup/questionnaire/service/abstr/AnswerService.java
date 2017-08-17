package ru.itfbgroup.questionnaire.service.abstr;

import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.util.JSONParse;

import java.util.List;

public interface AnswerService {

	void saveAnswers(Answer answer, List<JSONParse> jsonParses);

	void saveAnswer(Answer answer, List<JSONParse> jsonParses);

	void saveAdditionalInfo(Answer answer, List<JSONParse> jsonParses);

	Answer getAnswerById(Long id);
}
