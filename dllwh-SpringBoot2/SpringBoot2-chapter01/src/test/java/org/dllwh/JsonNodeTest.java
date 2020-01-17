package org.dllwh;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JsonNodeTest {
	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		// 美化输出
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		List<Map<String, Object>> nodeList = Lists.newArrayList();
		Map<String, Object> nodeMap = Maps.newHashMap();
		nodeMap.put("id", 1001);
		nodeMap.put("text", "北京");
		nodeList.add(nodeMap);
		nodeMap.put("id", 1002);
		nodeMap.put("text", "天津");
		nodeList.add(nodeMap);
		Map<String, Object> treeMap = Maps.newHashMap();
		treeMap.put("id", 1);
		treeMap.put("text", "城市管理");
		treeMap.put("nodes", nodeList);
		// String jsonData = mapper.writeValueAsString(nodeList);
		String jsonData = mapper.writeValueAsString(treeMap);
		System.out.println(jsonData);
	}
}
