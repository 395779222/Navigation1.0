package cn.eainfo.common;

import java.util.List;

/**
 * 生成ajax列表对象
 */
public class AjaxListData {
	private long pageAll;// 总页数
	private String label;// 模块名称
	private List<Object> items;// 数据对象
	private String result;// 处理结果 成功：sucess 失败：faild
	private long pageNo;// 当前页码
	private String resume;// 错误发生时的提醒信息

	private Object reportCount;// 统计结果

	public Object getReportCount() {
		return reportCount;
	}

	public void setReportCount(Object reportCount) {
		this.reportCount = reportCount;
	}

	public long getPageAll() {
		return pageAll;
	}

	public void setPageAll(long pageAll) {
		this.pageAll = pageAll;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
}
