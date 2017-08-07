package ru.itfbgroup.questionnaire.service.abstr;

import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.JSONParse;

import java.util.List;

public interface AnswerOptionService {

	void saveAnswers(Answer answer, List<JSONParse> jsonParses);
}
