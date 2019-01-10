package com.cdeledu.modules.system.sysconfig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdeledu.modules.system.upms.domain.SysDict;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 字典表数据层
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月8日 下午5:51:32
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Mapper
public interface SysDictMapper {
	/**
	 * @方法描述 : 据条件分页查询字典数据
	 * @param dictData
	 *            字典数据信息
	 * @return 字典数据集合信息
	 */
	public List<SysDict> getDictDataList(SysDict dictData);

	/**
	 * @方法描述 : 据条件分页统计字典数据
	 * @param dictData
	 * @return
	 */
	public int getCountDictData(SysDict dictData);

	/**
	 * @方法描述 :根据字典数据ID查询信息
	 * @param dictCode
	 *            字典数据ID
	 * @return 字典数据
	 */
	public SysDict selectDictDataById(Integer id);

	/**
	 * @方法描述 :通过字典ID删除字典数据信息
	 * @param dictCode
	 *            字典数据ID
	 * @return 结果
	 */
	public int deleteDictDataById(Integer id);

	/**
	 * @方法描述 :批量删除字典数据
	 * @param ids
	 *            需要删除的数据
	 * @return 结果
	 */
	public int deleteDictDataByIds(Integer[] ids);

	/**
	 * @方法描述 : 新增保存字典数据信息
	 * @param dictData
	 *            字典数据信息
	 * @return 结果
	 */
	public int insertDictData(SysDict dictData);

	/**
	 * @方法描述 : 修改保存字典数据信息
	 * @param dictData
	 *            字典数据信息
	 * @return 结果
	 */
	public int updateDictData(SysDict dictData);

	/**
	 * @方法描述 : 校验字典类型是否唯一
	 * @param dictCode
	 *            字典类型
	 * @return 结果
	 */
	public String checkDictCodeUnique(String dictCode);
}