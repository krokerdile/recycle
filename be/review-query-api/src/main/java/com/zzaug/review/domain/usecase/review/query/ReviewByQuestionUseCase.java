package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewByQuestionUseCaseRequest;
import com.zzaug.review.domain.dto.review.query.ReviewQueryResponse;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewByQuestionUseCase {
	private final ReviewQueryRepository reviewQueryRepository;

	@Transactional
	public List<ReviewQueryResponse> execute(ReviewByQuestionUseCaseRequest request) {
		List<ReviewQueryEntity> result =
				reviewQueryRepository.findAllByQuestionIdAndIsDeletedIsFalse(request.getQuestionId());
		return result.stream().map(ReviewQueryResponseConverter::from).collect(Collectors.toList());
	}
}