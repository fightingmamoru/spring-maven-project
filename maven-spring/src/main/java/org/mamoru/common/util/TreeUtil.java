package org.mamoru.common.util;

import java.util.*;

public class TreeUtil
{
	public static List<Map<String, Object>> convertTreeMapList(List<Map<String, Object>> dataList, String rootValue, final String keyName, final String parentKeyName, final String textName, final String orderName) throws Exception
	{
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();

		if ( dataList == null || dataList.size() == 0 )
		{
			throw new Exception("");
		}

		if ( dataList.get(0) == null )
		{
			throw new Exception("");
		}

		final List<Map<String, Object>> originDataList = new ArrayList<Map<String, Object>>(dataList);

		while ( originDataList.size() > 0 )
		{
			System.out.println("[DataList Count] " + originDataList.size());

			for ( int i=0; i<originDataList.size(); i++ )
			{
				Map<String, Object> originData = originDataList.get(i);

				if ( rootValue.equals(originData.get(parentKeyName)) )
				{
					Map<String, Object> treeData = new HashMap<String, Object>(originData);

					treeData.put("text", treeData.get(textName));
					treeData.put("value", treeData.get(keyName));

					treeList.add(treeData);

					originDataList.remove(originData);

					// todo : treeList를 정렬

					break;
				}
				else
				{
					new TreeUtilClass()
					{
						public void getParentNode(List<Map<String, Object>> childList, Map<String, Object> data)
						{
							for ( int i=0; i<childList.size(); i++ )
							{
								Map<String, Object> child = childList.get(i);

								if ( child.get(keyName).equals(data.get(parentKeyName)) )
								{
									Map<String, Object> treeData = new HashMap<String, Object>(data);

									treeData.put("text", treeData.get(textName));
									treeData.put("value", treeData.get(keyName));

									if ( child.get("nodes") == null )
									{
										child.put("nodes", new ArrayList<Map<String, Object>>());
									}

									((List<Map<String, Object>>) child.get("nodes")).add(treeData);

									originDataList.remove(originDataList.indexOf(data));

									// todo : treeList를 정렬

									break;
								}
								else
								{
									if ( child.get("nodes")!=null && ((List<Map<String, Object>>) child.get("nodes")).size() > 0 )
									{
										getParentNode(((List<Map<String, Object>>) child.get("nodes")), data);
									}
								}
							}
						}
					}.getParentNode(treeList, originData);
				}
			}
		}

		return treeList;
	}

	public interface TreeUtilClass
	{
		void getParentNode(List<Map<String, Object>> treeList, Map<String, Object> data);
	}
}