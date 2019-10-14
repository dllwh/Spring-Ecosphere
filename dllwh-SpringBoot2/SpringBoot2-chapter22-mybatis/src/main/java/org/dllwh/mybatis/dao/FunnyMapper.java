package org.dllwh.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dllwh.mybatis.entity.FunnyEntity;
import org.springframework.stereotype.Repository;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 服务实现类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-10-13 8:49:32 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Repository
@Mapper
public interface FunnyMapper {
	int insert(FunnyEntity record);

	FunnyEntity getByPrimaryKey(Integer funnyId);

	FunnyEntity getByGroupId(String groupId);
}
