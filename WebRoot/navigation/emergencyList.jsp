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
	var areaNo="${areaNo}"
	function searchList() {
		$('#tt').datagrid('load', {
			"type":$("#typeSearch").val(),
			"areaNo" : $("#areaNos").combobox('getValue')
		});
	}
	/**
	 *修改
	*/
	function editEmergency(emergencyID,emergencyType){
		$("#emergencyFormDialog").panel({
			title : "快捷键信息信息修改"
		});
		$('#emergencyForm').form('clear');
		$('#emergencyForm').form('load','emergency!ajaxGetBean?id=' + emergencyID + "&_rnd="+ Math.random(10000000000));
		$('#emergencyFormDialog').window('open');
	}
	/**
	删除
	 */
	function deleteEmergency(emergencyID) {
		$.messager.confirm('删除', '确认删除该导航应急吗?', function(r) {
			if (r) {
				$.tools.post("emergency!ajaxDelete", {
					id : emergencyID
				}, function(data) {
					if (data.result != 'success') {
						$.messager.alert('消息提醒', data.resume, 'warning');
					} else {
						searchList();
					}
				});
			}
		});

	}
	/**
	新增/修改角色
	 */

	function inserEmergencyOpen() {
		//隐藏图片输入框
		$("#emergencyFormDialog").panel({
			title : "导航应急信息新增"
		});
		$('#emergencyForm').form('clear');
		$('#areaNo').combobox('select',areaNo);
		$('#emergencyFormDialog').window('open');
	}
	//flag:俩中保存方式一种是保存，一种是提交审核
	function saveOrUpdateEmergency() {
		$('#emergencyForm').submit();
	}
	$(document).ready(function() {
		$('#tt').datagrid({	
			onDblClickRow:function(index,row){
				editEmergency(row.id,row.type);
			},	
		    title : '导航应急信息管理',
			iconCls : 'icon-save',
			pagination : true,
			toolbar : "#tb",
			fit : true,
			fitColumns : true,
			singleSelect : true,
			method : 'post',
			remoteSort : true,
			url : 'emergency!ajaxList?_rnd='
						+ Math.random(10000000000),
			nowrap : false,
			striped : true,
			 
			remoteSort : false,
			queryParams : {
				"type":'hd',
				"areaNo" : $("#areaNos").combobox('getValue')
			},
			/**数据格式定义**/
			columns : [ [
					{
						field : 'cityChName',
						title : '地区',
						width : 40
					},
					{
						field : 'type',
						title : '类型',
						width : 15,
						formatter : function(val,rec) {
							switch (val) {
				 				case 'hd':return '<font color="blue">高清</font>';
				 				case 'sd':return '<font color="blue">标清</font>';
							}
						}
					},
					
					{
						field : 'emergenceyRedirect',
						title : '跳转链接',
						width : 60
					},
					{
						field : 'opt',
						title : '操作',
						width : 30,
						align : 'center',
						formatter : function(value,rec,index) {
							return '<a href="#" onclick=editEmergency('+rec.id+',"'+rec.type+'")>修改</a><a href="#" onclick=deleteEmergency('+rec.id+')> 删除</a>';
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
		//上传文件表单提交验证
		/**
		*定义表单数据
		*/
		$('#emergencyForm').form(
			{
				url : 'emergency!ajaxSaveOrUpdate?_rnd=' + Math.random(10000000000),
				onSubmit : function() {
					if($("#areaNo").combobox('getValue')=='00'){
						$.messager.alert('消息提醒', '请选择具体某个市', 'warning');
						return false;
					}
					if($("#areaNo").combobox('getValue')==''){
						$.messager.alert('消息提醒', '请选择所属地区', 'warning');
						return false;
					}
					var emergenceyRedirect = $("#emergenceyRedirectUpdate").val();
					 if (emergenceyRedirect==''||emergenceyRedirect==null) {
						$.messager.alert('消息提醒',"请选择导航应急信息的实际地址！", 'warning');
						return false;
					} 	
					var type = $("#typeUpdate").val();
					 if (type==''||type==null) {
						$.messager.alert('消息提醒',"请选择导航应急信息类型！", 'warning');
						return false;
					} 			
										
				},
				onLoadSuccess : function(data) {
					//处理出现错误和会话超时的问题
					if (undefined != data.result && data.result == 'faild') {
						$('#emergencyFormDialog').window('close');
						$.messager.alert('消息提醒',data.resume, 'warning');
					}
				},
				success : function(data) {
					var returndata = $.parseJSON(data);
					// 还需要判断对象
					if (returndata.result == 'faild') {
						$.messager.alert('消息提醒',returndata.resume,'warning');
					} else {
						$.messager.alert('消息提醒','导航应急信息更新成功','warning');
						$('#emergencyFormDialog').window('close');
						searchList();
					}
				}
			});
		});
</script>
	</head>		
		<body>
		<DIV id=tb class=searchBox>
			<TABLE width=90>
				<TBODY>
				<tr>
						<TH width=20 align="left">
							类型
						</TH>
						<TD align="left"  width="20">
							<select id="typeSearch" name="type" style="color:blue">
								<option value="">全部</option>
								<option value="sd">标清</option>
								<option value="hd" selected="selected">高清</option>
							</select> 
						</TD>
						<TH width=20 align="left">
							地区
						</TH>
						<td width=40>
								${areaStrings }
						</td>
						<td width=80 align="center">
							<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"
							onclick="searchList()">查询</a>
													</TD>
				</TR>
				</TBODY>
			</TABLE>
			<div id="tb" class="grid-option">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="inserEmergencyOpen()">新增</a>
				</div>
			</div>
			
			</DIV>
		<!-- 数据处理 -->
		<div style="display:none;">
		<div id="emergencyFormDialog" class="easyui-dialog" modal="true"
			style="padding: 5px; width: 820px; height: 560px;" closed="true"
			title="" iconCls="icon-ok" toolbar="#dlg-toolbar"
			buttons="#dlg-buttons">
			<div id="dlg-toolbar" style="padding: 5px; height: 26px;">
			
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-cancel"
					onclick="javascript:$('#emergencyFormDialog').window('close');">取消</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="saveOrUpdateEmergency()">保存</a>
			</div>
			<div class="form-data">
				<form id="emergencyForm" action="" method="post" >
					<table>
						<tr>
							<th align="right">
								类型
								<font color="red">*</font>
							</th>
							<td>
								<input name="id" id="idUpdate"  type="hidden">
								<select name="type" id="typeUpdate" onchange="typeSelectChange()">
									<option value="sd">标清</option>
									<option value="hd">高清</option>
								</select> 
							</td>
							<th align="right">
								所属地区
								<font color="red">*</font>
							</th>
							<td>
								${areaString}<font color="red"></font>
							</td>
						</tr>
						<tr>
							<th align="right">
								实际地址
								<font color="red">*</font>
							</th>
							<td colspan="3">
								 <input name="emergenceyRedirect" id="emergenceyRedirectUpdate" type="text" class="text" size="70"> 
							</td>
						</tr>
					</table>
				</form>
				
			</div>
		</div>
		</div>
		<!-- 数据处理 -->
		<table id="tt" >

		</table>
	</body>
</html>