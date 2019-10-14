package org.dllwh.mybatis.service.impl;

import org.dllwh.mybatis.dao.FunnyMapper;
import org.dllwh.mybatis.entity.FunnyEntity;
import org.dllwh.mybatis.service.FunnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-10-13 9:02:22 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class FunnyServiceImpl implements FunnyService {
	@Autowired
	private FunnyMapper funnyMapper;

	@Override
	public int insert(FunnyEntity record) {
		return funnyMapper.insert(record);
	}

	@Override
	public FunnyEntity getByPrimaryKey(Integer funnyId) {
		return funnyMapper.getByPrimaryKey(funnyId);
	}

	@Override
	public FunnyEntity getByGroupId(String groupId) {
		return funnyMapper.getByGroupId(groupId);
	}

}
