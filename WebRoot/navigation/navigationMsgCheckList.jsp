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
	function searchList() {
		$('#tt').datagrid('load', {
			"checkState":$("#stateSearch").val(),
			"type" : $("#typeSearch").val()
		});
	}
	/*
	*这里会有2种审核标志;shortChecknum(点击快捷键待审核)
	*navigationChecknum(点击快捷键已审核);
	*/
	function checkAreaMsg(areaNo,type,checkType){
		window.location.href='areaNavCheckMsg!ajaxCheckListInit?_rnd='
									+ Math.random(10000000000)+"&areaNo="+areaNo+"&type="+type+"&checkType="+checkType;
	}
	$(document).ready(function() {
		$('#tt').datagrid({	
				onDblClickRow:function(index,row){
					checkAreaMsg(row.areaNo,row.type,"navigationCheckNum");
				},
			    title : '任务审核',
				iconCls : 'icon-save',
				pagination : true,
				toolbar : "#tb",
				fit : true,
				fitColumns : true,
				singleSelect : true,
				method : 'post',
				remoteSort : true,
				url : 'areaNavCheckMsg!ajaxList?_rnd='
							+ Math.random(10000000000),
				nowrap : false,
				striped : true,
				 
				remoteSort : false,
				queryParams : {
					"checkState":$("#stateSearch").val()
				},
				/**数据格式定义**/
				columns : [ [
						{
							field : 'cityEnName',
							title : '地区',
							width : 40
						},
						{
							field : 'shortCheckNum',
							title : '快捷键待审核',
							width : 40,
							formatter : function(val,rec) {
								return '<a href="#" onclick=checkAreaMsg("'+rec.areaNo+'","'+rec.type+'","shortCheckNum")><span style=\"color:red\">'+val+'</span></a>'
							}	
						},
						{
							field : 'shortNotCheckNum',
							title : '快捷键已审核',
							width : 40,
							formatter : function(val,rec) {
								return '<span>'+val+'</span>'
							}	
						},
						{
							field : 'isToCheckNum',
							title : '导航待审核数量',
							width : 50,
							formatter : function(val,rec) {
								return '<a href="#" onclick=checkAreaMsg("'+rec.areaNo+'","'+rec.type+'","navigationCheckNum")><span style=\"color:red\">'+val+'</span></a>'
							}	
						},
						{
							field : 'isNotToCheckNum',
							title : '导航已审核数量',
							width : 50,
							formatter : function(val,rec) {
								return '<span>'+val+'</span>';
							}	
						},
						{
							field : 'checkText',
							title : '审核语',
							width : 120,
						},
						{
							field : 'type',
							title : '类型',
							width : 40,
							align : 'center',
							formatter : function(val,rec) {
								switch (val) {
								 	case 'hd':return "高清";
								 	case 'sd':return "标清";
								}
							}
						},
						{
							field : 'checkState',
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
							field : 'opt',
							title : '操作',
							width : 80,
							align : 'center',
							formatter : function(value,rec,index) {
								return '<a href="#" onclick=checkAreaMsg("'+rec.areaNo+'","'+rec.type+'","navigationCheckNum")><font color="green">审核</font></a>';
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
	});	
</script>
	</head>		
	<body>
		<DIV id=tb class=searchBox>
			<TABLE>
				<TBODY>
					<tr>
						<TH width=30 align="left">
							类型
						</TH>
						<TD width=70 align="left">
							<select id="typeSearch" name="type" style="color:blue;width:80px" >
								<option value="">全部</option>
								<option value="hd">高清</option>
								<option value="sd">标清</option>
							</select> 
						</TD>
						<TH width=30 align="left">
							审核状态
						</TH>
						<TD width=70 align="left">
							<select id="stateSearch" name="state">
								<option value="">全部</option>
								<option value="待审核">待审核</option>
								<option value="审核通过">审核通过</option>
								<option value="审核不通过">审核不通过</option>
							</select> 
						</TD>
						
						<td width=60 align="center">
							<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"
							onclick="searchList()">查询</a>
													</TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
		<!-- 数据处理 -->
		<table id="tt" >
		</table>
	</body>
</html>