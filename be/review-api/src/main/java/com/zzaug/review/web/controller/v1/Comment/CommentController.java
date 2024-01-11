package com.zzaug.review.web.controller.v1.Comment;

import com.zzaug.review.domain.dto.comment.CommentResponse;
import com.zzaug.review.support.ApiResponse;
import com.zzaug.review.support.ApiResponseGenerator;
import com.zzaug.review.web.dto.comment.CommentRequest;
import com.zzaug.security.authentication.token.TokenUserDetails;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class CommentController {

	@PostMapping("/{question_id}/comments")
	public ApiResponse<ApiResponse.SuccessBody<Void>> createComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			CommentRequest request) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@GetMapping("/{question_id}/comments")
	public ApiResponse<ApiResponse.SuccessBody<List<CommentResponse>>> viewQuestionComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			CommentRequest request) {

		List<CommentResponse> responses = new ArrayList<>();
		CommentResponse res =
				CommentResponse.builder()
						.comment_id(1L)
						.question_id(1L)
						.content("content")
						.author("author")
						.author_id(1L)
						.parent_id(1L)
						.created_at(new Timestamp(System.currentTimeMillis()))
						.updated_at(new Timestamp(System.currentTimeMillis()))
						.build();

		responses.add(res);
		return ApiResponseGenerator.success(responses, HttpStatus.OK);
	}

	@PutMapping("/{question_id}/comments/{commnet_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> editComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@PathVariable Long commnet_id,
			CommentRequest request) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}

	@DeleteMapping("/{question_id}/comments/{commnet_id}")
	public ApiResponse<ApiResponse.SuccessBody<Void>> deleteComment(
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@PathVariable Long question_id,
			@PathVariable Long commnet_id) {

		return ApiResponseGenerator.success(HttpStatus.OK);
	}
}
