package cn.eainfo.system.model;

public class MenuTopModel {

	
	private long menuId;//
	private String menuName;// : '系统管理',
	private String moduleDesc;// : '管理系统中用户角色权限'
	
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	
}
