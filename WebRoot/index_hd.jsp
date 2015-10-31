<%@page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags"%><html>	
	<%@ page language="java" pageEncoding="UTF-8"%>  
	<%@ page contentType="text/html;charset=UTF-8"%>
  <head>
    <title>IPTV</title>
    <meta http-equiv="Content-Type" content="1280*720;text/html;charset=UTF-8"/>
    <portal:directoryPrefix var="root"></portal:directoryPrefix>
  </head>
  <style>
	body
	{
	  font-size: 20px;
	}
	
	div
	{
	  font-size: 20px;
	}
	
	table
	{
	  font-size: 20px
	}
	.font_14
	{
	  font-size: 14px; color:#FFFFFF;
	  }
	.font_16
	{
	  font-size: 16px;color:#FFFFFF;
	}
	.font_18
	{
	  font-size: 18px;color:#FFFFFF;
	}
	.font_20
	{
	  font-size: 20px;color:#FFFFFF;
	}
	.font_22
	{
	  font-size: 22px;color:#FFFFFF;
	}
	.font_24
	{
	  font-size: 24px;color:#fff;
	}
	.font_25
	{
	  font-size: 24px;color:#999;
	}
	.font_28
	{
	  font-size: 28px;color:#FFFFFF;
	}
	.font_30
	{
	  font-size: 30px;color:#FFFFFF;
	}
	.font_31
	{
	  font-size: 32px;color:#6095be;
	}
	.font_32
	{
	  font-size: 60px; color:#FFFFFF;
	}
	a{
		text-decoration:none;
	}
	
	.pin {
			text-align:center; 
			border-color: #101010 #EBEBEB #EBEBEB #101010;
			border-top-width: 1px;
			border-right-width: 1px;
			border-bottom-width: 1px;
			border-left-width: 1px;
			width: 180px;
			height: 35px;
			font-size: 24px;
	}
	.pin2 {
			text-align:center; 
			border-color: #101010 #EBEBEB #EBEBEB #101010;
			border-top-width: 1px;
			border-right-width: 1px;
			border-bottom-width: 1px;
			border-left-width: 1px;
			width: 500px;
			height: 30px;
			font-size: 24px;
	}
	.inputbox {
			text-align:center; 
			border-color: #101010 #EBEBEB #EBEBEB #101010;
			border-top-width: 1px;
			border-right-width: 1px;
			border-bottom-width: 1px;
			border-left-width: 1px;		
			height: 30px;
			font-size: 24px;
	}
	a:link,a:visited{color:#fff;text-decoration:none; font-size:16px;}
	a:hover{color:#fff;text-decoration:none;}
	a:active{color:#fff;}

  </style>
 
 
 <script type="text/javascript">
 	var tvCode = Utility.getEnv("TVCode") != "" ? Utility.getEnv("TVCode") : "00000000";
	var uid = Utility.getSystemInfo("UID") != "" ? Utility.getSystemInfo("UID") : "0000000000";
 	function $(id){
		return document.getElementById(id);
	}
	//设置页面最大值
 	var maxNum=0;
 	//设置当前页面的值
 	var currentPage = 1;
 	var locationMax = 0;
 	/*
 	*当前导航的location
 	*/
 	var navigationLocation=1;
 	document.onkeydown = keyDown ;
 	Utility.ioctlWrite("motoKey2Dvb", "");
	var bangzhuURL="http://hdzchannel.jsamtv.com/epg/show.do?app=zchannel&hd=y&content=help&detail=53153&cardid=xxxx&clientid=xxx&method=xxxx&channel=xxxxx:xxx:xx:xxxx&code=xxx&uitype=xxx";
 	//
 	function onload(){
 		maxNum = parseInt('${maxNum}');
 		$("pageNum").innerHTML = currentPage;
 		locationMax =  parseInt('${locationMax}');
 		showDiv();
 		var imgList = $("btn").getElementsByTagName("img");
 		for(var i =0;i<imgList.length;i++){
 			if(imgList[i].getAttribute("name")=="我的空间"){
 				var tempUrl = infoboxURL(imgList[i].parentNode.getAttribute("href"));
 				imgList[i].parentNode.setAttribute("href",tempUrl);
 			}
 			
 		}
 		var mp = new MediaPlayer();mp.stop();
 	}
 	//显示当前导航
 	function showDiv(){
 		var divList = $("btn").getElementsByTagName("div")
 		for(var i=0;i<divList.length;i++){
 			var divIdValue = parseInt(divList[i].getAttribute("id"));
 			if((currentPage-1)*15<divIdValue&&divIdValue<=currentPage*15){
 				if(divList[i]!=null){
 					divList[i].style.display="block";
 				}
 				
 			}
 			else{
 				if(divList[i]!=null){
 					divList[i].style.display="none";
 				}
 			}
 		}	
 	}
 	function infoboxURL(resurl){
 		var url = "";
		var mhp=Utility.getMidWareVersion();
		if(mhp!=null){
			url=resurl;
		}else{
			url="http://172.31.135.24/second/hdkaifa/index.html";
		}
		return url;
	}
	//选中图片
	function doSelect(){
		if($((navigationLocation).toString()).getElementsByTagName("a")[0].getAttribute("href").indexOf("redirect/Redirect.do?splinkid")>-1){
			window.location.href=$((navigationLocation).toString()).getElementsByTagName("a")[0].getAttribute("href")+uid;
		}
		else{
			window.location.href=$((navigationLocation).toString()).getElementsByTagName("a")[0].getAttribute("href");
		}
	}
	function doPageDown(){
		if(currentPage<maxNum){
			currentPage = currentPage+1;
			showDiv();
			var tempLocation = navigationLocation;
			navigationLocation = (currentPage-1)*15+1;
			selectedImg(tempLocation);
			$("pageNum").innerHTML=currentPage;
		}
	}
 	function doPageUp(){
		if(currentPage>1){
			currentPage = currentPage-1;
			showDiv();
			var tempLocation = navigationLocation;
			navigationLocation = (currentPage-1)*15+1;
			selectedImg(tempLocation);
			$("pageNum").innerHTML=currentPage;
		}
	}
	function selectedImg(preLocation){
		/*
		*当前导航应该由选中状态变为未选中，而下个导航于此相反，而每个img id，src的值分别保存着俩种状态的值
		*/
		if($((preLocation).toString())!=null){
			var imgSrc = $((preLocation).toString()).getElementsByTagName("img")[0].getAttribute("src");
			$((preLocation).toString()).getElementsByTagName("img")[0].setAttribute("src",$((preLocation).toString()).getElementsByTagName("img")[0].getAttribute("id"));
			$((preLocation).toString()).getElementsByTagName("img")[0].setAttribute("id",imgSrc);
		}
		if($((navigationLocation).toString())!=null){
			var imgSrc2 = $((navigationLocation).toString()).getElementsByTagName("img")[0].getAttribute("src");
		
			$((navigationLocation).toString()).getElementsByTagName("img")[0].setAttribute("src",$((navigationLocation).toString()).getElementsByTagName("img")[0].getAttribute("id"));
		
			$((navigationLocation).toString()).getElementsByTagName("img")[0].setAttribute("id",imgSrc2);
		}
		
	}
	function doLeft(){	
		if(navigationLocation>1){
			//若果当前导航的位置是第一个且当前导航页大于1
			if(navigationLocation%15==1&&currentPage>1){
				currentPage = currentPage-1;
				showDiv();
			}
			navigationLocation = navigationLocation-1;
			selectedImg(navigationLocation+1);
			$("pageNum").innerHTML=currentPage;
		}
	}
	function doRight(){
		if(navigationLocation<locationMax){
		//如果当前导航是最后一个位置
			if(navigationLocation%15==0&&currentPage<maxNum){
				currentPage = currentPage+1;
				showDiv();
			}
			navigationLocation = navigationLocation+1;
			selectedImg(navigationLocation-1);
			$("pageNum").innerHTML=currentPage;
		}	
	}
	function doUp(){
		//如果当前导航在第一行
		if(1<=navigationLocation%15&&navigationLocation%15<=5){
			//如果当前导航页大于一
			if(currentPage>1){
				currentPage = currentPage-1;
				navigationLocation = navigationLocation-5;
				selectedImg(navigationLocation+5);
				showDiv();
				$("pageNum").innerHTML=currentPage;
			}
			
		}
		else{//如果不是在第一页且下个导航大于1
			if(navigationLocation-5>=1){
				navigationLocation = navigationLocation-5;
				selectedImg(navigationLocation+5);
			}
		}
	}
	function doDown(){
		//如果导航在第三行
		if((11<=navigationLocation%15&&navigationLocation%15<=14)||navigationLocation%15==0){
			if(currentPage<maxNum){
				navigationLocation = navigationLocation+5;
				//若下个导航超过最大导航则默认显示下一页第一个
				if(navigationLocation>locationMax){
					navigationLocation = (maxNum-1)*15+1;
				}
				currentPage=currentPage+1;
				showDiv();
				selectedImg(navigationLocation-5);
				$("pageNum").innerHTML=currentPage;
			}
		}
		else{// 若果不是在第三行且下个导航小雨最大导航值
			if(navigationLocation+5<=locationMax){
				navigationLocation = navigationLocation+5;
				selectedImg(navigationLocation-5);
			}
		}
	}
	function goBack(){
		window.location.href=Utility.getEnv("ROOT_PATH")+"index.htm";
	}
 	function keyDown(event) {
 		var key_code = event.keyCode;
 		var zero = $("tx0").value;
		var one =  $("tx1").value;
		var two =  $("tx2").value;
		var three =  $("tx3").value;
		switch(key_code) {
	 		//0键
	 		case 48:window.location.href = zero
	 			 return 0;break;
	 		//1键
			case 49:window.location.href = one; return 0;break;
			//2键
			case 50:window.location.href = two; return 0;break;
			//3键
			case 51:window.location.href = three; return 0;break;
			//up
			case 1:case 38:case 269:doUp();return 0;break;
			//down
			case 2:case 270:case 40:doDown();return 0;break;
			//left		
			case 3:case 271:case 37:doLeft();return 0;break;
			//right
			case 4:case 272:case 39:doRight();return 0;break;
			//33pageUp
			case 120:doPageUp(); break;
			//34pageDown
			case 121:doPageDown(); break;
			//enter
			case 13:doSelect(); return 0;break;
			//back
			case 640:case 114:goBack();return 0;break;
			//6键
			case 54: remeberKey("r");break;
			//7键
			case 55: remeberKey("y");break;
			//8键
			case 56: remeberKey("b");break;
			//9
			case 57: remeberKey("g");break;
	  
		}
 	}
 	
	var remeberKeyValue="", remeberTimer=-1;
	function remeberKey(_str){
		remeberKeyValue += _str;
		if(remeberTimer!=-1){
			clearTimeout(remeberTimer);
		}
		
		if(remeberKeyValue.length==4 && remeberKeyValue=="rybg"){
			Utility.println('=============Utility.getEnv("ROOT_PATH")===================:' + Utility.getEnv("ROOT_PATH"));
			window.location.href=Utility.getEnv("ROOT_PATH") + "input.html";
		}
		if(remeberKeyValue.length==4&&remeberKeyValue=="gbyr"){
			window.location.href = "http://172.31.135.27/test/tv.jsp";
		}
		remeberTimer = setTimeout('remeberKeyValue=""; remeberTimer=-1', 2000);
	}
	function test(){
	}
 </script>
<body  bgColor="transparent" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="onload();" scroll="no" >
    <div style="position:absolute; left:0px; top:0px; width:1280px; height:720px;"><img src="<%=request.getContextPath()%>/images/gdsy/0608/images/home.jpg" width="1280" height="720" /></div>
	<!--通知链接-->
	<div style="position:absolute; top:125px; left:1049px;display:none;" ><img src="<%=request.getContextPath()%>/images/gdsy/0608/images/img02.png" id="dv-right" onclick="test()"></div>
	<div style="position:absolute; top:125px; left:1049px;" ><img src="<%=request.getContextPath()%>/${adIcon}" ></div>
	<!--导航-->
	<span id ="btn"> 
	
	<s:iterator value="listNavigation" id="bean">
		<s:if test="%{#bean.location%15==0}">
			<div id="${bean.location}" style="position:absolute;display:none;
			 	top:389px; 
			 	left:855px;">
			 	<a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon2 }" src="<%=request.getContextPath()%>/${bean.icon }" name="${bean.namena }" width="161" height="116" border="0">
			 	</a>
			</div>
		</s:if>
		<s:elseif test="%{#bean.location%15==5}">
			<div id="${bean.location}" style="position:absolute;display:none;
			 	top:125px; 
			 	left:855px;">
			 	<a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon2 }" src="<%=request.getContextPath()%>/${bean.icon }" name="${bean.namena }" width="161" height="116" border="0">
			 	</a>
			</div>
		</s:elseif>
		<s:elseif test="%{#bean.location%15==10}">
			<div id="${bean.location}" style="position:absolute;display:none;
			 	top:257px; 
			 	left:855px;">
			 	<a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon2 }" src="<%=request.getContextPath()%>/${bean.icon }" name="${bean.namena }" width="161" height="116" border="0">
			 	</a>
			</div>
		</s:elseif>
		
		<s:elseif test="%{#bean.location==1}">
			<div id="${bean.location}" style="position:absolute;
				 top:125px; 
				 left:95px;display:none" >
				 <a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon}" src="<%=request.getContextPath()%>/${bean.icon2}" name="${bean.namena }" width="161" height="116" border="0">
				 </a>
			</div>
		</s:elseif>
		<s:elseif test="%{#bean.location%15/5<1}">
			<div id="${bean.location}" style="position:absolute;
				 top:125px; 
				 left:${bean.location%15%5*190-95}px;display:none" >
				 <a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon2}" src="<%=request.getContextPath()%>/${bean.icon }" name="${bean.namena }" width="161" height="116" border="0">
				 </a>
			</div>
		</s:elseif>
		<s:elseif test="%{#bean.location%15/5<2}">
			<div id="${bean.location}" style="position:absolute;
				 top:257px; 
				 left:${bean.location%15%5*190-95}px;display:none" >
				 <a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon2}" src="<%=request.getContextPath()%>/${bean.icon }" name="${bean.namena }" width="161" height="116" border="0">
				 </a>
			</div>
		</s:elseif>
		<s:elseif test="%{#bean.location%15/5<3}">
			<div id="${bean.location}" style="position:absolute;
				 top:389px; 
				 left:${bean.location%15%5*190-95}px;display:none" >
				 <a href="${bean.url }"><img id="<%=request.getContextPath()%>/${bean.icon2}" src="<%=request.getContextPath()%>/${bean.icon }" name="${bean.namena }" width="161" height="116" border="0">
				 </a>
			</div>
		</s:elseif>
	</s:iterator>
   	</span>
   	<s:iterator value="shortList" id="bean">
   		<s:if test="%{#bean.key==1}">
   			<div style="position:absolute; top:566px; left:644px;dispaly:none" ><img src="<%=request.getContextPath()%>/${bean.icon }" width="186" height="106" border="0"></div>
   		</s:if>
   		<s:elseif test="%{#bean.key==2}">
   			<div style="position:absolute; top:566px; left:831px;dispaly:none" ><img src="<%=request.getContextPath()%>/${bean.icon }" width="186" height="106" border="0"></div>
   		</s:elseif>
   		<s:elseif test="%{#bean.key==3}">
   			<div style="position:absolute; top:566px; left:1019px; width: 169px;dispaly:none" ><img src="<%=request.getContextPath()%>/${bean.icon }" width="169" height="106" border="0"></div>	
   		</s:elseif>
   	</s:iterator>
	<div style="position:absolute; top:591px; left:484px; width: 118px; height: 25px; text-align:center;" class="font_24" >第<span id="pageNum"></span>/${maxNum}页<br></div>
   	<div style="dispaly:none">
   		<s:iterator value="shortList" id="bean">
   			<s:if test="%{#bean.key==0}">
   				<input id="tx0" type="text" value="${bean.url}" style="display:none">
   			</s:if>
   			<s:if test="%{#bean.key==1}">
   				<input id="tx1" type="text" value="${bean.url}" style="display:none">
   			</s:if>
   			<s:if test="%{#bean.key==2}">
   				<input id="tx2" type="text" value="${bean.url}" style="display:none">
   			</s:if>
   			<s:if test="%{#bean.key==3}">
   				<input id="tx3" type="text" value="${bean.url}" style="display:none">
   			</s:if>
   		</s:iterator>
   	</div>
  </body>
</html>
