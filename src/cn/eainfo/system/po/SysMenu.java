package cn.eainfo.system.po;

/**
 * 系统菜单
 */
public class SysMenu {
	// 数据库字段
	private long moduleId;// 菜单编号
	private long moduleParentId;// 父级菜单编号
	private int moduleFolder;// 是否目录 1：目录 2：菜单
	private String moduleName;// 菜单名称
	private String moduleDesc;// 菜单描述
	private String moduleUri;// 菜单地址
	private int orderNumber;// 排序

	// 数据库字段

	private String moduleParentName;// 父级菜单名称
	private String moduleFoldername;// 类型名称

	public String getModuleFoldername() {
		return moduleFoldername;
	}

	public void setModuleFoldername(String moduleFoldername) {
		this.moduleFoldername = moduleFoldername;
	}

	public String getModuleParentName() {
		return moduleParentName;
	}

	public void setModuleParentName(String moduleParentName) {
		this.moduleParentName = moduleParentName;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getModuleParentId() {
		return moduleParentId;
	}

	public void setModuleParentId(long moduleParentId) {
		this.moduleParentId = moduleParentId;
	}

	public int getModuleFolder() {
		return moduleFolder;
	}

	public void setModuleFolder(int moduleFolder) {
		this.moduleFolder = moduleFolder;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getModuleUri() {
		return moduleUri;
	}

	public void setModuleUri(String moduleUri) {
		this.moduleUri = moduleUri;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
}
