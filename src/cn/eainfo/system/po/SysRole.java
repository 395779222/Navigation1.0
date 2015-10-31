package cn.eainfo.system.po;

/**
 * 系统角色
 */
public class SysRole {
	private long roleId;// 角色编号
	private String roleName;// 角色名称
	private String roleDesc;// 角色描述
	private int isSystem;// 是否系统管理 1.是 2.否
	private int status;// 状态 1.正常 2.删除
	private String userIdsString; 
	private String menuIdsString;

	private String isSystemName;// 系统属性

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public int getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}

	public String getIsSystemName() {
		return isSystemName;
	}

	public void setIsSystemName(String isSystemName) {
		this.isSystemName = isSystemName;
	}

	public String getUserIdsString() {
		return userIdsString;
	}

	public void setUserIdsString(String userIdsString) {
		this.userIdsString = userIdsString;
	}

	public String getMenuIdsString() {
		return menuIdsString;
	}

	public void setMenuIdsString(String menuIdsString) {
		this.menuIdsString = menuIdsString;
	}
}
