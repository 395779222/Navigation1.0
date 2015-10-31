package cn.eainfo.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	private Constant() {
	}

	//Session Key
	public static final String S_USER = "SESSION_USER";
	public static final String S_USER_ID = "UserID";
	public static final String S_REAL_NAME = "realname";
	public static final String S_AREA_CODE = "SESSION_AREA_CODE";
	public static final String S_VALI_CODE = "SESSION_VALIDATE_CODE";
	public static final String S_USER_MOBILEPHONE="mobilephone";
	
	//Action类型
	public static final int ACTION_TYPE_NORMAL = 0;
	public static final int ACTION_TYPE_SEARCH = 1;
	public static final int ACTION_TYPE_INSERT = 2;
	public static final int ACTION_TYPE_UPDATE = 3;
	public static final int ACTION_TYPE_DELETE = 4;
	
	//客户品牌分类
	public static final int CUSTOMER_TYPE_PEOPLE = 1;//个人客户
	public static final int CUSTOMER_TYPE_ENTERPRISE = 2;//政企客户
	
	//客户状态
	public static final int CUSTOMER_STATE_NORMAL = 1;//正常
	public static final int CUSTOMER_STATE_PAUSE = 2;//暂停
	public static final int CUSTOMER_STATE_CANCEL = 3;//销户
	
	//客户卡状态
	public static final String CUSTOMER_CARD_NOSURE = "001";//未确认
	public static final String CUSTOMER_CARD_WAIT= "002";//待发卡
	public static final String CUSTOMER_CARD_NOSEND = "003";//暂不发卡
	public static final String CUSTOMER_CARD_SENDING = "004";//寄卡中
	public static final String CUSTOMER_CARD_ISSURES = "005";//已发卡
	public static final String CUSTOMER_CARD_PREMIUM = "006";//待补卡
	public static final String CUSTOMER_CARD_CANCEL = "007";//已销卡
	
	//卡状态
	public static final int CARD_NOACTIVE = 1;//未激活
	public static final int CARD_ACTIVE= 2;//已激活
	public static final int CARD_LOSS = 3;//已挂失
	public static final int CARD_CANCEL = 4;//已销号
	

	//审核状态
	public static final int AUDIATING_STATE_INIT = 0;// 未审核
	public static final int AUDIATING_STATE_SUCCESS = 1;// 审核通过
	public static final int AUDIATING_STATE_FAILER = 2;// 审核不通过
	public static final int AUDIATING_STATE_PAUSE = 8;// 暂停(审核)
	public static final int AUDIATING_STATE_CANCEL = 9;// 退订(审核)
	public static final int AUDIATING_STATE_ERROR = 5; //非网内电话
	
	public static final String INIT_PASSWORD = "123456";//初始密码
	
	public static final String SERVICE_CODE = "UTF-8";//
	
	//积分类型
	public static final int RULE_TYPE_A = 1;//1次积N分
	public static final int RULE_TYPE_B = 2;//M元N分

	public static final int MAX_RESULTS = 15;
	
	//执行结果代码(WebServices)
	public static final String EXECUTE_SUCCESS = "000";//成功
	public static final String EXECUTE_DATA_ERROR = "001";//数据错误(已存在/不存在)
	public static final String EXECUTE_FORMAT_ERROR = "002";//格式错误
	public static final String EXECUTE_STATES_ERROR = "003";//当前状态不能退定
	public static final String EXECUTE_UNKNOW_ERROR = "009";//未知错误
	public static final String EXECUTE_PHONE_ERROR = "008";//非网内电话号码
	
	//开通途径
	public static final String OPEN_FROM_BSS = "1";//BSS受理
	public static final String OPEN_FROM_CRMWEB = "2";//CRM受理
	public static final String OPEN_FROM_SP = "3";//SP受理
	public static final String OPEN_FROM_WANGTING = "4";//网厅受理
	public static final String OPEN_FROM_HB = "9";//号百受理
	public static final String OPEN_FROM_SHENGKEFU = "8";//省客服受理
	public static final String OPEN_FROM_SMS = "7";//短信平台
	public static final String OPEN_FROM_YUYIN = "6";//语音上行
	public static final String OPEN_FROM_FREE = "98";//省号百免费
	public static final String OPEN_FROM_SHENGHAOBAIWAIHU = "5";//省号百外呼
	
	//开通途径INT
	public static final int OPEN_FROM_BSS_INT = 1;//BSS受理
	public static final int OPEN_FROM_CRMWEB_INT = 2;//CRM受理
	public static final int OPEN_FROM_SP_INT = 3;//SP受理
	public static final int OPEN_FROM_WANGTING_INT = 4;//网厅受理
	public static final int OPEN_FROM_HB_INT = 9;//号百受理
	public static final int OPEN_FROM_SHENGKEFU_INT = 8;//省客服受理
	public static final int OPEN_FROM_SMS_INT = 7;//短信平台
	public static final int OPEN_FROM_YUYIN_INT = 6;//语音上行
	public static final int OPEN_FROM_FREE_INT = 98;//省号百免费
	public static final int OPEN_FROM_SHENGHAOBAIWAIHU_INT = 5;//省号百外呼
	//SP类型
	public static final int SP_TYPE_LAW = 1;    //政企法律
	public static final int SP_TYPE_DOCTOR = 2;  //家庭医生
	public static final int SP_TYPE_FINANCE = 3; //理财顾问
	public static final int SP_TYPE_NXT = 4;     
	public static final int SP_TYPE_PERSONLAW = 5; //个人法律
	public static final int SP_TYPE_DOCTORDETAIL=6;  //预约挂号
	public static final int SP_TYPE_TRAFFIC = 10;   //交通伴我行
	public static final int SP_TYPE_UHUILIFE = 11;  //惠生活
	public static final int SP_TYPE_NAVIGATION = 12;  //天翼导航
	public static final int SP_TYPE_Cinemalink = 13; //院线通
	public static final int SP_TYPE_ComputerNanny = 14; //电脑保姆
	public static final int SP_TYPE_YIUP = 15; //翼起来网站
	public static final int SP_TYPE_HANGMESSAGE = 16; //BSS挂机短信
	public static final int SP_TYPE_ELOTTERY = 17; //电子彩票
	public static final int SP_TYPE_DAYLIFEASSISTANT = 18; //天天生活助理
	public static final int SP_TYPE_EHAPPYLISTEN = 19; //英语快乐听
	public static final int SP_TYPE_YIXIAOTONG = 20; //翼校通
	public static final int SP_TYPE_JOBINFO = 21; //人才招聘
	public static final int SP_TYPE_YZLIFEINFO = 22; //扬州生活资讯
	
	public static int getSPType(String bussName){
		Map map = new HashMap();
		map.put("Bus0003", SP_TYPE_LAW);
		map.put("Bus0005", SP_TYPE_DOCTOR);
		map.put("Bus0004", SP_TYPE_FINANCE);
		map.put("Bus0006", SP_TYPE_PERSONLAW);
		map.put("Bus0019", SP_TYPE_Cinemalink);
		map.put("Bus0024", SP_TYPE_JOBINFO);
		return (Integer) map.get(bussName);
	}
	public static String getSPName(String busName){
		Map map = new HashMap();
		map.put("Bus0003", "政企法律");
		map.put("Bus0005", "健康顾问");
		map.put("Bus0004", "理财顾问");
		map.put("Bus0006", "个人法律");
		map.put("Bus0019", "院线通");
		map.put("Bus0024", "人才顾问");
		return (String) map.get(busName);
	}
	public static String getNameByBus(String bussName){
		Map map = new HashMap();
		map.put("Bus0004", "finance");
		map.put("Bus0005", "doctor");
		map.put("Bus0019", "cinema");
		map.put("Bus0024", "jobinfo");
		return (String) map.get(bussName);
	}
	
	//SP动作
	public static final int SP_ACTION_CREATE = 1; //开户;
	public static final int SP_ACTION_CHECK = 2; //审核;
	public static final int SP_ACTION_MODIFY = 3; //修改资料;
	public static final int SP_ACTION_CANCEL = 4; //退订;
	public static final int SP_ACTION_RECREATE = 5; //复机;
	public static final int SP_ACTION_Synchronization = 6; //同步数据给SP;
	//天翼导航动作
	public static final int SP_ACTION_PAUSE42 = 42; //欠费单停;
	public static final int SP_ACTION_PAUSE43 = 43; //欠费双停;
	public static final int SP_ACTION_RECREATE44 = 44; //单停复机;
	public static final int SP_ACTION_RECREATE45 = 45; //双停复机;
	public static final int SP_ACTION_MODIFY14 = 14; //补机补卡;
	
	//第二次批量BSS同步,根据返回结果写入日志表actionType字段,便于统计
	public static final int BSS_SUCCESS = 100; //同步成功;
	public static final int BSS_UNSUCCESS_101 = 101; //同步失败;
	public static final int BSS_UNSUCCESS_102 = 102; //号码状态异常：比如停机,挂失,报停,欠费等;
	public static final int BSS_UNSUCCESS_103 = 103; //号码不存在或者已拆机;
	
	//业务类型
	public static final Long BUSINESS_ROOM=new Long(1001); //订房	
	public static final Long BUSINESS_EAT=new Long(1002);//订餐	
	public static final Long BUSINESS_TICKET=new Long(1003);//订票
	public static final Long BUSINESS_GOOD=new Long(1004);	//订商品	
	public static final Long BUSINESS_FLOOR=new Long(1005);//订花
	public static final Long BUSINESS_SERVICE=new Long(1006);//订服务
	
	//套餐类型（包月包年免费）业务表中的MONTHTYPE字段区分
	public static final int SP_MONTH = 0; //包月;
	public static final int SP_YEAR = 1; //包年;
	public static final int SP_FREE = 3; //免费;
	
	//电子彩票玩法分类
	public static final String ELOTTERY_TYPE_SHUNGSEQIU = "1";//双色球
	public static final String ELOTTERY_TYPE_3D = "2";//3D
	public static final String ELOTTERY_TYPE_15XUAN5 = "3";//15选5
	public static final String ELOTTERY_TYPE_QILECAI = "4";//七乐彩
	public static final String ELOTTERY_TYPE_DONGFANG6JIA1 = "5";//东风6+1
	public static final String ELOTTERY_QUXIAO= "QX";//取消所有的电子彩票
	
	public static String getOpenWay(String openFrom){
		Map map = new HashMap();
		map.put("1", "5");
		map.put("3", "3");
		map.put("7", "4");
		map.put("8", "2");
		map.put("9", "1");
		map.put("6", "6");
		return (String)map.get(openFrom);
	}
	public static String getAreacodePhone(String phone,String areaNO){
		String result=phone;
		if(phone!=null&&!"".equals(phone)){
			if(phone.length()==8){
				result=getAreacode(areaNO)+phone;
			}
		}
		return result;
	}
	
	public static String getAreacode(String areaNO){
		Map map = new HashMap();
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
		return (String) map.get(areaNO);
	}

	public static String getType(String bussName){
		Map map = new HashMap();
		map.put("Bus0003", "1");
		map.put("Bus0005", "2");
		map.put("Bus0004", "3");
		map.put("Bus0006", "5");
		map.put("Bus0019", "13");
		map.put("Bus0014", "18");
		map.put("Bus0024", "21");
		return (String) map.get(bussName);
	}
	//VSOP套餐状态
	public static final int DINGGOUTC = 0;// 订购套餐
	public static final int JIARUTC = 1;// 加入套餐
	public static final int TUICHUTC = 2;// 退出套餐
	public static final int TUIDINGTC = 3;// 退订套餐

}
