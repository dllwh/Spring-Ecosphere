package org.dllwh.service;

import java.io.Serializable;
import java.util.List;

import org.dllwh.entity.Pictures;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 服务实现接口
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月15日 下午10:52:30
 * @版本: V1.0.1
 * @since: JDK 1.8
 */

public interface PicturesService {
	boolean insert(Pictures entity);

	boolean insertBatch(List<Pictures> entityList);

	boolean deleteById(Serializable id);

	boolean update(Pictures entity);

	List<Pictures> selectList(Pictures entity);

	int selectCount(Pictures entity);

	Pictures selectById(Serializable id);
	
	boolean insertOrUpdate(Pictures entity);
}