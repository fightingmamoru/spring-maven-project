package org.mamoru.common.service;

import java.util.Map;

/*
	ImService 프로토타입을 작성
	- 프로토타입은 메시지만 표시
 */
public interface ImService
{
	// 신청서 정보 입력
	void insertApplication(Map<String, Object> applicationData);

	// 결재 정보 입력
	void insertApproval(Map<String, Object> applicationData);

	// 결재 시작 처리
	void startApproval();

	// 결재 완료 처리 (결재완료 signal을 receiveTask로 전송)
	void completeApproval(String processInstanceId, String isOK);

	// 계정생성
	void insertAccount(Map<String, Object> accountInfo);

	// 권한 부여
	void insertAuthority(Map<String, Object> authorityInfo);

	// 계정 프로비전
	void provisionAccount(Map<String, Object> accountInfo);
}
