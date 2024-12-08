package com.zzaug.review.domain.dto.review;

import com.zzaug.review.entity.review.ReviewPoint;
import java.time.LocalDateTime;
import javax.persistence.Embedded;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReviewEditUseCaseRequest {
	private Long reviewId;

	private Long questionId;

	private String content;

	private String author;

	private Long authorId;

	private LocalDateTime updatedAt;

	@Embedded private ReviewPoint startPoint;

	@Embedded private ReviewPoint endPoint;
}
