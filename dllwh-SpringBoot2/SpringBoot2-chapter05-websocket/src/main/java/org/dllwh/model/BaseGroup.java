package org.dllwh.model;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-25 9:45:52 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class BaseGroup {
	/** 实体编号（唯一标识） */
	protected Long id;
	/** 群组名 */
	private String groupName;
	/** 群组头像 */
	private String avatar;
	/** 最初创建者 */
	protected Long creator;
	/** 创建人账号 */
	protected String creatorAccount;
	/** 数据创建时间 */
	protected Timestamp createTime;
	/** 最后修改人 */
	protected Long modifier;
	/** 修改人账号 */
	protected String modifyAccount;
	/** 最后更新时间 */
	protected Timestamp lastModifiedTime;
}
