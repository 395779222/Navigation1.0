package cn.eainfo.common;

/**
 * 定义ibatis分页查询的基本条件，如果需要多条件查询需要实现子类 作者 金贤敏 日期 2010-07-16 版本 1.0
 * 
 */
public class BaseModel {
	private long start;// 记录开始的条数
	private long end;// 记录结束条数

	// 为ajax分页服务
	private long page;// page当前页码
	private long rows;// rows是当前页的行数
	private long pageSize;// pageSize每页的记录条数

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
}
