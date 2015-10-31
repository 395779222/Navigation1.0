package cn.eainfo.common;



public class AjaxPageListData extends AjaxListData {
	private long pageAll;// 总页数
	private long pageNo;// 当前页码

	public long getPageAll() {
		return pageAll;
	}

	public void setPageAll(long pageAll) {
		this.pageAll = pageAll;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

}
