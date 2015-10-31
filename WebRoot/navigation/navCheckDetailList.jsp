<%@page import="cn.eainfo.system.po.SysRole"%>
<%@page import="cn.eainfo.system.po.SysArea"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Basic DataGrid - jQuery EasyUI Demo</title>
		<link rel="stylesheet" type="text/css"
			href="../newstyle/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="../newstyle/easyui/themes/icon.css">
		<script type="text/javascript" src="../newstyle/easyui/jquery.js"></script>
		<script type="text/javascript" src="../newstyle/easyui/ajaxtools.js"></script>
		<script type="text/javascript"
			src="../newstyle/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="../newstyle/easyui/locale/easyui-lang-zh_CN.js"></script>
			<script type="text/javascript" src="../newstyle/datepicker/WdatePicker.js"></script>


<script type="text/javascript">
	//审核类型
	var checkType = '${checkType}';
	var areaNo = '${areaNo}';
	var type = '${type}';
	//0表示当前步骤没有执行或执行的不符合要求
	var preLook ="0";
	//审核标志应当有俩个，快捷键审核，和导航审核俩个都审核通过才可以让其预览发布
	var shortCheckFlag = '${shortCheckFlag}';
	var navCheckFlag = '${navCheckFlag}';
	var preRelease = "0";
	function preRealse(){
		//预览发布要确保导航审核与快捷键审核都已经审核通过
		if(shortCheckFlag!="1"){
			$.messager.alert('消息提醒','快捷键审核未通过', 'warning');
			return false;
		}
		if(navCheckFlag!="1"){
			$.messager.alert('消息提醒','导航审核审核未通过', 'warning');
			return false;
		}
		preRelease = "1";
		$.messager.confirm('预览', '确认预览发布?', function(r) {
			if (r) {
				$.tools.post("releaseNavigation!preRelease", {
					areaNo :areaNo,
					type :type
				}, function(data) {
					if (data.result != 'success') {
						$.messager.alert('消息提醒', data.resume, 'warning');
					} else {
						$.messager.alert('消息提醒', '预览发布成功', 'warning');
					}
				});
			}
		});
		$("#a-formal").linkbutton({disabled:false});
	}
	/*function check(value){
		//如果没有进行预览了的话
		if(preLook!="1"){
			$.messager.alert('消息提醒','请先预览导航页面再进行审核', 'warning');
			return false;
		}
		var checkText = "";
		jQuery.messager.prompt('提示:','请输入审核信息.',function(r){
			if(r){
				checkText = r;
				$.tools.post("areaNavCheckMsg!ajaxUpdate", {
					areaNo: areaNo,
					type : type,
					checkState : value,
		 			checkText :checkText,
					checkType : checkType
				}, function(data) {
					if (data.result != 'success') {
						$.messager.alert('消息提醒', data.resume, 'warning');
					} else {
						$.messager.alert('消息提醒', '地区导航信息审核更新成功', 'warning');
						searchList();
						// 若当前页面是导航审核则
						
					}
				});
			}
		});
		if(checkType=="navigationCheckNum"&&value=="审核通过"){
			//导航审核标志
			navCheckFlag="1";
			if(shortCheckFlag=="1"){
				$("#a-preLook").linkbutton({disabled:false});
			}
		}else if(checkType=="navigationCheckNum"&&value=="审核不通过"){
			navCheckFlag="0";
			$("#a-preLook").linkbutton({disabled:true});
		}
		if(checkType=="shortCheckNum"&&value=="审核通过"){
			shortCheckFlag ="1";
			if(navCheckFlag=="1"){
				$("#a-preLook").linkbutton({disabled:false});
			}
		}else if(checkType=="shortCheckNum"&&value=="审核不通过"){
			shortCheckFlag="0";
			$("#a-preLook").linkbutton({disabled:true});
		}
	}*/
	function searchList(){
		if(checkType=="navigationCheckNum"){
			$('#tt').datagrid('load', {
				"type":type,
				"areaNo" : areaNo	
			});
		}
		else{
			$('#tt2').datagrid('load', {
				"type":type,
				"areaNo" : areaNo	
			});
		}
		
	}
	function pagePreLook(){
		preLook = "1";
		window.open('getNavigation!getPreLookNavigation?areaNo='+areaNo+'&type='+type,'','height=800,width=1024,scrollbars=yes,status =yes');
		$("#checkSelect").attr("disabled",false);
	}
	function officially(){
		if(preRelease!="1"){
			$.messager.alert('消息提醒','请先进行预览发布再进行正式发布', 'warning');
			return false;
		}
		$.messager.confirm('发布', '确认正式发布?', function(r) {
			if (r) {
				$.tools.post("releaseNavigation!officially", {
					areaNo :areaNo,
					type : type
				}, function(data) {
					if (data.result != 'success') {
						$.messager.alert('消息提醒', data.resume, 'warning');
					} else {
						$.messager.alert('消息提醒', '发布成功', 'warning');
					}
				});
			}
		});
	}
	function searchByCheckType() {
		window.location.href='areaNavCheckMsg!ajaxCheckListInit?_rnd='+ Math.random(10000000000)+"&areaNo="+areaNo+
		"&state="+$("#stateSearch").val()+"&type="+type+'&checkType='+$("#checkTypeSearch").val();
				
	}
	$(document).ready(function(){
		$("#stateSearch").val('${state}');
		//初始化页面若是导航审核则显示导航的审核信息
		if(checkType=="navigationCheckNum"){
			$('#tt').datagrid({	
					    title : '导航审核信息',
						iconCls : 'icon-save',
						pagination : true,
						toolbar : "#tb",
						fit : true,
						fitColumns : true,
						singleSelect : true,
						method : 'post',
						remoteSort : true,
						url : 'navigation!ajaxCheckList?_rnd='
									+ Math.random(10000000000),
						nowrap : false,
						striped : true,
						remoteSort : false,
						queryParams : {
							"areaNo" : areaNo,
							"type":type,
							"state":'${state}'
						},
						/**数据格式定义**/
						columns : [ [
								{
									field : 'type',
									title : '类型',
									width : 40,
									align : 'center',
									formatter : function(val,rec) {
										switch (val) {
							 				case 'hd':return '<font color="blue">高清</font>';
							 				case 'sd':return '<font color="blue">标清</font>';
										}
									}
								},
								{
									field : 'num',
									title : '所在页面',
									width : 30,
								},
								{
									field : 'location',
									title : '所在位置',
									width : 30,
								},
								{
									field : 'namena',
									title : '业务名称',
									width : 40
								},
								{
									field : 'url',
									title : '跳转链接',
									width : 120
								},
								
								{
									field : 'icon',
									title : '未选中图标',
									width : 50,
									height : 50,
									align : 'center',
									formatter : function(value,
											rec) {
										var str1='<img width="40" heigth="40" src=';
											 str1=str1+'"../';
											 str1=str1+value+'">';
										return str1;
									}
								},
								{
									field : 'icon2',
									title : '选中图标',
									width : 50,
									height : 50,
									align : 'center',
									formatter : function(value,
											rec) {
											var str1='<img width="40" heigth="40" src=';
											 str1=str1+'"../';
											 str1=str1+value+'">';
										return str1;
									}
								},
								
								{
									field : 'state',
									title : '状态',
									width : 40,
									formatter : function(val,rec) {
										if(val=="审核通过"){
											return "<span style=\"color:green\">"+val+"</span>";
										}if(val=="待审核"||val=="审核不通过"){
											return  "<span style=\"color:red\">"+val+"</span>";
										}
									}	
								},
								{
									field : 'cityEnName',
									title : '地区',
									width : 40
								}
								] ],
						/**数据格式定义**/
						pageSize : 20,
						pageNumber : 1,
						onLoadSuccess : function(data) {
								//处理出现错误和会话超时的问题
								if (undefined != data.result
										&& data.result == 'faild') {
									$.messager.alert('消息提醒',
											data.resume,
											'warning');
								}
						}
					});
		}
		//初始化页面若是快捷键审核则显示快捷键审核信息
		else{
			$('#tt2').datagrid({	
					    title : '快捷键审核信息',
						iconCls : 'icon-save',
						pagination : true,
						toolbar : "#tb",
						fit : true,
						fitColumns : true,
						singleSelect : true,
						method : 'post',
						remoteSort : true,
						url : 'short!ajaxList?_rnd='
									+ Math.random(10000000000),
						nowrap : false,
						striped : true,
						remoteSort : false,
						queryParams : {
							"areaNo" : areaNo,
							"type":type,
							"checkState":'${state}'
						},
						/**数据格式定义**/
						columns : [ [
								{
									field : 'type',
									title : '类型',
									width : 15,
									formatter : function(val,rec) {
										switch (val) {
										 	case 'hd':return "高清";
										 	case 'sd':return "标清";
										}
									}
								},
								{
									field : 'namena',
									title : '业务名称',
									width : 40
								},
								{
									field : 'key',
									title : '快捷键',
									width : 20
								},
								{
									field : 'url',
									title : '跳转链接',
									width : 60
								},
								{
									field : 'icon',
									title : '图片',
									width : 50,
									height : 50,
									align : 'center',
									formatter : function(value,
											rec) {
											var str1='<img width="40" height="40" src=';
											 str1=str1+'"../';
											 str1=str1+value+'">';
										return str1;
									}	
								},
								
								{
									field : 'checkState',
									title : '审核状态',
									width : 40,
									formatter : function(val,rec) {
										if(val=="待审核"){
											return "<span style=\"color:red\">"+val+"</span>";
										}else{
											return  "<span style=\"color:green\">"+val+"</span>";
										}
									}	
								}
								
								] ],
						/**数据格式定义**/
						pageSize : 20,
						pageNumber : 1,
						onLoadSuccess : function(data) {
								//处理出现错误和会话超时的问题
								if (undefined != data.result
										&& data.result == 'faild') {
									$.messager.alert('消息提醒',
											data.resume,
											'warning');
								}
						}
					});
		}
		//审核按钮不可操作
		$("#tb").find("a").each(function(index){
			if(index>1){
				$(this).linkbutton({disabled:true});
			}
		});
		$("#checkSelect").attr("disabled",true);
		if( shortCheckFlag =="1"&&navCheckFlag =="1"){
			$("#a-preRelease").linkbutton({disabled:false});
		}
		$("#a-formal").linkbutton({disabled:true});
	});
	
	function checkSelect(objSelect){
		var value = $(objSelect).val();
		if($(objSelect).val()=="-1"){
			return false;
		}
		//如果没有进行预览了的话
		if(preLook!="1"){
			$.messager.alert('消息提醒','请先预览导航页面再进行审核', 'warning');
			return false;
		}
		var checkText = "";
		jQuery.messager.prompt('提示:','请输入审核信息.',function(r){
			if(r){
				checkText = r;
				$.tools.post("areaNavCheckMsg!ajaxUpdate", {
					areaNo: areaNo,
					type : type,
					checkState : value,
		 			checkText :checkText,
					checkType : checkType
				}, function(data) {
					if (data.result != 'success') {
						$.messager.alert('消息提醒', data.resume, 'warning');
					} else {
						$.messager.alert('消息提醒', '地区导航信息审核更新成功', 'warning');
						searchList();
						// 若当前页面是导航审核则
						
					}
				});
			}
		});
		if(checkType=="navigationCheckNum"&&value=="审核通过"){
			//导航审核标志
			navCheckFlag="1";
			if(shortCheckFlag=="1"){
				$("#a-preRelease").linkbutton({disabled:false});
			}
		}else if(checkType=="navigationCheckNum"&&value=="审核不通过"){
			navCheckFlag="0";
			$("#a-preRelease").linkbutton({disabled:true});
		}
		if(checkType=="shortCheckNum"&&value=="审核通过"){
			shortCheckFlag ="1";
			if(navCheckFlag=="1"){
				$("#a-preRelease").linkbutton({disabled:false});
			}
		}else if(checkType=="shortCheckNum"&&value=="审核不通过"){
			shortCheckFlag="0";
			$("#a-preRelease").linkbutton({disabled:true});
		}
		
	}
	
</script>
	</head>		
		<body>
		<DIV id=tb class=searchBox>
			<TABLE>
				<TBODY>
				<tr>
						<TH width=55 align="left">
							审核状态
						</TH>
						<TD width=70 align="left">
							<select id="stateSearch" name="state">
								
								<option value="待审核">待审核</option>
								<option value="审核通过">审核通过</option>
								<option value="审核不通过">审核不通过</option>
							</select> 
						</TD>
						<TH width=55 align="left">
							审核类型
						</TH>
						<TD width=70 align="left">
							<select id="checkTypeSearch" name="checkType">
								<option value="shortCheckNum">快捷键审核</option>
								<option value="navigationCheckNum">导航信息审核</option>
							</select> 
						</TD>
						<td width=80 align="center">
							<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"
							onclick="searchByCheckType()">查询</a>
						</td>
				</TR>
				</TBODY>
			</TABLE>
			<div id="tb" class="grid-option">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="pagePreLook()">导航页面预览</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select id="checkSelect" onchange="checkSelect(this)" style="color:red">
					<option value="-1">请审核</option>
					<option value="审核通过">审核通过</option>
					<option value= 审核不通过>审核不通过</option>
					<option value="待审核">撤销审核 </option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-add" -->
<!-- 					plain="true" onclick="check('审核通过')" id="a-pass">审核通过</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-add" -->
<!-- 					plain="true" onclick="check('审核不通过')" id="a-notPass">审核不通过</a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-add" -->
<!-- 					plain="true" onclick="check('待审核')" id="a-back">撤销审核 </a>&nbsp;&nbsp;&nbsp;&nbsp; -->
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="preRealse()" id="a-preRelease">预览发布</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="officially()" id="a-formal">正式发布</a>
				
			</div>
			</div>
			
			</DIV>
		<!-- 数据处理 -->
		
			<table id="tt" >

			</table>
		
		
			<table id="tt2" >

			</table>
		
	</body>
</html>