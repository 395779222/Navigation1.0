//package cn.eainfo.spinfo.action;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//
////import c.eainfo.common.AjaxPageListData;
////import com.eainfo.common.ToolSpring;
////import com.eainfo.osgi.adr.dao.AdrReportDao;
//import com.opensymphony.xwork2.ActionSupport;
//
///**
// * adr报告导出
// */
//public class AdrReportExpAction extends ActionSupport {
//	private static final long serialVersionUID = 1L;
//
//	private String receiveTimeStart = "";
//	private String receiveTimeEnd = "";
//	private String degreeSelect = "";
//	private String adrNameSelect = "";
//	private String currentNameSelect = "";
//	/**
//	 * 下载参数
//	 */
//	private String contentDisposition;// 文件类型
//	private int fileSize;// 文件大小
//
//	/**
//	 * 下载execl原始文件
//	 * 
//	 * @throws UnsupportedEncodingException
//	 */
//	public InputStream getUploadResult() throws Exception {
//		try {
//			HSSFWorkbook wb = new HSSFWorkbook();
//
//			// 标题定义
//
//			// 标题定义
//			HSSFCellStyle styleFont = wb.createCellStyle();
//
//			HSSFCellStyle styleTitleLeft = wb.createCellStyle();
//			styleTitleLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			styleTitleLeft.setFillForegroundColor(HSSFColor.AQUA.index);
//			styleTitleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//			styleTitleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平
//			HSSFFont fontTitle = wb.createFont();
//			fontTitle.setFontHeightInPoints((short) 10);
//			styleTitleLeft.setFont(fontTitle);
//			styleTitleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			styleTitleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			styleTitleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			styleTitleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			styleTitleLeft.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
//			styleTitleLeft.setRightBorderColor(HSSFColor.DARK_BLUE.index);
//			styleTitleLeft.setTopBorderColor(HSSFColor.DARK_BLUE.index);
//			styleTitleLeft.setBottomBorderColor(HSSFColor.DARK_BLUE.index);
//
//			HSSFCellStyle styleContent = wb.createCellStyle();
//			styleContent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//			styleContent.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平
//			styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			styleContent.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContent.setRightBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContent.setTopBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContent.setBottomBorderColor(HSSFColor.DARK_BLUE.index);
//
//			HSSFCellStyle styleContentWrap = wb.createCellStyle();
//			styleContentWrap.setWrapText(true);
//			styleContentWrap
//					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//			styleContentWrap.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平
//			styleContentWrap.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			styleContentWrap.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			styleContentWrap.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			styleContentWrap.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			styleContentWrap.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContentWrap.setRightBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContentWrap.setTopBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContentWrap.setBottomBorderColor(HSSFColor.DARK_BLUE.index);
//
//			// 表格样式定义
//
//			HSSFFont font = wb.createFont();
//			font.setFontName("黑体");
//			font.setFontHeightInPoints((short) 18);
//			styleFont.setFont(font);
//
//			// 标题定义
//			HSSFCellStyle styleContentCount = wb.createCellStyle();
//			styleContentCount.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			font = wb.createFont();
//			font.setFontHeightInPoints((short) 12);
//			font.setColor(HSSFColor.RED.index);
//			styleContentCount.setFont(font);
//			styleContentCount
//					.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//			styleContentCount
//					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
//			styleContentCount.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 水平
//			styleContentCount.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			styleContentCount.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			styleContentCount.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			styleContentCount.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			styleContentCount.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContentCount.setRightBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContentCount.setTopBorderColor(HSSFColor.DARK_BLUE.index);
//			styleContentCount.setBottomBorderColor(HSSFColor.DARK_BLUE.index);
//
//			HSSFSheet sheet = wb.createSheet("ADR报告导出");
//			sheet.setColumnWidth((short) 0, (short) 3000);
//			sheet.setColumnWidth((short) 1, (short) 3000);
//			sheet.setColumnWidth((short) 3, (short) 3000);
//			sheet.setColumnWidth((short) 6, (short) 4000);
//			sheet.setColumnWidth((short) 7, (short) 4000);
//			sheet.setColumnWidth((short) 8, (short) 10000);
//			sheet.setColumnWidth((short) 9, (short) 15000);
//			sheet.setColumnWidth((short) 11, (short) 8000);
//			sheet.setColumnWidth((short) 12, (short) 5000);
//			sheet.setColumnWidth((short) 13, (short) 5000);
//			sheet.setColumnWidth((short) 14, (short) 3000);
//			sheet.setColumnWidth((short) 15, (short) 3000);
//			sheet.setColumnWidth((short) 16, (short) 10000);
//			sheet.setColumnWidth((short) 17, (short) 5000);
//			sheet.setColumnWidth((short) 18, (short) 12000);
//			sheet.setColumnWidth((short) 19, (short) 4000);
//			sheet.setColumnWidth((short) 20, (short) 4000);
//			sheet.setColumnWidth((short) 21, (short) 4000);
//
//			HSSFRow rowNumber = sheet.createRow(0);
//			rowNumber.setHeightInPoints(22);
//			HSSFCell colTemp = rowNumber.createCell(0);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("系统编号");
//
//			colTemp = rowNumber.createCell(1);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("报告类型");
//
//			colTemp = rowNumber.createCell(2);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("患者性别");
//
//			colTemp = rowNumber.createCell(3);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("出生年月");
//
//			colTemp = rowNumber.createCell(4);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("民族");
//
//			colTemp = rowNumber.createCell(5);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("体重");
//
//			colTemp = rowNumber.createCell(6);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("家族ADR");
//
//			colTemp = rowNumber.createCell(7);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("既往ADR");
//
//			colTemp = rowNumber.createCell(8);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("ADR名称");
//
//			colTemp = rowNumber.createCell(9);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("ADR描述及处理");
//
//			colTemp = rowNumber.createCell(10);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("怀疑并用");
//
//			colTemp = rowNumber.createCell(11);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("通用名称");
//
//			colTemp = rowNumber.createCell(12);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("给药途径");
//
//			colTemp = rowNumber.createCell(13);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("用法用量");
//
//			colTemp = rowNumber.createCell(14);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("用药开始日期");
//
//			colTemp = rowNumber.createCell(15);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("用药结束日期");
//
//			colTemp = rowNumber.createCell(16);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("用药原因");
//
//			colTemp = rowNumber.createCell(17);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("ADR结果");
//
//			colTemp = rowNumber.createCell(18);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("原患疾病");
//
//			colTemp = rowNumber.createCell(19);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("对原患疾病影响");
//
//			colTemp = rowNumber.createCell(20);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("ADR分析");
//
//			colTemp = rowNumber.createCell(21);
//			colTemp.setCellStyle(styleTitleLeft);
//			colTemp.setCellValue("报告单位类型");
//
//			AdrReportDao adrReportDao = (AdrReportDao) ToolSpring
//					.getBean("adrReportDao");
//			if (!"".equals(receiveTimeStart)) {
//				receiveTimeStart = receiveTimeStart + " 00:00:00";
//			}
//			if (!"".equals(receiveTimeEnd)) {
//				receiveTimeEnd = receiveTimeEnd + " 23:59:59";
//			}
//			String degree = "";
//			if (degreeSelect != null && !"".equals(degreeSelect)) {
//				degree = new String(degreeSelect.getBytes("ISO-8859-1"),
//						"utf-8");
//			}
//
//			String adrName = "";
//			if (adrNameSelect != null && !"".equals(adrNameSelect)) {
//				adrName = new String(adrNameSelect.getBytes("ISO-8859-1"),
//						"utf-8");
//			}
//
//			String currentName = "";
//			if (currentNameSelect != null && !"".equals(currentNameSelect)) {
//				currentName = new String(currentNameSelect
//						.getBytes("ISO-8859-1"), "utf-8");
//			}
//
//			int pageSize = 5000;
//			AjaxPageListData ajaxPageListData = adrReportDao.getAdrReportList(
//					degree, receiveTimeStart, receiveTimeEnd, adrName,
//					currentName, 1, pageSize);
//
//			long pageAll = ajaxPageListData.getPageAll();// 总页面数
//			int rowCount = 1;
//			for (int z = 1; z <= pageAll; z++) {
//				ajaxPageListData = adrReportDao.getAdrReportList(degree,
//						receiveTimeStart, receiveTimeEnd, adrName, currentName,
//						z, pageSize);
//				List adrList = ajaxPageListData.getItems();
//
//				if (adrList != null && adrList.size() > 0) {
//					int count = adrList.size();
//					for (int i = 0; i < count; i++) {
//						Object[] objects = (Object[]) adrList.get(i);
//						HSSFRow rowTemp = sheet.createRow(rowCount);
//						rowTemp.setHeightInPoints(22);
//
//						colTemp = rowTemp.createCell(0);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[1].toString());
//
//						colTemp = rowTemp.createCell(1);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[2].toString());
//
//						colTemp = rowTemp.createCell(2);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[3].toString());
//
//						colTemp = rowTemp.createCell(3);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[4].toString());
//
//						colTemp = rowTemp.createCell(4);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[5].toString());
//
//						colTemp = rowTemp.createCell(5);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[6].toString());
//
//						colTemp = rowTemp.createCell(6);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[7].toString());
//
//						colTemp = rowTemp.createCell(7);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[8].toString());
//
//						colTemp = rowTemp.createCell(8);
//						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue(objects[9].toString());
//
//						colTemp = rowTemp.createCell(9);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[10].toString());
//
//						colTemp = rowTemp.createCell(10);
//						colTemp.setCellStyle(styleContentWrap);
//						String suspicionOrTogether = objects[11].toString();
//						if (suspicionOrTogether != null
//								&& "1".equals(suspicionOrTogether)) {
//							colTemp.setCellValue("怀疑");
//						} else {
//							colTemp.setCellValue("并用");
//						}
//
//						colTemp = rowTemp.createCell(11);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[12].toString());
//
//						colTemp = rowTemp.createCell(12);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[13].toString());
//
//						colTemp = rowTemp.createCell(13);
//						colTemp.setCellStyle(styleContentWrap);
//						String numberString = "";
//						String number = objects[14].toString();
//						if (number != null && !"".equals(number)) {
//							numberString = (number.split("\\."))[0];
//						}
//						colTemp.setCellValue(numberString
//								+ objects[15].toString() + "/"
//								+ objects[16].toString() + "日 "
//								+ objects[17].toString() + "次");
//
//						colTemp = rowTemp.createCell(14);
//						colTemp.setCellStyle(styleContentWrap);
//						String startTime = objects[18].toString();
//						if (startTime != null && !"".equals(startTime)) {
//							colTemp.setCellValue(startTime.split(" ")[0]);
//						} else {
//							colTemp.setCellValue("");
//						}
//						colTemp = rowTemp.createCell(15);
//						colTemp.setCellStyle(styleContentWrap);
//						String endTime = objects[19].toString();
//						if (endTime != null && !"".equals(endTime)) {
//							colTemp.setCellValue(endTime.split(" ")[0]);
//						} else {
//							colTemp.setCellValue("");
//						}
//
//						colTemp = rowTemp.createCell(16);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[20].toString());
//
//						colTemp = rowTemp.createCell(17);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[21].toString());
//
//						colTemp = rowTemp.createCell(18);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[22].toString());
//
//						colTemp = rowTemp.createCell(19);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[23].toString());
//
//						colTemp = rowTemp.createCell(20);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(objects[24].toString());
//
//						String orgClassValue = "";
//						String orgClass = objects[25].toString();
//						if (orgClass != null && "生产企业".equals(orgClass.trim())) {
//							orgClassValue = orgClass;
//						} else {
//							orgClassValue = "非生产企业";
//						}
//						colTemp = rowTemp.createCell(21);
//						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue(orgClassValue);
//
//						rowCount = rowCount + 1;
//					}
//				}
//
//			}
//
//			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
//			wb.write(bytestream);
//			byte imgdata[] = bytestream.toByteArray();
//			bytestream.close();
//			String downFileName = new String("ADR报告导出.xls".getBytes(),
//					"ISO8859-1");
//			setContentDisposition("attachment;filename=\"" + downFileName
//					+ "\"");
//			return new ByteArrayInputStream(imgdata);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public String getContentDisposition() {
//		return contentDisposition;
//	}
//
//	public void setContentDisposition(String contentDisposition) {
//		this.contentDisposition = contentDisposition;
//	}
//
//	public int getFileSize() {
//		return fileSize;
//	}
//
//	public void setFileSize(int fileSize) {
//		this.fileSize = fileSize;
//	}
//
//	public String getReceiveTimeStart() {
//		return receiveTimeStart;
//	}
//
//	public void setReceiveTimeStart(String receiveTimeStart) {
//		this.receiveTimeStart = receiveTimeStart;
//	}
//
//	public String getReceiveTimeEnd() {
//		return receiveTimeEnd;
//	}
//
//	public void setReceiveTimeEnd(String receiveTimeEnd) {
//		this.receiveTimeEnd = receiveTimeEnd;
//	}
//
//	public String getDegreeSelect() {
//		return degreeSelect;
//	}
//
//	public void setDegreeSelect(String degreeSelect) {
//		this.degreeSelect = degreeSelect;
//	}
//
//	public String getAdrNameSelect() {
//		return adrNameSelect;
//	}
//
//	public void setAdrNameSelect(String adrNameSelect) {
//		this.adrNameSelect = adrNameSelect;
//	}
//
//	public String getCurrentNameSelect() {
//		return currentNameSelect;
//	}
//
//	public void setCurrentNameSelect(String currentNameSelect) {
//		this.currentNameSelect = currentNameSelect;
//	}
//
//}
