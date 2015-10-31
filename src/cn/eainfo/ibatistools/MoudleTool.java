package cn.eainfo.ibatistools;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MoudleTool {

	/** 
	 * 方法说明：生成ibaits xml配置文件
	 * 作者：胡翔宇
	 * @param moudle
	 * @param xmlPath
	 * @see [类、类#方法、类#成员]
	 */
	public void createXmlFile(String moudle, String xmlPath) {

		try {
			// 获取文件名称
			int startNumber = moudle.lastIndexOf(".") + 1;
			String beanName = moudle.substring(startNumber, moudle.length());
			String beanId = "";
			// 获取类中字段属性
			Field[] fds = Class.forName(moudle).getDeclaredFields();
			List fields = new ArrayList();

			int popLength = fds.length;
			for (int i = 0; i < popLength; i++) {
				BeanPreporty beanPreporty = new BeanPreporty();
				if (i == 0) {
					beanId = fds[i].getName();
					beanPreporty.setKey("key");
				} else {
					beanPreporty.setKey("preporty");
				}
				beanPreporty.setName(fds[i].getName());
				fields.add(beanPreporty);
			}

			Configuration cfg = new Configuration();

			ResourceFile resourceFile = new ResourceFile();
			cfg.setDefaultEncoding("utf-8");
			BufferedReader bufferedReader = resourceFile
					.getFileString("/cn/eainfo/ibatistools/moudle.ftl");
			ReaderTemplateLoader fileLoader = new ReaderTemplateLoader(
					bufferedReader);
			cfg.setTemplateLoader(fileLoader);
			Map root = new HashMap();
			root.put("beanName", beanName);
			root.put("beanId", beanId);
			root.put("moudle", moudle);
			root.put("fields", fields);
			root.put("popLength", popLength - 1);

			String fileName = xmlPath + "/" + beanName + ".xml";
			FileWriter out = new FileWriter(fileName);
			Template template = cfg.getTemplate("");
			template.process(root, out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SecurityException,
			ClassNotFoundException, IOException {
		String[] moduleArray = new String[] { "cn.eainfo.navigation.po.Emergency" };
		MoudleTool tool = new MoudleTool();
		for (int i = 0; i < moduleArray.length; i++) {
			tool.createXmlFile(moduleArray[i], "e:/");
			System.out.println("生成结束！" + moduleArray[i] + "~~");
		}

	}

}
