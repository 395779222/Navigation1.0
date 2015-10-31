package cn.eainfo.system.po;

/**
 * 系统用户角色关系
 */
public class SysUserRoleRel {
	private long roleId;// 角色id
	private long userId;// 用户id

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
