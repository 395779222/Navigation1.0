package cn.eainfo.common;

import java.text.DecimalFormat;
import java.util.Random;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符串信息处理中心，提供字符串的相关处理方法
 * 
 */
public class StringInfoCenter {

	/**
	 * 产生一个20位ID号，最多20位
	 * 
	 * @return String
	 */
	public static synchronized String createId() {
		String subID = new java.util.Date().getTime() + "";
		int m = (int) (Math.random() * 10000000);
		subID += m + "";
		return subID;
	}

	/**
	 * 获取中文全拼
	 * 
	 * @return String
	 */
	public static String getPingYin(String src) {
		char[] t1;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符函数
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else
					t4 += java.lang.Character.toString(t1[i]);
			}
			System.out.println(t4);
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();

		}
		return t4;
	}

	/**
	 * 获取中文简拼
	 * 
	 * @return String
	 */
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	public static String formatDouble(double value) {
		DecimalFormat df = new DecimalFormat("###,###.###");
		String returnValue = df.format(value);
		String[] spits = returnValue.split("\\.");
		if (spits != null && spits.length > 0) {
			if (spits.length == 2) {
				String first = spits[0];
				String second = spits[1];
				if (second.length() == 1) {
					returnValue = first + "." + second + "0";
				} else if (second.length() == 2) {
					returnValue = first + "." + second;
				} else {
					String par = second.substring(2, 3);
					int parValue = Integer.parseInt(par);
					if (parValue > 4) {
						if ((Integer.parseInt(second.substring(1, 2)) + 1) == 10) {
							returnValue = first
									+ "."
									+ (Integer.parseInt(second.substring(0, 1)) + 1)
									+ "0";
						} else {
							returnValue = first
									+ "."
									+ Integer.parseInt(second.substring(0, 1))
									+ (Integer.parseInt(second.substring(1, 2)) + 1);
						}

					} else {
						String secondTemp = second.substring(0, 2);
						returnValue = first + "." + secondTemp;
					}

				}
			} else {
				returnValue = spits[0] + ".00";
			}
		} else {
			returnValue = "0.00";
		}
		return returnValue;
	}
	public static String createDynamicpwd() {
		String pswdCharPool = "1234567890";
		int pswdLen = 6;
		Random r = new Random();
		StringBuffer pswd = new StringBuffer();
		for (int i = 0; i < pswdLen; ++i) {
			int randomIdx = Math.abs(r.nextInt(pswdCharPool.length()));
			pswd.append(pswdCharPool.charAt(randomIdx));
		}
		return pswd.toString();
	}
	public static void main(String args[]) {
		System.out.print(formatDouble(285032132132D));
	}
}
