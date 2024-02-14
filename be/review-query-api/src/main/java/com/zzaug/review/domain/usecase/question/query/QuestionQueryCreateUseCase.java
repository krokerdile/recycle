package com.zzaug.review.domain.usecase.question.query;

import com.zzaug.review.domain.dto.question.query.QuestionQueryCreateUseCaseRequest;
import com.zzaug.review.domain.event.SaveQuestionEvent;
import com.zzaug.review.domain.model.question.query.QuestionQuery;
import com.zzaug.review.domain.persistence.question.QuestionQueryRepository;
import com.zzaug.review.domain.support.entity.QuestionQueryEntityConverter;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryCreateUseCase {

	private final QuestionQueryRepository questionQueryRepository;
	private final QuestionQueryConverter questionQueryConverter;

	@Transactional
	@EventListener
	public void execute(SaveQuestionEvent event) {
		QuestionQuery questionQuery = questionQueryConverter.from(event);
		questionQueryRepository.save(QuestionQueryEntityConverter.from(questionQuery));
	}
}