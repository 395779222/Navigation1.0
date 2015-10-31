package cn.eainfo.common;

import java.util.HashMap;
import java.util.Map;

public class BusinessExpandConfig {

	/**
	 * 获得地区编码
	 */
	public static String getCity(String cityId) {
		Map map = new HashMap();
		// 南京
		map.put("9", "0001");
		map.put("10", "0001");
		map.put("11", "0001");
		map.put("12", "0001");
		map.put("13", "0001");
		map.put("14", "0001");
		// 镇江
		map.put("5", "0005");
		map.put("6", "0005");
		map.put("7", "0005");
		map.put("8", "0005");

		// 无锡市
		map.put("16", "0003");
		map.put("18", "0003");
		map.put("19", "0003");

		// 苏州市
		map.put("21", "0002");
		map.put("23", "0002");
		map.put("74", "0002");
		map.put("75", "0002");
		map.put("76", "0002");
		map.put("77", "0002");
		

		// 南通市
		map.put("24", "0007");
		map.put("25", "0007");
		map.put("27", "0007");
		map.put("28", "0007");
		map.put("29", "0007");
		map.put("30", "0007");
		map.put("31", "0007");
		

		// 扬州市
		map.put("22", "0006");
		map.put("32", "0006");
		map.put("34", "0006");
		map.put("35", "0006");
		map.put("36", "0006");
		map.put("37", "0006");
		map.put("38", "0006");
	

		// 盐城市
		map.put("17", "0011");
		map.put("40", "0011");
		map.put("41", "0011");
		map.put("42", "0011");
		map.put("43", "0011");
		map.put("44", "0011");
		map.put("45", "0011");
		map.put("46", "0011");
		map.put("47", "0011");

		// 徐州市
		map.put("49", "0009");
		map.put("50", "0009");
		map.put("51", "0009");
		map.put("52", "0009");
		map.put("53", "0009");
		map.put("54", "0009");
		map.put("55", "0009");
		map.put("90", "0009");

		// 淮安市
		map.put("56", "0010");
		map.put("57", "0010");
		map.put("58", "0010");
		map.put("59", "0010");
		map.put("61", "0010");
		map.put("62", "0010");
		map.put("89", "0010");

		// 连云港
		map.put("64", "0012");
		map.put("65", "0012");
		map.put("66", "0012");
		map.put("67", "0012");
		map.put("68", "0012");

		// 常州市
		map.put("70", "0004");
		map.put("72", "0004");
		map.put("73", "0004");
		

		// 泰州市
		map.put("80", "0008");
		map.put("81", "0008");
		map.put("82", "0008");
		map.put("83", "0008");
		map.put("78", "0008");

		// 宿迁市
		map.put("71", "0013");
		map.put("85", "0013");
		map.put("86", "0013");
		map.put("87", "0013");
		map.put("88", "0013");

		map.put("3", "0001");
		map.put("20", "0002");
		map.put("15", "0003");
		map.put("26", "0007");
		map.put("33", "0006");
		map.put("39", "0011");
		map.put("48", "0009");
		map.put("60", "0010");
		map.put("63", "0012");
		map.put("69", "0004");
		map.put("79", "0008");
		map.put("84", "0013");
		map.put("4", "0005");
		
		return (String) map.get(cityId);
	}
	public static String getAreaNO(String cityId) {
		Map map = new HashMap();
		map.put("025", "0001");
		map.put("0512", "0002");
		map.put("0510", "0003");
		map.put("0513", "0007");
		map.put("0514", "0006");
		map.put("0515", "0011");
		map.put("0516", "0009");
		map.put("0517", "0010");
		map.put("0518", "0012");
		map.put("0519", "0004");
		map.put("0523", "0008");
		map.put("0527", "0013");
		map.put("0511", "0005");
		return (String) map.get(cityId);
	}
	
	/**
	 * 获得属性编码
	 */
	public static String getPid(String pid) {
		Map map = new HashMap();
		map.put("10086", "身份证号码");
		map.put("10087", "驾驶执照");
		map.put("10088", "军人证");
		map.put("10089", "业务电话");
		map.put("10090", "家庭地址");
		map.put("10091", "公司地址");
		map.put("10092", "邮编");
		map.put("10093", "邮箱地址");
		map.put("10094", "性别");
		map.put("10095", "生日");	
		return (String) map.get(pid);
	}

	/**
	 * 获得属性状态
	 */
	public static String getActionType(String actionType) {
		Map map = new HashMap();
		map.put("01", "开通");
		map.put("02", "取消");
		map.put("03", "修改");

		return (String) map.get(actionType);
	}

	/**
	 * 获得相应业务
	 */
	public static String getBusCode(String busCode) {
		Map map = new HashMap();
		map.put("Bus0001", "移动号百");
		map.put("Bus0002", "号百招聘个人短信");
		map.put("Bus0003", "政企法律");
		map.put("Bus0004", "理财顾问");
		map.put("Bus0005", "家庭医生");
		map.put("Bus0006", "个人法律");		
		map.put("Bus0007", "生活百事通");
		map.put("Bus0008", "交通伴我行");
		map.put("Bus0009", "天翼导航");
		map.put("Bus0018", "U惠生活");
		map.put("Bus0019", "院线通");
		map.put("Bus0020", "电脑保姆");
		map.put("Bus0021", "翼起来");
		map.put("Bus0011", "BSS挂机短信");
		map.put("Bus0012", "BSS挂机短信");
		map.put("Bus0013", "BSS挂机短信");
		map.put("Bus0010", "农信百事通");
		map.put("Bus0022", "电子彩票");
		map.put("Bus0014", "天天生活助理");
		map.put("Bus0015", "英语快乐听");
		map.put("Bus0023", "翼校通");
		map.put("Bus0014-1", "VSOP天天生活助理");
		map.put("Bus0011-1", "VSOP挂机短信");
		map.put("Bus0012-1", "VSOP挂机短信");
		map.put("Bus0013-1", "VSOP挂机短信");
		map.put("Bus0009-1", "VSOP天翼导航");
		map.put("Bus0024", "号百人才招聘");
		return (String) map.get(busCode);
	}
	
	/**
	 * 获得个人法律 政企法律服务ID
	 */
	public static String getServiceCode(String serviceId) {
		Map map = new HashMap();
		map.put("1155", "1");
		map.put("1156", "2");
		map.put("1157", "3");
		map.put("1158", "2");
		map.put("1159", "3");
		map.put("1160", "1");		
		return (String) map.get(serviceId);
	}


	/**
	 * 获得业务变更
	 */
	public static String getBusTypeId(String busTypeId) {
		Map map = new HashMap();
		map.put("1", "开户");
		map.put("3", "销户");
		map.put("7", "业务变更");
		map.put("8", "业务验证");
		map.put("42", "欠费单停");
		map.put("43", "欠费双停");
		map.put("44", "单停复机");
		map.put("45", "双停复机");
		map.put("14", "补机补卡");
		map.put("21", "移机");
		return (String) map.get(busTypeId);
	}

	/**
	 * 获得号码类型
	 */
	public static String getMsisdnType(String msisdnType) {
		Map map = new HashMap();
		map.put("01", "Msisdn");
		map.put("02", "PHS");
		map.put("03", "固定电话");
		map.put("04", "号百产品");
		return (String) map.get(msisdnType);
	}

	/**
	 * 获得VSOP号码类型
	 */
	public static String getVsopMsisdnType(String msisdnType) {
		Map map = new HashMap();
		map.put("0", "Msisdn");
		map.put("1", "PHS");
		map.put("2", "PSTN");
		map.put("3", "宽带帐号");
		map.put("4", "IPTV帐号");
		map.put("5", "VNET帐号");
		
		return (String) map.get(msisdnType);
	}

	/**
	 * 获得VSOP订购动作类型
	 */
	public static String getVsopOperType(String operType) {
		Map map = new HashMap();
		map.put("0", "订购");
		map.put("1", "加入套餐");
		map.put("2", "替换已有产品");
		map.put("3", "退订");
		map.put("4", "退出套餐");
		return (String) map.get(operType);
	}
	
	/**
	 * 获得VSOP增值销售品类型
	 */
	public static String getVsopProductOfferType(String productOfferType) {
		Map map = new HashMap();
		map.put("0", "纯增值销售品");
		map.put("1", "增值捆绑套餐");
		map.put("2", "（传统+增值）捆绑套餐");
		return (String) map.get(productOfferType);
	}
	/**
	 * 获得VSOP套餐销售品(ProductOfferType)跟号百的资费(billTypeId)的对应关系
	 * 由于VSOP那边的套餐的销售品ID可能是300500003681这种形式的.
	 * 所以要转换为跟号百跟VSOP那边统一的销售品样式即：号百的业务编码-资费类型（Bus0003-1）
	 */
	public static String getBillTypeIdByVsop(String productOfferTypeId) {
		Map map = new HashMap();
		

		
		map.put("300006001154", "Bus0014-1");
		map.put("300500004556", "Bus0014-1");
		map.put("300500003678", "Bus0014-1");
		map.put("300500003677", "Bus0014-1");
		map.put("300500003674", "Bus0014-1");
		map.put("300509003238", "Bus0014-1");
		map.put("300169001192", "Bus0014-1");
		
		map.put("300006001061", "Bus0009-1");
		map.put("300509004176", "Bus0009-1");
		map.put("300509004175", "Bus0009-1");
		map.put("300509004177", "Bus0009-1");
		map.put("300509004178", "Bus0009-1");
		
		map.put("300509008294", "Bus0019-1");
		map.put("300509009676", "Bus0019-1"); 
		map.put("300509005563", "Bus0019-1"); 
	
		return (String) map.get(productOfferTypeId);
	}
	
	
	/**
	 * 获得VSOP用户状态同步操作类型
	 */
	public static String getVsopUserInfoActionType(String actionType) {
		Map map = new HashMap();
		map.put("10", "新增");
		map.put("11", "用户状态变更");
		map.put("12", "改号");
		map.put("15", "拆机");
		map.put("16", "用户付费类型变更");
		map.put("17", "换卡");
		return (String) map.get(actionType);
	}
	/**
	 * 获得付费方式
	 */
	public static String getFeeType(String feeType) {
		Map map = new HashMap();
		map.put("1", "预付费用户");
		map.put("2", "后付费用户");

		return (String) map.get(feeType);
	}

	/**
	 * 是否发短信通知
	 */
	public static String getSendNotification(String sendNotification) {
		Map map = new HashMap();
		map.put("0", "不发");
		map.put("1", "发");

		return (String) map.get(sendNotification);
	}
	
	/**
	 * 获得WEBSERVICE SP接口中的日志开通途径
	 */
	public static String getOpenFrom(String openFrom) {
		Map map = new HashMap();
		map.put("0", Constant.OPEN_FROM_SP);
		map.put("1", Constant.OPEN_FROM_SMS);
		map.put("2", Constant.OPEN_FROM_SHENGKEFU);
		map.put("3", Constant.OPEN_FROM_YUYIN);
		map.put("4", Constant.OPEN_FROM_WANGTING);
		map.put("5", Constant.OPEN_FROM_BSS);
		return (String) map.get(openFrom);
	}
	
	public static String getElotteryType(String serviceCode) {
		Map map = new HashMap();
		map.put("DEFAULT", "默认");		
		map.put("QX", "取消全部");
		return (String) map.get(serviceCode);
	}

	public static String getQueryCity(String cityId) {
		Map map = new HashMap();
		map.put("00", "3");
		map.put("0001", "3");
		map.put("0002", "20");
		map.put("0003", "15");
		map.put("0004", "69");
		map.put("0005", "4");
		map.put("0006", "33");
		map.put("0007", "26");
		map.put("0008", "79");
		map.put("0009", "48");
		map.put("0010", "60");
		map.put("0011", "39");
		map.put("0012", "63");
		map.put("0013", "84");
		return (String) map.get(cityId);
	}
	
	public static String getCityId(String cityId) {
		Map map = new HashMap();
		map.put("00", "025");
		map.put("0001", "025");
		map.put("0002", "0512");
		map.put("0003", "0510");
		map.put("0004", "0519");
		map.put("0005", "0511");
		map.put("0006", "0514");
		map.put("0007", "0513");
		map.put("0008", "0523");
		map.put("0009", "0516");
		map.put("0010", "0517");
		map.put("0011", "0515");
		map.put("0012", "0518");
		map.put("0013", "0527");
		return (String) map.get(cityId);
	}
	
	public static String getUserIDType(String phone) {
		String result = "1";
		if (phone != null && !phone.trim().equals("")&& phone.length() == 12) {
			if (phone.substring(0, 2).equals("05")) {				
				result = "3";
			} else {
				result = "1";
			}
		} else if (phone != null && !phone.trim().equals("") && phone.length() == 11) {
			if (phone.substring(0, 3).equals("025")) {
				result = "3";
			} else if (phone.substring(0, 3).equals("133")
					|| phone.substring(0, 3).equals("153")
					|| phone.substring(0, 3).equals("189")
					|| phone.substring(0, 3).equals("180")
					|| phone.substring(0, 3).equals("181")) {
				result = "1";
			} else {
				result = "1";
			}
		} else {
			result = "1";
		}
		return result;
	}
	//判断是否为电信号码
	public static String checkDianxinPhone(String phone){
		String result = "";
		if (phone != null && !phone.trim().equals("")&& phone.length() == 12) {
			if (phone.substring(0, 3).equals("051")||phone.substring(0, 3).equals("052")) {				
				result = "Y";
			} else {
				result = "N";
			}
		} else if (phone != null && !phone.trim().equals("") && phone.length() == 11) {
			if (phone.substring(0, 3).equals("025")) {
				result = "Y";
			} else if (phone.substring(0, 3).equals("133")
					|| phone.substring(0, 3).equals("153")
					|| phone.substring(0, 3).equals("189")
					|| phone.substring(0, 3).equals("180")
					|| phone.substring(0, 3).equals("181")
					|| phone.substring(0, 4).equals("1700")) {
				result = "Y";
			} else {
				result = "N";
			}
		} else {
			result = "N";
		}
		return result;
	}
}
