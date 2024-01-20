package com.zzaug.review.domain.model.question;

import java.sql.Timestamp;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Question {
	private Long question_id;

	private String content;

	private String author;

	private Long author_id;

	private int review_cnt;

	private Timestamp created_at;

	private Timestamp updated_at;
}