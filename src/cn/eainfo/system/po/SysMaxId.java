package cn.eainfo.system.po;

/**
 * 系统id管理
 */
public class SysMaxId {
	// 数据库字段
	private String fieldName;// 系统表名+id名称,主键
	private long maxValue;// id最大值

	// 数据库字段

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}

}
