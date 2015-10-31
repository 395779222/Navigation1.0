<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>  
<%@page import="java.util.List"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="1280*720;text/html; charset=gb2312" />
<title>中国</title>
<style type="text/css">
body {	margin:0;	padding:0;	font-:黑体;	font-size:24px;	background:url(images/linksbj.jpg) no-repeat left top;	font-family: "黑体";}
ul,li{ padding:0; margin:0; list-style:none; }
div, h1{padding:0; margin:0;}
a img{ color:#2a2a2a; border:none;}
a,a:hover { text-decoration:none; color:#2a2a2a;}
.nav {
	position:absolute;
	left:78px;
	top:129px;
	width:1200px;
	height: 437px;
}
*.nav li{ 
	float:left; 
    width:300px; 
	height:40px;
	line-height:40px;
}
.nav a { 
	float:left; 
	display:block;
	background:url(images/anniu1.gif) no-repeat;
	padding-left:40px;

	}
.nav a:hover { 
	color:#FF6600; 
	background:url(images/anniu2.gif) no-repeat left top;}

</style>
<script>

Coship.setDrawFocusRing(1);
Utility.setDrawFocusRing(0);

</script>
</head>

<script type="text/javascript">

	
	function re() {
	var uid =  Utility.getSystemInfo("UID")
	location.href='http://sdamsp.jsamtv.com/redirect/Redirect.do?splinkid=04-019&userCode=' + uid;
}

function old() {
	var uid =  Utility.getSystemInfo("UID")
	location.href='http://hdamsp.jsamtv.com/redirect/Redirect.do?splinkid=11-101&userCode=' + uid;
}

function movie() {
	var uid =  Utility.getSystemInfo("UID")
	location.href='http://hdamsp.jsamtv.com/redirect/Redirect.do?splinkid=04-130&userCode=' + uid;
}

</script>
<body>
	<ul class="nav">
	<logic:iterate id="bean" collection="${testlist}">
		<li><a href="${bean.url }">${bean.name }</a></li>
	</logic:iterate>
  
</ul>
</body>
</html>
