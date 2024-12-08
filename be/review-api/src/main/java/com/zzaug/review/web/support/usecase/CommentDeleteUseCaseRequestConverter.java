package com.zzaug.review.web.support.usecase;

import com.zzaug.review.domain.dto.comment.CommentDeleteUseCaseRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentDeleteUseCaseRequestConverter {
	public static CommentDeleteUseCaseRequest from(
			Long commentId, Long questionId, TokenUserDetails userDetails) {
		return CommentDeleteUseCaseRequest.builder()
				.commentId(commentId)
				.questionId(questionId)
				.authorId(Long.valueOf(userDetails.getId()))
				.build();
	}
}
