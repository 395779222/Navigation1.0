<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
	java.net.InetAddress inet = java.net.InetAddress.getLocalHost();
	String hostName = inet.getHostName();
	String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0
			|| "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}

	String ipTemp = ip.substring(ip.length() - 2, ip.length());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>万事通·导航分级管理系统</title>
		<link rel="stylesheet" href="newstyle/index.css" type="text/css"></link>
		<link rel="shortcut icon" href="favorite.ico" type="image/x-icon" />
		<script type="text/javascript" src="newstyle/easyui/jquery.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
		/**实现功能，保存用户的登录信息到cookie中。当登录页面被打开时，就查询cookie**/
		var username = getCookieValue("username");
		$("#username").val(username);
		var password = getCookieValue("password");
		$("#password").val(password);
		if ($("#password").val() != '' && $("#username").val() != '') {
			$("#showCode").focus();
			$("input[name='chkRememberPass']").attr("checked", true);
		} else {
			$("#username").focus();
		}
	});
	function getvalidate() {
		var date = new Date();
		var mytime = date.toLocaleTimeString();
		$("#imgValidateUpload").attr("src",
				"imgValidateUpload.action?date=" + mytime);
	}
	function submitLogin() {
		var username = $("#username").val();
		if (username == '') {
			$("#error").html("请输入登录名称！").show();
			return false;
		}

		var password = $("#password").val();
		if (password == '') {
			$("#error").html("请输入登录密码！").show();
			return false;
		}
		var showCode = $("#showCode").val();
		if (showCode == '') {
			$("#error").html("请输入验证码！").show();
			return false;
		}
		if ($("input[name='chkRememberPass']:checked").size() == 1) {
			//添加cookie  
			addCookie("username", username, 24, "/");
			addCookie("password", password, 24, "/");
		}
		if ($("input[name='chkRememberPass']:checked").size() == 0) {//删除cookie	        
			deleteCookie("username", "/");
			deleteCookie("password", "/");
		}
	}
	function addCookie(username, password, days, path) {
		/**添加设置cookie**/
		var username = escape(username);
		var password = escape(password);
		var expires = new Date();
		expires.setTime(expires.getTime() + days * 3600000 * 24);
		//path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用  
		path = path == "" ? "" : ";path=" + path;//GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC  
		//参数days只能是数字型  
		var _expires = (typeof days) == "string" ? "" : ";expires="
				+ expires.toUTCString();
		document.cookie = username + "=" + password + _expires + path;
	}
	function getCookieValue(username) {
		/**获取cookie的值，根据cookie的键获取值**/
		var username = escape(username); //用处理字符串的方式查找到key对应value    
		var allcookies = document.cookie; //读cookie属性，这将返回文档的所有cookie         
		username += "="; //查找名为name的cookie的开始位置  
		var pos = allcookies.indexOf(username);
		//如果找到了具有该名字的cookie，那么提取并使用它的值  
		if (pos != -1) { //如果pos值为-1则说明搜索"version="失败  
			var start = pos + username.length; //cookie值开始的位置  
			var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置  
			if (end == -1)
				end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie  
			var value = allcookies.substring(start, end); //提取cookie的值  
			return (value); //对它解码        
		} else { //搜索失败，返回空字符串  
			return "";
		}
	}
	/**根据cookie的键，删除cookie，设置其失效**/
	function deleteCookie(username, path) {
		var username = escape(username);
		var expires = new Date(0);
		path = path == "" ? "" : ";path=" + path;
		document.cookie = username + "=" + ";expires=" + expires.toUTCString()
				+ path;
	}
</script>

	</head>

	<body style="background:#fff" onload="getvalidate()">
		<table class="layout">
			<tr>
				<td height="40" valign="top">
					<div class="head-bg">

						<div class="logo"></div>
						<div class="seach-nav">
							<ul>
								<li style="float: right">
									<a href="#" class="help" title="帮助"></a>
								</li>
							</ul>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<div class="login-container">
						<div class="login-title">
							登录
						</div>
						<div class="login-content">
							<s:if test="userError==null">
								<div class="error" id="error" style="display: none"></div>
							</s:if>
							<s:else>
								<div class="error" id="error">
									${userError}
								</div>
							</s:else>
							<form action="login.action" method="post"
								onsubmit="return submitLogin()">
								<div class="login-form">
									<table>
										<tr>
											<td width="60">
												用户名：
											</td>
											<td>
												<input type="text" name="username" id="username"
													class="input" value="" size=30 />
											</td>
										</tr>
										<tr>
											<td>
												密&nbsp;&nbsp;&nbsp;码：
											</td>
											<td>
												<input type="password" name="password" id="password"
													value="" class="input" size=30 />
											</td>
										</tr>
										<tr>
											<td>
												验证码：
											</td>
											<td>
												<input id="showCode" class="input" value="" name="showCode"
													style="width: 25%;" maxlength="6"></input>
												<img onclick="getvalidate()" title="看不清？点击换一张图片"
													src="no.jpg" id="imgValidateUpload" align="absmiddle"
													width="70" height="30"></img>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
											<td>
												<input type="checkbox" value="1" name="chkRememberPass"
													id="chkRememberPass" />
												<span style="color: #909090">在这台计算机上记住我的登录信息</span>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
											<td>
												<input type="submit" name="submit" value="登录" class="button" />
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="reset" class="button" id="resetLogin"
													name="resetLogin" value="重 置" />
											</td>
										</tr>
									</table>
								</div>

							</form>
						</div>
					</div>
				</td>
			</tr>

		</table>

	</body>
</html>
