package org.dllwh.mybatis.service;

import org.dllwh.mybatis.entity.FunnyEntity;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 对外服务接口
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-10-13 9:01:54 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
*/
public interface FunnyService {
	int insert(FunnyEntity record);

	FunnyEntity getByPrimaryKey(Integer funnyId);
	
	FunnyEntity getByGroupId(String groupId);
}
