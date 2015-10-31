/**执行文件处理
 * 金贤敏
 * 2010-03-09
 **/
package cn.eainfo.ibatistools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ResourceFile {

	/**
	 * 文件内容的读取
	 * 
	 * @param filePath文件路径
	 * @return BufferedReader返回文件缓冲
	 */
	public BufferedReader getFileString(String filePath) throws IOException {
		URL path = this.getClass().getResource(filePath);
		URLConnection uc = path.openConnection();
		InputStreamReader inputStreamReader = new InputStreamReader(uc
				.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		return bufferedReader;
	}

	/**
	 * 文件复制
	 * 
	 * @param oldPath原文件路径
	 * @return BufferedReader目标文件路径
	 */
	public void copyFile(String oldPath, String newPath) throws IOException {
		URL path = this.getClass().getResource(oldPath);
		URLConnection uc = path.openConnection();
		BufferedInputStream inBuff = new BufferedInputStream(uc
				.getInputStream());

		FileOutputStream out = new FileOutputStream(newPath);
		byte[] bt = new byte[1024];
		int count;
		while ((count = inBuff.read(bt)) > 0) {
			out.write(bt, 0, count);
		}
		inBuff.close();
		out.close();
	}
}
