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
			"type":$("#typeSearch").val(),
			"name"	:$("#nameSearch").val(),
			"areaNo":$("#areaNos").combobox('getValue')
		});
	}
	/**
	*删除
	 */
	function deleteRelease(releaseID) {
		$.messager.confirm('删除', '确认删除该服务器信息吗?', function(r) {
			if (r) {
				$.tools.post("release!ajaxDelete", {
					id : releaseID
				}, function(data) {
					if (data.result != 'sucess') {
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

	function insertReleaseOpen() {
		$("#releaseFormDialog").panel({
			title : "服务器信息新增"
		});
		$('#releaseForm').form('clear');
		$('#releaseFormDialog').window('open');
	}
	//flag:俩中保存方式一种是保存，一种是提交审核
	function saveOrUpdateRelease() {
	
		$('#releaseForm').submit();
	}
	$(document).ready(function() {
					$('#tt').datagrid({	
					    title : '快捷键管理',
						iconCls : 'icon-save',
						pagination : true,
						toolbar : "#tb",
						fit : true,
						fitColumns : true,
						singleSelect : true,
						method : 'post',
						remoteSort : true,
						url : 'release!ajaxList?_rnd='
									+ Math.random(10000000000),
						nowrap : false,
						striped : true,
						 
						remoteSort : false,
						queryParams : {
							"type":$("#typeSearch").val(),
							"name":$("#nameSearch").val(),
							"areaNo":$("#areaNos").combobox('getValue')
						},
						/**数据格式定义**/
						columns : [ [
							
								{
									field : 'name',
									title : '服务器名称',
									width : 40
								},
								{
									field : 'ip',
									title : '地址',
									width : 40
								},
								{
									field : 'port',
									title : '端口',
									width : 80
								},
								{
									field : 'userName',
									title : '登录名',
									width : 40	
								},
								{
									field : 'pass',
									title : '密码',
									width : 40	
								},
								{
									field : 'address',
									title : '原路径',
									width : 40	
								},
								{
									field : 'url',
									title : '正式库路径',
									width : 40	
								},
								{
									field : 'areaNo',
									title : '地区编码',
									width : 40	
								},
								{
									field : 'opt',
									title : '操作',
									width : 40,
									align : 'center',
									formatter : function(value,
											rec,index) {
										return '<a href="#" onclick=deleteRelease('+rec.id+')> 删除</a>';
									}
								}
								
								] ],
						/**数据格式定义**/
						pageSize : 10,
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
		$('#releaseForm').form(
				{
					url : 'release!ajaxSaveOrUpdate?_rnd=' + Math.random(10000000000),
					onSubmit : function() {
						var namena = $("#nameUpdate").val();
									if (namena == '') {
									    $.messager.alert('消息提醒', "请输入服务器名称！", 'warning');
										$("#namenaUpdate").focus();
										return false;
									}
									var ip = $("#ipUpdate").val();
									if (ip == '') {
									$.messager.alert('消息提醒', "请输服务器地址！", 'warning');
										$("#urlUpdate").focus();
										return false;
									}
									var port = $("#portUpdate").val();
									if (port=='') {
									$.messager.alert('消息提醒',"请填写端口！", 'warning');
										return false;
									}
									var userName = $("#userNameUpdate").val();
									if(userName==''){
										$.messager.alert('消息提醒',"请填写用户名！", 'warning');
										return false;
									}
									var pass = $("#passUpdate").val();
									if(pass==''){
										$.messager.alert('消息提醒',"请填写密码！", 'warning');
										return false;
									}
									var address = $("#addressUpdate").val();
									if(address==''){
										$.messager.alert('消息提醒',"请填写原路径！", 'warning');
										return false;
									}
									var url = $("#urlUpdate").val();
									if(url==''){
										$.messager.alert('消息提醒',"请填写服务器路径！", 'warning');
										return false;
									}
									
											
					},
					onLoadSuccess : function(data) {
						//处理出现错误和会话超时的问题
						if (undefined != data.result && data.result == 'faild') {
							$('#navigationFormDialog').window('close');
							$.messager.alert('消息提醒',data.resume, 'warning');
						}
					},
					success : function(data) {
						var returndata = $.parseJSON(data);
						// 还需要判断对象
						if (returndata.result == 'faild') {
							$.messager.alert('消息提醒',returndata.resume,'warning');
						} else {
							$.messager.alert('消息提醒','更新成功','warning');
							$('#releaseFormDialog').window('close');
							searchList();
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
						<TH width=55 align="left">
							地区
						</TH>
						<TD align="left"  width=70>
							${areaStrings }
						</TD>
						<TH width=55 align="left">
							类型
						</TH>
						<TD align="left"  width=70>
							<select name="type" id="typeSearch">
								<option value="">请选择</option>
								<option value="0">测试服务器</option>
								<option value="1">正式服务器</option>
							</select>
						</TD>
						<TH width=55 align="left">
							名称
						</TH>
						<TD align="left"  width=70>
							<input id="nameSearch" name="name" type="text">
						</TD>
						<td width=80 align="center">
							<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'"
							onclick="searchList()">查询</a>
													</TD>
				</TR>
				</TBODY>
			</TABLE>
			<div id="tb" class="grid-option">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="insertReleaseOpen()">新增</a>
				</div>
			</div>
			
			</DIV>
		<!-- 数据处理 -->
		<div style="display:none;">
		<div id="releaseFormDialog" class="easyui-dialog" modal="true"
			style="padding: 5px; width: 820px; height: 560px;" closed="true"
			title="" iconCls="icon-ok" toolbar="#dlg-toolbar"
			buttons="#dlg-buttons">
			<div id="dlg-toolbar" style="padding: 5px; height: 26px;">
			
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-cancel"
					onclick="javascript:$('#releaseFormDialog').window('close');">取消</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="saveOrUpdateRelease()">保存</a>
			</div>
			<div class="form-data">
				<form id="releaseForm" action="" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th align="right">
								名称
								<font color="red">*</font>
							</th>
							<td
								<input name="id" id="idUpdate"  type="hidden">
								<input name="name" id="nameUpdate" class="text" size="20">
							</td>
							<th align="right">
								主机地址
								<font color="red">*</font>
							</th>
							<td>
								<input name="ip" id="ipUpdate" class="text" size="20">
							</td>
						</tr>
						<tr>
							<th align="right">
								端口
								<font color="red">*</font>
							</th>
							
							<td colspan="">
								<input name="port" id="portUpdate" type="text" class="text" size="21"><font color="red"></font>
							</td>
							
							<th align="right">
								登录名
								<font color="red">*</font>
							</th>
							
							<td colspan="">
								<input name="userName" id="userNameUpdate" type="text" class="text" size="21"><font color="red"></font>
							</td>
						</tr>
						<tr>
							<th align="right">
								登录密码
								<font color="red">*</font>
							</th>
							<td >
								<input name="pass" id="passUpdate" type="text" class="text" size="21"><font color="red"></font>
							</td>
							<th align="right">
								源地址
								<font color="red">*</font>
							</th>
							<td >
								<input name="address" id="addressUpdate" type="text" class="text" size="21"><font color="red"></font>
							</td>
						</tr>
						<tr>
							<th align="right">
								正式库路径
								<font color="red">*</font>
							</th>
							<td >
								<input name="url" id="urlUpdate" type="text" class="text" size="21"><font color="red"></font>
							</td>
							<th align="type">
								类型
								<font color="red">*</font>
							</th>
							<td >
								<select name="type" id="typeUpdate">
									<option value="0">测试服务器</option>
									<option value="1">正式服务器</option>
								</select>
							</td>
						</tr>
							<tr>
							<th align="right">
								地区
								<font color="red">*</font>
							</th>
							<td >
								${areaString }
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