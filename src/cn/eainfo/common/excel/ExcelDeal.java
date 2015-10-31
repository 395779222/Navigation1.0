
package cn.eainfo.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import cn.eainfo.common.excel.CommonDateUtils;



/**
 * <excel生成与解析>
 * @author 胡翔宇
 * @version  [版本号, 2014-4-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ExcelDeal {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	/**
	 * 记录excel表的字段位置和字段名称
	 */
	public Map<Integer,String> filedIndexNameMap = new HashMap<Integer,String>();
	
	/**
	 * 记录excel表字段名称，和字段的取值
	 */
	public Map<String, Object> filedNameValueMap = new HashMap<String, Object>();
	
	/**
	 * 默认构造函数
	 */
	public ExcelDeal(){
		
	}
	
	/**
	 * 构造方法传入execel中每一列的字段名称
	 */
	public ExcelDeal(String[] names){
		for(int i = 0; i < names.length; i++){
			this.filedIndexNameMap.put(i, names[i]);
		}
	}
	/**
	 * 开始读取对象所在第几行,默认为0
	 */
	public int startIndex = 0;
	
	
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	

	public void setIndexName(int index, String name){
		this.filedIndexNameMap.put(index, name);
	}
	

	public void setIndexName(String[] names){
		for(int i = 0; i < names.length; i++){
			this.filedIndexNameMap.put(i, names[i]);
		}
	}
	

	public void setExcelField(int index, String name, Object value){
		this.filedIndexNameMap.put(index, name);
		this.filedNameValueMap.put(name, value);
	}
	

	public List<Map<String, Object>> parseExcelToList(File file) throws Exception{
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			
			logger.log(Level.INFO, " parseExcelToList() BEGIN");
			
			if(null == file || !file.exists() || !file.isFile()) {
				throw new FileNotFoundException("文件不存在") ;
			}
			String filePath = file.getAbsolutePath().trim().toLowerCase();
			if(filePath.endsWith("xls")) {
				list = read2003Excel(file);
			} else if(filePath.endsWith("xlsx")) {
				list = read2007Excel(file);
			} else {
				throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!") ;
			}
			
			logger.log(Level.INFO, " parseExcelToList() END");
			
		} catch (Exception e) {
			throw e;
		} catch (Throwable e) {
			
			logger.log(Level.ERROR, " parseExcelToList() 解析excel文件，并返回一个list ",e);
			
		}
		return list;
	}

	private List<Map<String, Object>> read2003Excel(File file) throws Exception{
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			
			logger.log(Level.INFO, " read2003Excel() BEGIN");
			
			HSSFWorkbook work = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = work.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 0; r < rows; r++) {
				filedNameValueMap = new HashMap<String, Object>();
				if (startIndex > 0 && r < startIndex) {
					continue;
				}
				Cell cell = null;
				Row row = sheet.getRow(r);

				for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
					cell = row.getCell(c);

					// 拿到字段名称
					String name = filedIndexNameMap.get(c);
					String value = (String)getValueFromCell(cell);
					// 设值到名称值的map中
					filedNameValueMap.put(name, value);
				}
				list.add(filedNameValueMap);
			}
			
			logger.log(Level.INFO, " read2003Excel() END");
			
		}catch (Exception e) {
			
            throw e;
            
        }catch (Throwable e) {
        	
        	logger.log(Level.ERROR, " read2003Excel() 解析2003的excel文件 ",e);
        	
		}
		return list;
	}
	

	private List<Map<String, Object>> read2007Excel(File file) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			
			logger.log(Level.INFO, " read2007Excel() BEGIN");
			
			// Workbook workbok = new XSSFWorkbook(new FileInputStream(file));
			Workbook workbok = createWorkbook(new FileInputStream(file));
			
			// 拿到第一个工作簿
			Sheet sheet = workbok.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			
			for(int r = 0; r < rows; r ++){
				filedNameValueMap = new HashMap<String, Object>();
				if(startIndex > 0 && r < startIndex){
					continue ;
				}
				Cell cell = null;
				Row row = sheet.getRow(r) ;
			
				for(int c = row.getFirstCellNum() ; c < row.getLastCellNum() ; c++) {
					cell = row.getCell(c) ;
					
					//拿到字段名称
					String name = filedIndexNameMap.get(c);
					
					//设值到名称值的map中
					filedNameValueMap.put(name, (String)getValueFromCell(cell));
				}
				        	
				list.add(filedNameValueMap);
			}
			
			logger.log(Level.INFO, " read2007Excel() END");
			
		} catch (Exception e) {
			
			throw e;
			
		}catch (Throwable e) {
        	
        	logger.log(Level.ERROR, " read2007Excel() 解析2007的excel文件 ",e);
        	
		}
		
		return list;
		
	}

	private final String getValueFromCell(Cell cell) {
		if(cell == null) {
			return null;
		}
		String value = null ;
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC :	// 数字
				if(HSSFDateUtil.isCellDateFormatted(cell)) {		// 如果是日期类型
					value = CommonDateUtils.dateToString(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
				} else 	{
					value = removeDotZero(cell.getNumericCellValue()) ;
				}
				break ;
			case Cell.CELL_TYPE_STRING:		// 字符串
				value = cell.getStringCellValue() ;
				break ;
			case Cell.CELL_TYPE_FORMULA:	// 公式
				// 用数字方式获取公式结果，根据值判断是否为日期类型
				double numericValue = cell.getNumericCellValue() ;
				if(HSSFDateUtil.isValidExcelDate(numericValue)) {	// 如果是日期类型
					value = CommonDateUtils.dateToString(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
				} else 	{
					value = removeDotZero(numericValue) ;
				}
				break ;
			case Cell.CELL_TYPE_BLANK:				// 空白
				value = StringUtils.EMPTY ;
				break ;
			case Cell.CELL_TYPE_BOOLEAN:			// Boolean
				value = String.valueOf(cell.getBooleanCellValue()) ;
				break ;
			case Cell.CELL_TYPE_ERROR:				// Error，返回错误码
				value = String.valueOf(cell.getErrorCellValue()) ;
				break ;
			default:value = StringUtils.EMPTY ;break ;
		}
		// 使用[]记录坐标
		return value;
	}

	public static Workbook createWorkbook(InputStream inp) throws IOException,
			InvalidFormatException {
		if (!inp.markSupported()) {
			inp = new PushbackInputStream(inp, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inp)) {
			return new HSSFWorkbook(inp);
		}
		if (POIXMLDocument.hasOOXMLHeader(inp)) {
			return new XSSFWorkbook(OPCPackage.open(inp));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}
	
	/**
	 * 方法说明:
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-14
	 * @param numString
	 * @return
	 */
	public static String removeDotZero(double numString){
		String dbStr = "" + numString;
		while(dbStr.charAt(dbStr.length() - 1) == '0'){
			dbStr = dbStr.substring(0, dbStr.length() - 1);
		}
		if(dbStr.charAt(dbStr.length() -1 ) == '.'){
			dbStr = dbStr.substring(0, dbStr.length() - 1);
		}
		return dbStr;
	}
	
	public HSSFWorkbook generateExcel(String strCHArray[], List list,String strENArray[]){
		 //第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("导航信息列表");
		
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);//设置自动换行
		HSSFCell cell = null;
		//设置excel标题
		for(int k=0;k<strCHArray.length;k++){
			cell = row.createCell((short) k);
			cell.setCellValue(strCHArray[k]);
			cell.setCellStyle(style);
		}
		//便利list
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				row = sheet.createRow((int) i + 1);
				//便利每一个实体类
				Map map = (Map) list.get(i);
				
				for(int j=0;j<strENArray.length;j++){
					
					row.createCell((short) j).setCellStyle(style);
					row.createCell((short) j).setCellValue(String.valueOf(map.get(strENArray[j])));
				}
			}	
		}
		return wb;
	}
	
}
