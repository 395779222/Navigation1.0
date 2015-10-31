package cn.eainfo.common;

import java.util.List;

/**
 * 生成符合esayui框架的列表对象
 */
public class AjaxJsonListData {
private long total;
	private List rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
