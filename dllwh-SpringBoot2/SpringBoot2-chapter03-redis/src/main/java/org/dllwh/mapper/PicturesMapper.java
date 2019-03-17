package org.dllwh.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dllwh.entity.Pictures;

@Mapper
public interface PicturesMapper {

	int insertOrUpdate(Pictures entity);
}