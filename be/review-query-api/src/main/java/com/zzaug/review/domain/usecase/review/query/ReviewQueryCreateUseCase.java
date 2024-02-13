package com.zzaug.review.domain.usecase.review.query;

import com.zzaug.review.domain.dto.review.query.ReviewQueryCreateUseCaseRequest;
import com.zzaug.review.domain.model.review.query.ReviewQuery;
import com.zzaug.review.domain.persistence.review.ReviewQueryRepository;
import com.zzaug.review.domain.support.entity.ReviewQueryEntityConverter;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryCreateUseCase {

	private final ReviewQueryRepository reviewQueryRepository;
	private final ReviewQueryConverter reviewQueryConverter;

	@Transactional
	public void execute(ReviewQueryCreateUseCaseRequest request) {
		ReviewQuery review = reviewQueryConverter.from(request);
		reviewQueryRepository.save(ReviewQueryEntityConverter.from(review));
	}
}