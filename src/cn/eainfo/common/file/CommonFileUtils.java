/**
 * 
 */
package cn.eainfo.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * <p>文件名称: commonfileUtils.java </p>
 * <p>文件描述: 此工具类对文件的常用操作：删除，赋值，比对，读写等</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-4-17</p>
 * <p>修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author huxiangyu
 */
public class CommonFileUtils {
	
	//日志对象，用于记录日志
    private  Logger logger = Logger.getLogger(this.getClass().getName());
    
    // 各种文件类型
	public static int ICOMPRESSFILETYPE = 0;
	public static int IPARSEFILETYPE = 1;
	public static int IUNKNOWFILETYPE = -1;
	
	
	public static boolean delFile(String targetPath){
         boolean flag=false;
         try {
        	 
            File targetFile=new File(targetPath);
     		if (targetFile.isDirectory()) { 
			     FileUtils.deleteDirectory(targetFile);
			     flag=true;
     		 } else if (targetFile.isFile()) {
                 targetFile.delete();
                 flag=true;
            }		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//logger.log(Level.ERROR, "删除文件出错"+e);
		}
      
	   return flag;
	}
	

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				// 先删除文件夹里面的文件
				delAllFile(path + "/" + tempList[i]);
				//再删除空文件夹
				// delFolder(path + "/" + tempList[i]);
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * 方法说明:复制文件到另一个目录
	 * 创建人:huxiangyu
	 * 创建时间:2014-4-7
	 * @param resFilePath
	 * @param destFilePath
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(String resFilePath,String  destFilePath){
		boolean flag=false;
		try {
			File resFile=new File(resFilePath);
			File destFile=new File(destFilePath);
			FileUtils.copyFile(resFile, destFile);
			flag=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 方法说明: 拷贝文件到文件夹下
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param file
	 * @param desDir
	 * @return
	 */
	public static boolean copyFileToDerecotry(File file, File destDir){
		try {
			FileUtils.copyFileToDirectory(file, destDir);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 方法说明: 移动到文件夹下,顺带改名称为toFileName
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param src
	 * @param desDir
	 * @param toAllFileName -- 全名称,包括后缀
	 * @return
	 */
	public static File removeTo(File src, File desDir, String toAllFileName){
		if(! desDir.exists()){
			desDir.mkdirs();
		}
		File temp = new File(desDir + File.separator + toAllFileName);
		if(temp.exists()){
			return src;
		}
		
		// renameTo
		if(! src.renameTo(temp)){
			return src;
		}
		return temp;
	}
	
	/**
	 * 方法说明: 移动到文件夹下,顺带改名称为toFileName
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param src
	 * @param desDir
	 * @param toAllFileName -- 全名称,包括后缀
	 * @return
	 */
	public static void removeTo(File src, File desDir) throws Exception{
		FileUtils.copyFileToDirectory(src, desDir);
		delFile(src.getAbsolutePath());
	}
	
	/**
	 * 方法说明: 在目录dir下搜索文件名称为fileName的文件
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static List<File> findFile(File dir, String fileName){
		List<File> list = new ArrayList<File>();
		for(File _file : dir.listFiles()){
			if(_file.isDirectory()){
				list.addAll(findFile(_file, fileName));
			} else {
				if(_file.getName().equalsIgnoreCase(fileName)){
					list.add(_file);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 
	 * 方法说明:判断两个文件的内容是否一致
	 * 创建人:huxiangyu
	 * 创建时间:2014-4-17
	 * @param resFilePath
	 * @param destFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean compareFile(String resFilePath,String destFilePath) {
		boolean flag=false;
		
		try {
			File resFile=new File(resFilePath);
			File destFile=new File(destFilePath);
			flag=FileUtils.contentEquals(resFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.ERROR, "判断两个文件内容是否一致出错"+e);
		}
		return flag;
	}
	
	/**
	 * 
	 * 方法说明:将文件读取成字符串
	 * 创建人:huxiangyu
	 * 创建时间:2014-4-17
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
    public String readFileToString(String filePath){
    	String stringFile=null;
    	try {
    		
    		File myfile=new File(filePath);
    	    stringFile=FileUtils.readFileToString(myfile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.ERROR, "将文件读取成字符串出错"+e);
		}
    	return stringFile;
    }
    
    /**
     * 
     * 方法说明:将文件读取为字符串集
     * 创建人:huxiangyu
     * 创建时间:2014-4-17
     * @param filePath
     * @return
     * @throws IOException
     */
    public List<String> readFileToStringList(String filePath){
    	List<String> stringFileList=null;
    	try {
    		File myfile=new File(filePath);
			stringFileList=FileUtils.readLines(myfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.ERROR, "将文件读取成字符串集合出错"+e);
		}
    	return stringFileList;
    } 
    
    /**
     * 方法说明: 从字节数组中读取字节到文件中
     * 创建人: huxiangyu
     * 创建时间:2014-4-17
     * @param byteArr
     * @param fileName
     * @return
     */
    public static boolean writeByteArrToFile(byte[] byteArr, File file){
    	// 默认正常写入文件
    	boolean flag = true;
    	FileOutputStream out = null;
    	BufferedOutputStream bout = null;
    	try {
    		out = new FileOutputStream(file);
    		bout = new BufferedOutputStream(out);
    		bout.write(byteArr);
    		bout.flush();
		} catch (Exception e) {
			flag = false;
		} finally {
			try {
				out.close();
				bout.close();
			} catch (Exception e2) {
				out = null;
				bout = null;
			}
		}
    	return flag;
    }
    
    /**
	 * 方法说明:列出指定文件指定后缀名的所有文件
	 * 创建人:huxiangyu
	 * 创建时间:2014-4-17
	 * @param directory 文件夹
	 * @param suffix 要查找文件的后缀
	 * @return
	 * @throws Exception
	 */
	public static List<File> getSuffixedFiles(File directory, String suffix) throws Exception{
		// 查找到的后缀文件
		List<File> rFiles = new ArrayList<File>();
		if(! directory.exists() || ! directory.isDirectory()){
			// 不是文件夹，
			return rFiles;
		} 
		File[] files = directory.listFiles();
		// 循环迭代出该目录下的文件
		for(File _thisFile : files){
			if(_thisFile.isDirectory()){
				// 如果是目录文件
				List<File> tempList = getSuffixedFiles(_thisFile, suffix);
				rFiles.addAll(tempList);
				
			} else {
				// 查询文件后缀
				if(_thisFile.getAbsolutePath().toLowerCase().endsWith(suffix.toLowerCase())){
					rFiles.add(_thisFile);
				}
			}
		}
		return rFiles;
	}
	
	/**
	 * 方法说明:获取文件夹下文件
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param dir 文件夹
	 * @return
	 * @throws Exception
	 */
	public static List<File> getFilesOfDir(File dir){
		if(null == dir || ! dir.exists() || ! dir.isDirectory()){
			return null;
		}
		
		// 要返回给调用者的文件列表
		List<File> list = new ArrayList<File>();
		for(File file : dir.listFiles()){
			if(file.isDirectory()){
				list.addAll(getFilesOfDir(file));
			} else {
				list.add(file);
			}
		}
		return list;
	}
	
	/**
	 * 方法说明: 从文件中读取出字节数组
	 * 创建人: 胡翔宇
	 * 创建时间:2014-4-17
	 * @param file
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static byte[] readFileToByteArray(File file, int begin, int length) throws Exception{
		FileInputStream fis = null; 
		byte[] returnByte = new byte[length];
		try {
			// 读取文件
			fis = new FileInputStream(file);
			fis.skip(begin);
			// 读取文件内容到相应的字节数组中
			fis.read(returnByte, 0, length);
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				fis.close();
				fis = null;
			} catch (Exception e2) {
				fis = null;
			}
		}
		return returnByte;
	}
	
	/**
	 * 方法说明: 创建文件,force如果为true表示如果不存在上级目录,则创建
	 * 创建人: 胡翔宇
	 * 创建时间:2014-4-17
	 * @param file
	 * @param force
	 * @return
	 */
	public static boolean createFile(File file, boolean force){
		if(null == file){
			return false;
		}
		// 如果文件不存在
		if(! file.exists()){
			if(! file.getParentFile().exists()){
				if(! force){
					return false;
				}
				file.getParentFile().mkdirs();
			}
			try {
				file.createNewFile();
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 方法说明: 创建文件,force如果为true表示如果不存在上级目录,则创建
	 * 创建人: 胡翔宇
	 * 创建时间:2014-4-17
	 * @param _thisCompressFile
	 * @param force
	 * @return
	 */
	public static boolean createFile(String filePath, boolean force){
		File file = new File(filePath);
		return createFile(file, force);
	}
	
	
	
	/**
	 * 方法说明: 创建文件夹 ,force如果为true表示如果不存在上级目录,则创建
	 * 创建人: 胡翔宇
	 * 创建时间:2013-4-17
	 * @param _thisCompressFile
	 * @param force
	 * @return
	 */
	public static boolean createDirectory(File dir, boolean force){
		if(null == dir){
			return false;
		}
		// 如果文件不存在
		if(! dir.exists()){
			if(! dir.getParentFile().exists()){
				if(! force){
					return false;
				}
			}
			dir.mkdirs();
		}
		return true;
	}
	
	/**
	 * 方法说明: 创建文件夹 ,force如果为true表示如果不存在上级目录,则创建
	 * 创建人: 胡翔宇
	 * 创建时间:2014-4-17
	 * @param _thisCompressFile
	 * @param force
	 * @return
	 */
	public static boolean createDirectory(String dirPath, boolean force){
		File dir = new File(dirPath);
		return createDirectory(dir, force);
	}

	/**
	 * 方法说明: 获取文件的行数
	 * 创建人: 胡翔宇
	 * 创建时间:2014-4-17
	 * @param file
	 * @return
	 */
	public static int getLineNumOfFile(File file){
		// 总共行数
		int num = 0;
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
	        inputReader = new InputStreamReader(inputStream);
	        bufferReader = new BufferedReader(inputReader);
	        
	        String line = "";
	        // 读取一行，解析为对象
			while ((line = bufferReader.readLine()) != null){
				num ++;
			}
		} catch (Exception e) {
			// 记录日志
			e.printStackTrace();
		} finally {
			if(inputStream != null){
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e2) {
					inputStream = null;
				}
			}
			if(inputReader != null){
				try {
					inputReader.close();
					inputReader = null;
				} catch (Exception e2) {
					inputReader = null;
				}
			}
			if(bufferReader != null){
				try {
					bufferReader.close();
					bufferReader = null;
				} catch (Exception e2) {
					bufferReader = null;
				}
			}
		}
		return num;
	}
	
	/**
	 * 方法说明: 获取文件的行数
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param file
	 * @return
	 */
	public static List<String> getLineNumOfFile(File file, int beginLine, int endLine){
		// 从文件中取出endLine - beginLine数来
		List<String> list = new ArrayList<String>();
		int index = 0;
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
	        inputReader = new InputStreamReader(inputStream);
	        bufferReader = new BufferedReader(inputReader);
	        
	        String line = "";
	        // 读取一行，解析为对象,跳过beginLine
			while ((line = bufferReader.readLine()) != null){
				if(index < beginLine){
					// 遗弃，继续下一个循环
					continue ;
				}
				if(index >= endLine){
					// 跳出循环
					break;
				}
				list.add(line);
				index ++;
			}
		} catch (Exception e) {
			// 记录日志
			e.printStackTrace();
		} finally {
			if(inputStream != null){
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e2) {
					inputStream = null;
				}
			}
			if(inputReader != null){
				try {
					inputReader.close();
					inputReader = null;
				} catch (Exception e2) {
					inputReader = null;
				}
			}
			if(bufferReader != null){
				try {
					bufferReader.close();
					bufferReader = null;
				} catch (Exception e2) {
					bufferReader = null;
				}
			}
		}
		return list;
	}
	/**
	 * 方法说明: 读取文件的byte数组
	 * 创建人: huxiangyu
	 * 创建时间:2014-4-17
	 * @param file
	 * @return
	 */
	public static byte[] toByteArray(File f) throws IOException{  
         
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());  
        BufferedInputStream in = null;  
        try{  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while(-1 != (len = in.read(buffer,0,buf_size))){  
                bos.write(buffer,0,len);  
            }  
            return bos.toByteArray();  
        }catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        }finally{  
            try{  
                in.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    }  
}
