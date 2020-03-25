package org.dllwh.model;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 群组数据
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-25 9:45:52 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class BaseGroup {
	/** 群组 ID，群组唯一标识符 */
	protected Long id;
	/** 群组名称，根据用户输入创建 */
	private String groupName;
	/** 群组描述，根据用户输入创建 */
	private String description;
	/** 群组类型：true：公开群，false：私有群 */
	private Boolean ifPublic;
	/** 加入群组是否需要群主或者群管理员审批。true：是，false：否。 */
	private Boolean membersonly;
	/** 是否允许群成员邀请别人加入此群。 true：允许群成员邀请人加入此群，false：只有群主才可以往群里加人。 */
	private Boolean allowInvites;
	/** 群成员上限，创建群组的时候设置，可修改 */
	private int maxUsers;
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
