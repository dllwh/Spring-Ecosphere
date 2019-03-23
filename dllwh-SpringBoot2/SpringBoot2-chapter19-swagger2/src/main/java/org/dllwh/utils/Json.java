package org.dllwh.utils;

import java.util.HashMap;
import java.util.Map;

public class Json {
	public static Map<String, Object> success(Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("msg", "ok");
		map.put("data", data);
		return map;
	}

	public static Map<String, Object> success() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("msg", "ok");
		return map;
	}

	public static Map<String, Object> fail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 400);
		map.put("msg", "error");
		return map;
	}
}
