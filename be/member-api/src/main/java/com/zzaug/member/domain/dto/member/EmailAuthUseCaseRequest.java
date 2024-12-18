package com.zzaug.member.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EmailAuthUseCaseRequest {

	private Long memberId;
	private String sessionId;
	private String email;
	private String nonce;
}
