package com.zzaug.review.domain.usecase.review;

import com.zzaug.review.domain.dto.review.ReviewResponse;
import com.zzaug.review.domain.dto.review.ReviewCreateUseCaseRequest;
import com.zzaug.review.domain.model.review.Review;
import com.zzaug.review.domain.persistence.question.QuestionRepository;
import com.zzaug.review.domain.persistence.review.ReviewRepository;
import com.zzaug.review.domain.support.entity.ReviewEntityConverter;
import com.zzaug.review.domain.usecase.review.converter.ReviewConverter;
import com.zzaug.review.domain.util.UtilSomething;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewCreateUseCase {

	private final ReviewRepository reviewRepository;
	private final ReviewConverter reviewConverter;
	private final QuestionRepository questionRepository;

	@Transactional
	public void execute(ReviewCreateUseCaseRequest request) {
		Review review = reviewConverter.from(request);
		reviewRepository.save(ReviewEntityConverter.from(review));
		incReviewCount(request.getQuestionId());
		// 리뷰 생성 이벤트 발행 ( effect : 리뷰 생성 및 리뷰 카운트 증가 )
	}

	private void incReviewCount(Long questionId) {
		questionRepository.incReviewCnt(questionId);
	}
}
