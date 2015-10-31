package cn.eainfo.system.model;

/**
 * 菜单查询模型
 */
public class SysMenuModel {
	private long userId;// 用户编号
	private long moduleParentId;// 父级菜单编号

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getModuleParentId() {
		return moduleParentId;
	}

	public void setModuleParentId(long moduleParentId) {
		this.moduleParentId = moduleParentId;
	}
}
