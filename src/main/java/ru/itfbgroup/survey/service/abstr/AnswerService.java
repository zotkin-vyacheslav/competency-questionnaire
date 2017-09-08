package ru.itfbgroup.survey.service.abstr;

import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.util.JSONParse;

import java.util.List;

public interface AnswerService {

	void saveOptionsUserAnswer(Answer answer, List<JSONParse> jsonParses);

	void saveAdditionalInfo(Answer answer, List<JSONParse> jsonParses);

	Answer getAnswerById(Long id);

	void saveUserAnswer(Answer answer);

	List<JSONParse> getUserAnswerForJSON(Long userId);

	List<JSONParse> getAdditionalUserAnswerForJSON(Long userId);

	List getDataForStatistics(Long categoryId);

	List getDataForPersonalStatistics(Long userId, Long categoryId);
}
