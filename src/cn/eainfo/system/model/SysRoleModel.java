package cn.eainfo.system.model;
/**
 * 角色查询模型
 * */
import cn.eainfo.common.BaseModel;

public class SysRoleModel extends BaseModel {
	private String roleName;// 角色名称
	private int isSystem;// 是否系统管理
	private int status;// 状态 1.正常 2.删除

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}
}
