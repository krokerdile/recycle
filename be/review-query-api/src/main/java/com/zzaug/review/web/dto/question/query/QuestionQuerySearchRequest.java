package com.zzaug.review.web.dto.question.query;

import lombok.*;
import org.springframework.data.domain.PageRequest;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionQuerySearchRequest {
	private Long authorId;
	private String query;
	private PageRequest pageRequest;
}
