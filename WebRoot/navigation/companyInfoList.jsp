<%@page import="cn.eainfo.system.po.SysRole"%>
<%@page import="cn.eainfo.system.po.SysArea"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="utf-8"%>
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
			"districtName":$("#districtNameSearch").val(),
			"areaNo":$("#areaNos").combobox('getValue')
		});
	}
	/**
	*删除
	 */
	function deleteCompanyInfo(companyInfoID) {
		$.messager.confirm('删除', '确认删除该服务器信息吗?', function(r) {
			if (r) {
				$.tools.post("companyInfo!ajaxDelete", {
					id : companyInfoID
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

	function insertCompanyInfoOpen() {
		$("#companyInfoFormDialog").panel({
			title : "公司信息新增"
		});
		$('#companyInfoForm').form('clear');
		$('#companyInfoFormDialog').window('open');
	}
	function editCompanyInfo(id){
		$("#companyInfoFormDialog").panel({
			title : "公司信息修改"
		});
		$('#companyInfoForm').form('clear');
		$('#companyInfoForm').form('load','companyInfo!ajaxGetBean?id=' + id + "&_rnd="	+ Math.random(10000000000));
		$('#companyInfoFormDialog').window('open');
	}
	//flag:俩中保存方式一种是保存，一种是提交审核
	function saveOrUpdateCompanyInfo() {
		$('#companyInfoForm').submit();
	}
	$(document).ready(function() {
					$('#tt').datagrid({	
					    title : ' 公司信息管理',
						iconCls : 'icon-save',
						pagination : true,
						toolbar : "#tb",
						fit : true,
						fitColumns : true,
						singleSelect : true,
						method : 'post',
						remoteSort : true,
						url : 'companyInfo!ajaxList?_rnd='
									+ Math.random(10000000000),
						nowrap : false,
						striped : true,
						 
						remoteSort : false,
						queryParams : {
							"districtName":$("#districtNameSearch").val(),
							"areaNo":$("#areaNos").combobox('getValue')
						},
						/**数据格式定义**/
						columns : [ [
							
								{
									field : 'districtName',
									title : '分公司名称',
									width : 40
								},
								{
									field : 'districtCode',
									title : '分公司编码',
									width : 40
								},
								{
									field : 'cityEnName',
									title : '地区名称',
									width : 40
								},
								{
									field : 'opt',
									title : '操作',
									width : 40,
									align : 'center',
									formatter : function(value,
											rec,index) {
										return '<a href="#" onclick=deleteCompanyInfo('+rec.id+')> 删除</a><a href="#" onclick=editCompanyInfo('+rec.id+')> 修改</a>';
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
		$('#companyInfoForm').form(
				{
					url : 'companyInfo!ajaxSaveOrUpdate?_rnd=' + Math.random(10000000000),
					onSubmit : function() {
						var districtName = $("#districtNameUpdate").val();
						if (districtName == '') {
						    $.messager.alert('消息提醒', "请输入分公司名称！", 'warning');
							$("#districtNameUpdate").focus();
							return false;
						}
						var areaNo = $("#areaNo").combobox('getValue')	;
						if(areaNo==""||areaNo=="00"){
							 $.messager.alert('消息提醒', "请选择所在地区且必须精确到市！", 'warning');
									$("#areaNos").focus();
									return false;
						}		
						var districtCode = $("#districtCodeUpdate").val();
						if (districtName == '') {
						    $.messager.alert('消息提醒', "请输入分公司编码！", 'warning');
							$("#districtCodeUpdate").focus();
							return false;
						}			
											
					},
					onLoadSuccess : function(data) {
						//处理出现错误和会话超时的问题
						if (undefined != data.result && data.result == 'faild') {
							$('#companyInfoFormDialog').window('close');
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
							$('#companyInfoFormDialog').window('close');
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
							分公司名称
						</TH>
						<TD align="left"  width=70>
							<input name="districtName" id="districtNameSearch">
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
					plain="true" onclick="insertCompanyInfoOpen()">新增</a>
				</div>
			</div>
			
			</DIV>
		<!-- 数据处理 -->
		<div style="display:none;">
		<div id="companyInfoFormDialog" class="easyui-dialog" modal="true"
			style="padding: 5px; width: 820px; height: 560px;" closed="true"
			title="" iconCls="icon-ok" toolbar="#dlg-toolbar"
			buttons="#dlg-buttons">
			<div id="dlg-toolbar" style="padding: 5px; height: 26px;">
			
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-cancel"
					onclick="javascript:$('#companyInfoFormDialog').window('close');">取消</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="saveOrUpdateCompanyInfo()">保存</a>
			</div>
			<div class="form-data">
				<form id="companyInfoForm" action="" method="post" >
					<table>
						<tr>
							<th align="right">
								  分公司名称
								<font color="red">*</font>
							</th>
							<td>
								<input name="id" id="idUpdate" type="hidden" >
								<input name="districtName" id="districtNameUpdate" class="text" size="20">
								
							</td>
							<th align="right">
								  分公司编号
								<font color="red">*</font>
							</th>
							<td>
								<input name="districtCode" id="districtCodeUpdate" class="text" size="20">
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