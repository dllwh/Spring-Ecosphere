package org.dllwh.service.impl;

import java.util.List;
import java.util.Map;

import org.dllwh.dao.GeneratorDao;
import org.dllwh.service.SysGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年5月25日 上午12:12:20
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {
	@Autowired
	private GeneratorDao generatorDao;

	@Override
	public int getTableCount(Map<String, Object> map) {
		return generatorDao.getTableCount(map);
	}

	@Override
	public List<Map<String, Object>> getTableList(Map<String, Object> map) {
		return generatorDao.getTableList(map);
	}

	@Override
	public Map<String, String> getTableInfo(String tableName) {
		return generatorDao.getTableInfo(tableName);
	}

	@Override
	public List<Map<String, String>> getColumns(String tableName) {
		return generatorDao.getColumns(tableName);
	}
}