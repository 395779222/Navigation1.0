package cn.eainfo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * <文件同步实现类>
 * @author  Administrator
 * @version  [版本号, 2014-3-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class SshUtil {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private String hostname;
	private Integer port;
	private String username;
	private String password;
	private static String cmd;
	private Integer exitStatus;
	
	
	/** <默认构造函数>
	 */
	public SshUtil() {
	}
	
	public SshUtil(String hostname, Integer port, String username,
			String password, String cmd) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		SshUtil.cmd=cmd;
	}



	/** 
	 * <功能详细描述>
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("finally")
	public String execCmd(){
		logger.log(Level.ERROR, " execCmd()######同步到服务器执行中#######");
		Connection conn = null;
		Session sess = null;
		StringBuffer sb = new StringBuffer();
		try {
			/* Create a connection instance */

			/* Now connect */
			conn = new Connection(hostname, port);
			conn.connect();
			logger.info("connect ok");
			/*
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			logger.info("Authentication ok");
			/* Create a session */
			sess = conn.openSession();
			sess.execCommand(cmd);
			logger.info("Here is some information about the remote host:");
			/*
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 */
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				logger.info(line);
				sb.append(line);
			}
			/* Show exit status, if available (otherwise "null") */
			exitStatus = sess.getExitStatus();
			logger.info("ExitCode: " + sess.getExitStatus());
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			logger.log(Level.ERROR, " execCmd()######同步到服务器出错#######");
			
		}finally{
			if(null!=sess) sess.close();
			/* Close the connection */
			if(null!=conn) conn.close();
			logger.log(Level.INFO, " execCmd()######同步到服务器结束#######");
			return sb.toString();
		}
	}

/*	public String execCmd2() {

		try {
			 Create a connection instance 
			Connection conn = new Connection(hostname, port);
			 Now connect 
			conn.connect();
			logger.info("connect ok");
			
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			logger.info("Authentication ok");
			 Create a session 
			Session sess = conn.openSession();
			sess.execCommand(cmd);
			logger.info("Here is some information about the remote host:");
			
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			StringBuffer sb = new StringBuffer();
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				logger.info(line);
				sb.append(line);
			}
			 Show exit status, if available (otherwise "null") 
			exitStatus = sess.getExitStatus();
			logger.info("ExitCode: " + sess.getExitStatus());
			 Close this session 
			sess.close();
			 Close the connection 
			conn.close();

			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
			return exitStatus.toString();
		}
	}*/	
	
	public static void main(String[] args) throws IOException {
		SshUtil sshUtil = new SshUtil();
		sshUtil.execCmd();
	}
}
