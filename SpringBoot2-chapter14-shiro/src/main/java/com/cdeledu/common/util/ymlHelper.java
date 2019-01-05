package com.cdeledu.common.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 配置处理工具类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月5日 下午2:40:34
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public final class ymlHelper {
	private static Map<String, String>	yamlMap		= new HashMap<String, String>();
	private final static String			YAML_SOURCE	= "application.yml";

	static {
		/**
		 * 利用静态代码块的特性 -> 获取全局配置
		 */
		InputStream inputStream = ymlHelper.class.getClassLoader().getResourceAsStream(YAML_SOURCE);
		Iterator<Object> result = new Yaml().loadAll(inputStream).iterator();
		while (result.hasNext()) {
			Map map = (Map) result.next();
			iteratorYml(map, null);
		}
	}

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		return yamlMap.get(key);
	}

	private static void iteratorYml(Map map, String key) {
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			Object key2 = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof LinkedHashMap) {
				if (key == null) {
					iteratorYml((Map) value, key2.toString());
				} else {
					iteratorYml((Map) value, key + "." + key2.toString());
				}
			}
			if (value instanceof String) {
				if (key == null) {
					yamlMap.put(key2.toString(), value.toString());
				}
				if (key != null) {
					yamlMap.put(key + "." + key2.toString(), value.toString());
				}
			}
		}

	}
}