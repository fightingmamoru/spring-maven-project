package org.mamoru.common.service.impl;

import org.activiti.engine.RuntimeService;
import org.mamoru.common.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
	ImService의 실제 로직 구현
 */
@Service
public class ImServiceImpl implements ImService
{
	@Autowired
	RuntimeService runtimeService;

	@Override
	public void insertApplication(Map<String, Object> applicationData)
	{
		System.out.println("[ImService - insertApplication()] START");

		for ( String key : applicationData.keySet() )
		{
			System.out.println("[ImService - insertApplication()] (Process Insert Application Data) key : " + key + " / value : " + applicationData.get(key));
		}

		System.out.println("[ImService - insertApplication()] END");
	}

	@Override
	public void insertApproval(Map<String, Object> applicationData)
	{
		System.out.println("[ImService - insertApproval()] START");

		for ( String key : applicationData.keySet() )
		{
			System.out.println("[ImService - insertApproval()] (Process Insert Application Data) key : " + key + " / value : " + applicationData.get(key));
		}

		// 결재 정보 생성 - 신청서 정보 기준
		System.out.println("[ImService - insertApproval()] (Process Create Approval Data - Using Application Data)");

		// 결재 정보 입력
		System.out.println("[ImService - insertApproval()] (Process Insert Approval Data)");

		// 결재 시작
		startApproval();

		System.out.println("[ImService - insertApproval()] END");
	}

	@Override
	public void startApproval()
	{
		System.out.println("[ImService - startApproval()] START");

		System.out.println("[ImService - startApproval()] END");
	}

	@Override
	public void completeApproval(String processInstanceId, String isOK)
	{
		System.out.println("[ImService - completeApproval()] START");

		System.out.println("[ImService - completeApproval()] isOK : " + isOK);

		// todo : receiveTask로 signal 전송하여 결재 종료 처리
		Map<String, Object> signalParam = new HashMap<String, Object>();

		signalParam.put("isOK", isOK);

		runtimeService.signal(processInstanceId, signalParam);

		System.out.println("[ImService - completeApproval()] END");
	}

	@Override
	public void insertAccount(Map<String, Object> accountInfo)
	{
		System.out.println("[ImService - insertAccount()] START");

		// 생성할 계정 정보 조회
		for ( String key : accountInfo.keySet() )
		{
			System.out.println("[ImService - insertAccount()] (Process Insert Account Data) key : " + key + " / value : " + accountInfo.get(key));
		}

		System.out.println("[ImService - insertAccount()] END");
	}

	@Override
	public void insertAuthority(Map<String, Object> authorityInfo)
	{
		System.out.println("[ImService - insertAuthority()] START");

		// 생성할 계정 정보 조회
		for ( String key : authorityInfo.keySet() )
		{
			System.out.println("[ImService - insertAuthority()] (Process Insert Account Data) key : " + key + " / value : " + authorityInfo.get(key));
		}

		System.out.println("[ImService - insertAuthority()] END");
	}

	@Override
	public void provisionAccount(Map<String, Object> accountInfo)
	{
		System.out.println("[ImService - provisionAccount()] START");

		// 생성할 계정 정보 조회
		for ( String key : accountInfo.keySet() )
		{
			System.out.println("[ImService - provisionAccount()] (Process Provision Account Data) key : " + key + " / value : " + accountInfo.get(key));
		}

		System.out.println("[ImService - provisionAccount()] END");
	}
}