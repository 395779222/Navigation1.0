<html xmlns="http://www.w3.org/1999/xhtml">  
<%@page import="cn.eainfo.navigation.action.CustomerInfoAction"%>   
<%@page import="java.net.URLEncoder"%>   
<%@page language="java" contentType="text/html; charset=GBK"pageEncoding="GBK"%>   
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/newstyle/easyui/jquery.js"></script>   
</head>
<script type="text/javascript">
function getUserCity(cardNo){
  var userCityName = "";
	var dataInfo = {cardNo:cardNo};
	$.ajax({
		async:false,
		type: "post",
		data: dataInfo,
		url: "<%=request.getContextPath()%>/navigation/customerInfo!getUserCityName",
		dataType: "json",
		success: function(data) {
			if (data.result != 'success') {
				$.messager.alert('ÏûÏ¢ÌáÐÑ', data.resume, 'warning');
				userCityName = "emergency";
			}
			else{
				userCityName = data.resume;
			}
		}
		});
		return userCityName;
}
window.onload=function(){
//	var caSerialNum = CA.serialNumber;
	//caSerialNum = caSerialNum.substr(0,15);
	caSerialNum = "111111111111";
	var host = window.location.hostname ;  
	alert(host);
	if(host.indexOf("127.0.0.1")>-1) {    
		var enCityName = getUserCity(caSerialNum);   
		window.location.href="<%=request.getContextPath()%>/Root/"+enCityName+"/index_"+enCityName+"_hd.jsp";
	}
	if(host.indexOf("sdtvinfo.jsamtv.com")>-1) {    
		var enCityName = getUserCity(caSerialNum);   
		window.location.href="<%=request.getContextPath()%>/Root/"+enCityName+"/index_"+enCityName+"_sd.jsp";
	}
	<%
		String host = request.getHeader("Host"); 
		String op = request.getParameter("op"); 
		if(host == null) { 
			response.sendRedirect("emergency/index_emergency_hd.jsp"); 
		}
		if(host.indexOf("sdtvinfo.jsamtv.com")>-1) { 
		}
		if(host.indexOf("hdtvinfo.jsamtv.com")>-1) { 
		}
		else if(host.indexOf("hdgjzh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.25/stbstock"); 
		}		else if(host.indexOf("hdtqzx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdtqzx/tianqizixun.jsp"); 
		}		else if(host.indexOf("hdxxly-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdxxly/index.html"); 
		}		else if(host.indexOf("hddyzx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hddyzx/index.html"); 
		}		else if(host.indexOf("hdyfjy-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:8098/welcome.htm"); 
		}		else if(host.indexOf("hdmscx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdmscx/index_1.html"); 
		}		else if(host.indexOf("hdzxyq-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:9091/index.html"); 
		}		else if(host.indexOf("hdylws-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:8092/all/index.php"); 
		}		else if(host.indexOf("hdjsjs-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdqyfc/index.html"); 
		}		else if(host.indexOf("hdsckx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdsckx/index.html"); 
		}		else if(host.indexOf("voting.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.170.2:80/goviewvote/VoteMain"); 
		}		else if(host.indexOf("hdwzfw-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:8094/lbs/lbs/load.jsp"); 
		}		else if(host.indexOf("hddsbx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hddsbx/index.html"); 
		}		else if(host.indexOf("hddsgh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.153.13/zxghhd/"); 
		}		else if(host.indexOf("hdcswq-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdjs/index.html"); 
		}		else if(host.indexOf("hdggly-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.27:8084/ggly_gq/index.jsp"); 
		}		else if(host.indexOf("hdjsln-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.28:9080/jsln/hd/index.html"); 
		}		else if(host.indexOf("hdjlms-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdjlms/index.html"); 
		}		else if(host.indexOf("hdmfzf-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdmfmf/index.html"); 
		}		else if(host.indexOf("hdqzzp-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdqzzp/index.html"); 
		}		else if(host.indexOf("hdslpw-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdslpw/index.html"); 
		}		else if(host.indexOf("hdjgfw-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdjgfw/index.html"); 
		}		else if(host.indexOf("hdjjzs-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdjjzs/index.html"); 
		}		else if(host.indexOf("hdzxjt-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.28:9000/jtcms/jiaotong/loading.html"); 
		}		else if(host.indexOf("hdxfhj-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdxfhj/index.html"); 
		}		else if(host.indexOf("hdqcsh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdqcsh/index.html"); 
		}		else if(host.indexOf("hdbbcz-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdbbcz/index.html"); 
		}		else if(host.indexOf("hdamyz-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/hdamyz/index.html"); 
		}		else if(host.indexOf("hdaxcs-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.28:8090/jsCharity/charity/hd/index.jsp"); 
		}		else if(host.indexOf("sdmfzf-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdmfmf/index720.html"); 
		}		else if(host.indexOf("sdxxly-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdxxly/index720.html"); 
		}		else if(host.indexOf("sdaxcs-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.28:8090/jsCharity/charity/sd/index.jsp"); 
		}		else if(host.indexOf("sdzxyq-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:6668/index.html"); 
		}		else if(host.indexOf("sdjgfw-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdjgfw/index.html"); 
		}		else if(host.indexOf("sdbbcz-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdbbcz/index720.html"); 
		}		else if(host.indexOf("sdxfhj-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdxfhj/index720.html"); 
		}		else if(host.indexOf("sdjsln-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.28:9080/jsln/index.html"); 
		}		else if(host.indexOf("sdamyz-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdyj/yj.html"); 
		}		else if(host.indexOf("sdjsjs-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdqyfc/index.html"); 
		}		else if(host.indexOf("sdgjzh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.25/stbstock_sd"); 
		}		else if(host.indexOf("sdtqzx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdtqzx/tianqizixun.jsp"); 
		}		else if(host.indexOf("sdylws-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:8092/biao/index.php"); 
		}		else if(host.indexOf("sdslpw-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdslpw/index720.html"); 
		}		else if(host.indexOf("http://voting.jsamtv.com/wst/SD/index.html")>-1 ) {
 			response.sendRedirect("http://172.31.170.2/wst/SD/index.html"); 
		}		else if(host.indexOf("sdwzfw-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:8104/lbs/lbs/load.jsp"); 
		}		else if(host.indexOf("sddsgh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.153.13/zxghsd/"); 
		}		else if(host.indexOf("sdmscx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdmscx/index720.html"); 
		}		else if(host.indexOf("sdqcsh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdqcsh/index720.html"); 
		}		else if(host.indexOf("sdjlms-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdjlms/index720.html"); 
		}		else if(host.indexOf("sdyfjy-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.30:8096/welcome.htm"); 
		}		else if(host.indexOf("sddzyh-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdjs/index.html"); 
		}		else if(host.indexOf("sdsckx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdyj/yj.html"); 
		}		else if(host.indexOf("sddyzx-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sddyzx/index720.html"); 
		}		else if(host.indexOf("sdggly-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.27:8082/ggly/index.jsp"); 
		}		else if(host.indexOf("sdqzzp-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdqzzp/index720.html"); 
		}		else if(host.indexOf("sdjjzs-tvinfo.jsamtv.com")>-1 ) {
 			response.sendRedirect("http://172.31.135.24/second/sdjjzs/index.html"); 
		}	
	%>
}
</script>
<body>
</body>
</html>