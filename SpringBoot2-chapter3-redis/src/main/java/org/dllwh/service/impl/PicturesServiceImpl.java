package org.dllwh.service.impl;

import java.io.Serializable;
import java.util.List;

import org.dllwh.entity.Pictures;
import org.dllwh.mapper.PicturesMapper;
import org.dllwh.service.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 服务实现类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月15日 下午10:55:37
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class PicturesServiceImpl implements PicturesService {
	@Autowired
	private PicturesMapper picturesMapper;

	@Override
	public boolean insert(Pictures entity) {
		return false;
	}

	@Override
	public boolean insertBatch(List<Pictures> entityList) {
		return false;
	}

	@Override
	public boolean deleteById(Serializable id) {
		return false;
	}

	@Override
	public boolean update(Pictures entity) {
		return false;
	}

	@Override
	public List<Pictures> selectList(Pictures entity) {
		return null;
	}

	@Override
	public int selectCount(Pictures entity) {
		return 0;
	}

	@Override
	public Pictures selectById(Serializable id) {
		return null;
	}

	@Override
	public boolean insertOrUpdate(Pictures entity) {
		picturesMapper.insertOrUpdate(entity);
		return false;
	}
}