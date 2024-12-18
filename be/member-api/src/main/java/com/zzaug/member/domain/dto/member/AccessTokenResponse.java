package com.zzaug.member.domain.dto.member;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccessTokenResponse {
	private String accessToken;

	public AccessTokenResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
