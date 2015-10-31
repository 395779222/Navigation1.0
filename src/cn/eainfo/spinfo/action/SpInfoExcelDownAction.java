package cn.eainfo.spinfo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import cn.eainfo.spinfo.service.SpInfoService;

import com.opensymphony.xwork2.ActionSupport;

public class SpInfoExcelDownAction extends ActionSupport{

	private SpInfoService spInfoService;
	/**  
	 * @Fields serialVersionUID : TODO  
	 */  
	    
	private static final long serialVersionUID = 5858377927949898105L;
	/**
	 * 下载参数
	 */
	private String contentDisposition;// 文件类型
	private int fileSize;// 文件大小
	/**
	 * 下载execl原始文件
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public InputStream getUploadResult() throws Exception {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			// 标题定义
			HSSFCellStyle styleFont = wb.createCellStyle();

			HSSFCellStyle styleTitleLeft = wb.createCellStyle();
			styleTitleLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleTitleLeft.setFillForegroundColor(HSSFColor.AQUA.index);
			styleTitleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			styleTitleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平
			HSSFFont fontTitle = wb.createFont();
			fontTitle.setFontHeightInPoints((short) 10);
			styleTitleLeft.setFont(fontTitle);
			styleTitleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleTitleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleTitleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleTitleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleTitleLeft.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
			styleTitleLeft.setRightBorderColor(HSSFColor.DARK_BLUE.index);
			styleTitleLeft.setTopBorderColor(HSSFColor.DARK_BLUE.index);
			styleTitleLeft.setBottomBorderColor(HSSFColor.DARK_BLUE.index);

			HSSFCellStyle styleContent = wb.createCellStyle();
			styleContent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			styleContent.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平
			styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleContent.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
			styleContent.setRightBorderColor(HSSFColor.DARK_BLUE.index);
			styleContent.setTopBorderColor(HSSFColor.DARK_BLUE.index);
			styleContent.setBottomBorderColor(HSSFColor.DARK_BLUE.index);

			HSSFCellStyle styleContentWrap = wb.createCellStyle();
			styleContentWrap.setWrapText(true);
			styleContentWrap
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			styleContentWrap.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平
			styleContentWrap.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleContentWrap.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleContentWrap.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleContentWrap.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleContentWrap.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
			styleContentWrap.setRightBorderColor(HSSFColor.DARK_BLUE.index);
			styleContentWrap.setTopBorderColor(HSSFColor.DARK_BLUE.index);
			styleContentWrap.setBottomBorderColor(HSSFColor.DARK_BLUE.index);

			// 表格样式定义
			HSSFFont font = wb.createFont();
			font.setFontName("黑体");
			font.setFontHeightInPoints((short) 18);
			styleFont.setFont(font);

			// 标题定义
			HSSFCellStyle styleContentCount = wb.createCellStyle();
			styleContentCount.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			font = wb.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setColor(HSSFColor.RED.index);
			styleContentCount.setFont(font);
			styleContentCount
					.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			styleContentCount
					.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			styleContentCount.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 水平
			styleContentCount.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleContentCount.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleContentCount.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleContentCount.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleContentCount.setLeftBorderColor(HSSFColor.DARK_BLUE.index);
			styleContentCount.setRightBorderColor(HSSFColor.DARK_BLUE.index);
			styleContentCount.setTopBorderColor(HSSFColor.DARK_BLUE.index);
			styleContentCount.setBottomBorderColor(HSSFColor.DARK_BLUE.index);

			HSSFSheet sheet = wb.createSheet("SP信息导出");
			//设置宽度
			sheet.setColumnWidth((short) 0, (short) 3000);
			sheet.setColumnWidth((short) 1, (short) 3000);
			sheet.setColumnWidth((short) 2, (short) 5000);
			sheet.setColumnWidth((short) 3, (short) 3000);
			sheet.setColumnWidth((short) 4, (short) 3500);
			sheet.setColumnWidth((short) 5, (short) 5000);
			sheet.setColumnWidth((short) 6, (short) 5000);
			sheet.setColumnWidth((short) 7, (short) 6000);
			sheet.setColumnWidth((short) 8, (short) 2000);
			sheet.setColumnWidth((short) 9, (short) 9000);
			sheet.setColumnWidth((short) 10, (short) 4000);

			HSSFRow rowNumber = sheet.createRow(0);
			rowNumber.setHeightInPoints(11);
			HSSFCell colTemp = rowNumber.createCell(0);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("SP编号");

			colTemp = rowNumber.createCell(1);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("SP名称");

			colTemp = rowNumber.createCell(2);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("创建日期  ");

			colTemp = rowNumber.createCell(3);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("负责人");

			colTemp = rowNumber.createCell(4);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("负责人电话");

			colTemp = rowNumber.createCell(5);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("SP合同开始时间");

			colTemp = rowNumber.createCell(6);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("SP合同结束时间");

			colTemp = rowNumber.createCell(7);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("客服网址");

			colTemp = rowNumber.createCell(8);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("状态");

			colTemp = rowNumber.createCell(9);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("SP和号百接口信息");

			colTemp = rowNumber.createCell(10);
			colTemp.setCellStyle(styleTitleLeft);
			colTemp.setCellValue("备注");

////		int pageSize = 5000;
//			AjaxListData ajaxPageListData = spInfoService.getSpInfoPage(spInfoModel);
////					adrReportDao.getAdrReportList(
////					degree, receiveTimeStart, receiveTimeEnd, adrName,
////					currentName, 1, pageSize);
////			
//			long pageAll = ajaxPageListData.getPageAll();// 总页面数
			int rowCount = 1;
//			for (int z = 1; z <= pageAll; z++) {
//				ajaxPageListData = spInfoService.getSpInfoPage(spInfoModel); 
//						adrReportDao.getAdrReportList(degree,
//						receiveTimeStart, receiveTimeEnd, adrName, currentName,
//						z, pageSize);
//				List spList = ajaxPageListData.getItems();
//
//				if (spList != null && spList.size() > 0) {
//					int count = spList.size();
//					for (int i = 0; i < count; i++) {
//						Object[] objects = (Object[]) spList.get(i);
			    List spList = spInfoService.getAll();
			    if(null!=spList && spList.size()>0){
			    	for(int i =0;i<spList.size();i++){
			    		Object[] objects = (Object[])spList.get(i);
			    
						HSSFRow rowTemp = sheet.createRow(rowCount);
						rowTemp.setHeightInPoints(11);

						colTemp = rowTemp.createCell(0);
						colTemp.setCellStyle(styleContent);
						colTemp.setCellValue(objects[1].toString());
//						colTemp.setCellValue("1+1");

						colTemp = rowTemp.createCell(1);
						colTemp.setCellStyle(styleContent);
						colTemp.setCellValue(objects[2].toString());
//						colTemp.setCellValue("2+2");

						colTemp = rowTemp.createCell(2);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("3+3");
						colTemp.setCellValue(objects[3].toString());

						colTemp = rowTemp.createCell(3);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("4+4");
						colTemp.setCellValue(objects[4].toString());

						colTemp = rowTemp.createCell(4);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("5+5");
						colTemp.setCellValue(objects[5].toString());

						colTemp = rowTemp.createCell(5);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("6+6");
						colTemp.setCellValue(objects[6].toString());

						colTemp = rowTemp.createCell(6);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("7+7");
						colTemp.setCellValue(objects[7].toString());
						
//						if (endTime != null && !"".equals(endTime)) {
//						colTemp.setCellValue(endTime.split(" ")[0]);
//					} else {
//						colTemp.setCellValue("");
//					}
						colTemp = rowTemp.createCell(7);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("8+8");
						colTemp.setCellValue(objects[8].toString());
						
//						if (suspicionOrTogether != null
//								&& "1".equals(suspicionOrTogether)) {
//							colTemp.setCellValue("怀疑");
//						} else {
//							colTemp.setCellValue("并用");
//						}

						colTemp = rowTemp.createCell(8);
						colTemp.setCellStyle(styleContent);
//						colTemp.setCellValue("9+9");
						colTemp.setCellValue(objects[9].toString());

						colTemp = rowTemp.createCell(9);
						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue("10+10");
						colTemp.setCellValue(objects[10].toString());

						colTemp = rowTemp.createCell(10);
						colTemp.setCellStyle(styleContentWrap);
//						colTemp.setCellValue("11+11");
						colTemp.setCellValue(objects[11].toString());
						
						rowCount = rowCount + 1;
//			    	}
//			    }
//					}
				}
			}

			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			wb.write(bytestream);
			byte imgdata[] = bytestream.toByteArray();
			bytestream.close();
			String downFileName = new String("SP信息导出.xls".getBytes(),"ISO8859-1");
			setContentDisposition("attachment;filename=\"" + downFileName+ "\"");
			return new ByteArrayInputStream(imgdata);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public SpInfoService getSpInfoService() {
		return spInfoService;
	}
	public void setSpInfoService(SpInfoService spInfoService) {
		this.spInfoService = spInfoService;
	}
	
}
