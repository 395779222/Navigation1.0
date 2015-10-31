
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		function userReLogin(){
			top.window.location.href="<%=basePath%>login.jsp";
	    }
        </script>
 </head>
 <body style="background: #fff">
		<table class="layout">
            <tr>
				<td align="center" valign="middle">
					<div class="login-container">
						<div class="login-title">
							超时提醒
						</div>
						<div class="login-content">
							<div class="login-form" style="height:100px;padding-top:30px">
								登录系统超时或没有登录系统，请重新登录系统
							    <a href="javascript:userReLogin()">登录</a>  
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
