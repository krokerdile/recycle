package com.zzaug.review.domain.dto.review.query;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SearchByReviewUseCaseRequest {
	private Long authorId;
	private String query;
}
