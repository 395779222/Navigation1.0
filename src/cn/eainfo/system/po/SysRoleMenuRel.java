package cn.eainfo.system.po;

/**
 * 角色菜单关系
 */
public class SysRoleMenuRel {
	private long roleId;// 角色id
	private long menuId;// 菜单id

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
}
