package org.mamoru.common.util;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtilTest
{
	@Test
	public void test() throws Exception
	{
		// 테스트 데이터 생성
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();



		Map<String, Object> data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES100");
		data.put("upper_mngr_resrce_id", "0");
		data.put("mngr_resrce_nm", "자원100");
		data.put("mngr_resrce_ordr", 1);

		dataList.add(data);


		data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES221");
		data.put("upper_mngr_resrce_id", "RES220");
		data.put("mngr_resrce_nm", "자원221");
		data.put("mngr_resrce_ordr", 1);

		dataList.add(data);


		data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES200");
		data.put("upper_mngr_resrce_id", "0");
		data.put("mngr_resrce_nm", "자원200");
		data.put("mngr_resrce_ordr", 2);

		dataList.add(data);


		data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES110");
		data.put("upper_mngr_resrce_id", "RES100");
		data.put("mngr_resrce_nm", "자원110");
		data.put("mngr_resrce_ordr", 1);

		dataList.add(data);


		data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES120");
		data.put("upper_mngr_resrce_id", "RES100");
		data.put("mngr_resrce_nm", "자원120");
		data.put("mngr_resrce_ordr", 2);

		dataList.add(data);


		data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES210");
		data.put("upper_mngr_resrce_id", "RES200");
		data.put("mngr_resrce_nm", "자원210");
		data.put("mngr_resrce_ordr", 1);

		dataList.add(data);


		data = new HashMap<String, Object>();

		data.put("mngr_resrce_id", "RES220");
		data.put("upper_mngr_resrce_id", "RES200");
		data.put("mngr_resrce_nm", "자원220");
		data.put("mngr_resrce_ordr", 2);

		dataList.add(data);


		Gson gson = new Gson();

		List<Map<String, Object>> treeList = TreeUtil.convertTreeMapList(dataList, "0", "mngr_resrce_id", "upper_mngr_resrce_id", "mngr_resrce_nm", "");
		//List<Map<String, Object>> treeList = JsonUtil.convertorTreeMap(dataList, "0", "mngr_resrce_id", "upper_mngr_resrce_id", "mngr_resrce_nm");

		System.out.println("[Data Check]" + gson.toJson(treeList));
	}
}