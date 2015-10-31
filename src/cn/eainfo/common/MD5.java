package cn.eainfo.common;

import java.security.MessageDigest;

/**
 * 定义系统中密码的md5加密 作者 金贤敏 日期 2010-09-20 版本 1.0
 * 
 */
public class MD5 {
	 
	/**
	 * 将字符串进行md5加密
	 * 
	 * @param s需要进行加密的原字符串
	 * @return String加密后的字符串
	 */
	public final static String doit(String s) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);

			// MessageDigest.getInstance(algorithm)
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];

			// 二进制转十六进制
			int k = 0;

			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}

			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	 
}
