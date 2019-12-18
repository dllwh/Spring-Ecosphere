package org.dllwh.model.rbac;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SysRoleVO implements Serializable {

	private static final long serialVersionUID = -4814743654952399953L;
	/** 角色名称 */
	private String            roleName;
	/** 角色编码 */
	private String            roleCode;
	/** 角色分类 */
	private Integer           categoryCode;
	/** 最初创建者 */
	private Integer           creator;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	/** 数据创建时间 */
	private Date              createTime;
	/** 最后修改人 */
	private Integer           modifier;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	/** 数据最后更新时间 */
	private Date              updateTime;
	/** 实体编号（唯一标识） */
	private Integer           id;
	/** 是否可见;1:可见,默认值;0:不可见 */
	private Integer           ifVisible;
	/** 是否有效;-1:删除;0:不可用,默认值;1:可用 */
	private Integer           ifEnabled;
	/** 是否允许编辑;1:允许,默认值;0:不允许 */
	private Integer           allowEdit;
	/** 是否允许删除;1:允许删除,默认值,0:不允许删除 */
	private Integer           allowDelete;
	/** 排序,默认从1开始 */
	private Integer           sequence;
	/** 备注、说明 */
	private String            remark;
}
