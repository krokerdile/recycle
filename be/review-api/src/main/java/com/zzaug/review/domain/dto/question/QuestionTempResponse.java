package com.zzaug.review.domain.dto.question;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuestionTempResponse {
	private String t_id;
	private String content;
	private String author;
	private Long author_id;
	private LocalDateTime created_at;
}
