package com.zzaug.web.support;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ApiResponseGenerator {

	public static ApiResponse<ApiResponse.Success> success(final HttpStatus status) {
		return new ApiResponse<>(
				new ApiResponse.Success(MessageCode.SUCCESS.getValue(), MessageCode.SUCCESS.getCode()),
				status);
	}

	public static ApiResponse<ApiResponse.Success> success(
			final HttpStatus status, MessageCode code) {
		return new ApiResponse<>(new ApiResponse.Success(code.getValue(), code.getCode()), status);
	}

	public static <D> ApiResponse<ApiResponse.SuccessBody<D>> success(
			final D data, final HttpStatus status) {
		return new ApiResponse<>(
				new ApiResponse.SuccessBody<>(
						data, MessageCode.SUCCESS.getValue(), MessageCode.SUCCESS.getCode()),
				status);
	}

	public static <D> ApiResponse<ApiResponse.SuccessBody<D>> success(
			final D data, final HttpStatus status, MessageCode code) {
		return new ApiResponse<>(
				new ApiResponse.SuccessBody<>(data, code.getValue(), code.getCode()), status);
	}

	public static ApiResponse<Void> fail(final HttpStatus status) {
		return new ApiResponse<>(status);
	}

	public static ApiResponse<ApiResponse.FailureBody> fail(
			final ApiResponse.FailureBody body, final HttpStatus status) {
		return new ApiResponse<>(body, status);
	}

	public static ApiResponse<ApiResponse.FailureBody> fail(
			final String code, final String message, final HttpStatus status) {
		return new ApiResponse<>(new ApiResponse.FailureBody(code, message), status);
	}

	public static ApiResponse<ApiResponse.FailureBody> fail(
			final String message, final HttpStatus status) {
		return new ApiResponse<>(new ApiResponse.FailureBody(message), status);
	}

	public static ApiResponse<ApiResponse.FailureBody> fail(
			final MessageCode message, final HttpStatus status) {
		return new ApiResponse<>(
				new ApiResponse.FailureBody(message.getCode(), message.getValue()), status);
	}
}
