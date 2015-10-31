package cn.eainfo.common;

import java.util.List;

/**
 * 定义系统数据的分页 作者 金贤敏 日期 2010-07-20 版本 1.0
 * 
 */
public class PageBean {
	private long showPage;// 显示的页面数
	private long pageNum;// 总页面数
	private long curPage = 1;// 当前页
	private long sum;// 总数
	private long perPageNum = 20;// 每页个数
	private long start;// 记录开始的条数
	private long end;// 记录结束条数
	private List data;// 数据

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public long getShowPage() {
		return showPage;
	}

	public void setShowPage(long showPage) {
		this.showPage = showPage;
	}

	/**
	 * 分页对象初始化
	 * 
	 * @param curPage
	 *            当前页
	 * 
	 * @param sum
	 *            总数
	 */
	public PageBean(long curPage, long sum) {
		if (curPage != 0) {
			this.curPage = curPage;
		}
		this.sum = sum;
		this.init();
	}

	/**
	 * 分页对象初始化
	 * 
	 * @param curPage
	 *            当前页
	 * @param perPageNum
	 *            每页个数
	 * @param sum
	 *            总数
	 */
	public PageBean(long curPage, long perPageNum, long sum) {
		if (curPage != 0) {
			this.curPage = curPage;
		}
		if (perPageNum != 0) {
			this.perPageNum = perPageNum;
		}

		this.sum = sum;
		this.init();
	}

	public long getMaxNum() {
		return perPageNum - 1;
	}

	public PageBean(long curPage) {
		this.curPage = curPage;

	}

	public static long countCurPage(long curPage, long pageNum) {
		if (pageNum == 0) {
			curPage = 0;
		} else if (curPage > pageNum) {
			curPage = pageNum;
		}
		return curPage;
	}

	public static long countPageNum(long sum, long perPageNum) {
		return (sum + perPageNum - 1) / perPageNum;
	}

	public static long countStart(long curPage, long perPageNum) {
		long start = 0;
		if ((curPage - 1) * perPageNum > 0) {
			start = (curPage - 1) * perPageNum;
		}
		return start;
	}

	public void init() {
		this.pageNum = countPageNum(this.sum, this.perPageNum);
		this.curPage = countCurPage(curPage, pageNum);
		this.start = countStart(curPage, perPageNum);
		this.end = start + perPageNum;
	}

	/**
	 * 获取页面分页代码
	 * 
	 */
	public String getPaging() {
		if (pageNum != 0) {
			StringBuffer htmlCode = new StringBuffer();

			if (pageNum > showPage) {
				htmlCode.append("<a href='javascript:go(1)'>&lt;&lt;</a>");
			}
			if (curPage > 1) {
				htmlCode.append("<a href='javascript:go(" + (curPage - 1)
						+ ")'>&lt;</a>");

			}
			long startNumber = curPage - (long) Math.floor(showPage / 2) + 1;
			if (startNumber < 1) {
				startNumber = 1;
			}
			long endNumber = curPage + (long) Math.floor(showPage / 2) + 1;
			if (endNumber > pageNum) {
				endNumber = pageNum;
			}
			for (long i = startNumber; i <= endNumber; i++) {
				if (i == curPage) {
					htmlCode.append("<a class='pageSelect' href='javascript:go("
							+ i + ")'>" + i + "</a>");
				} else {
					htmlCode.append("<a href='javascript:go(" + i + ")'>" + i
							+ "</a>");
				}
			}
			if (pageNum > showPage && curPage != pageNum
					&& curPage + showPage < pageNum) {
				htmlCode.append("...");
			}
			if (pageNum > curPage) {
				htmlCode.append("<a href='javascript:go(" + (curPage + 1)
						+ ")'>&gt;</a>");
			}
			if (pageNum > 1 && curPage != pageNum) {
				htmlCode.append("<a href='javascript:go(" + pageNum
						+ ")'>&gt;&gt;</a>");
			}
			htmlCode.append("<input type='hidden' name='page' id='page' value='"
					+ curPage + "'/>");
			return htmlCode.toString();
		} else {
			return "<div style='height:20px'>目前没有数据！</div>";
		}

	}

	public String getHtml() {
		StringBuffer htmlCode = new StringBuffer();
		htmlCode.append("<script language='JavaScript'>\r\n");
		htmlCode.append("function Jumping(){\r\n");
		htmlCode.append("document.forms[0].submit();\r\n");
		htmlCode.append("return;}\r\n");
		htmlCode.append("function gotoPage(page){\r\n");
		htmlCode.append("document.forms[0].page.value=page;\r\n");
		htmlCode.append("Jumping();\r\n");
		htmlCode.append("return;}\r\n");
		htmlCode.append("</script>\r\n");
		htmlCode.append("<div align='right'>\r\n");
		htmlCode.append(" 共 <font color='red'>" + sum + "</font> 条&nbsp;\r\n");
		htmlCode.append(" 每页 <font color='red'>" + perPageNum
				+ "</font> 条&nbsp;\r\n");
		htmlCode.append(" 第 <font color='red'>" + curPage
				+ "</font>/<font color='red'>" + pageNum
				+ "</font> 页&nbsp;\r\n");
		if (curPage < 2) {
			htmlCode.append("首页&nbsp;上一页&nbsp;\r\n");
		} else {
			htmlCode.append("<a onclick='gotoPage(1)'>首页</a>&nbsp;\r\n");
			htmlCode.append(" <a onclick='gotoPage(" + (curPage - 1)
					+ ")'>上一页</a>&nbsp;\r\n");
		}
		if (curPage >= pageNum) {
			htmlCode.append("下一页&nbsp;尾页&nbsp;\r\n");
		} else {
			htmlCode.append("<a onclick='gotoPage(" + (curPage + 1)
					+ ")'>下一页</a>&nbsp;\r\n");
			htmlCode.append("<a onclick='gotoPage(" + pageNum
					+ ")'> 尾页</a>&nbsp;\r\n");
		}
		htmlCode.append("转到第<select name='page' id='page' onchange='Jumping()'>\r\n");
		if (this.pageNum == 0) {
			htmlCode.append("<option value='0'>0</option>\r\n");
		} else {
			for (long i = 1; i <= this.pageNum; i++) {
				if (i == this.curPage) {
					htmlCode.append("<option selected value=" + i + ">" + i
							+ "</option>\r\n");
				} else {
					htmlCode.append("<option value=" + i + ">" + i
							+ "</option>\r\n");
				}
			}
		}

		htmlCode.append("</select>页</div>\r\n");
		return htmlCode.toString();
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;

	}

	public long getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(long perPageNum) {
		this.perPageNum = perPageNum;
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