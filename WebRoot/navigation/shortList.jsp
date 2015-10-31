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
	var areaNo = "${areaNo}";
	var overallKeyValue="";
	function searchList() {
		$('#tt').datagrid('load', {
			"type":$("#typeSearch").val(),
			"areaNo" : $("#areaNos").combobox('getValue'),
			"checkState" : $("#checkStateSearch").val()	
		});
	}
	/*
	 *修改
	*/
	function editShort(shortID,shortType){
		$("#typeUpdate").val(shortType);
		if(shortType=="sd"){
			$("#tr-sd").show();
			$("#tr-hd").hide();
		}else{
			$("#tr-sd").hide();
			$("#tr-hd").show();
		}
		//隐藏图片的输入框
		$("#iconUpdate").show();
		$("#shortFormDialog").panel({
			title : "快捷键信息信息修改"
		});
		$('#shortForm').form('clear');
		$('#shortForm').form('load','short!ajaxGetBean?id=' + shortID + "&_rnd="+ Math.random(10000000000));
		$('#shortForm').form('load',{//加载本地记录
				shortID:'name2',
				email:'mymail@gmail.com',
				subject:'subject2',
			message:'message2',
			language:5
		});
		//每次修改时记录当前快捷键的值
		$('#shortFormDialog').window('open');
		//因为‘load’异步取得数据这是设置了延迟加载数据单位1秒
		window.setTimeout("saveOriginalKeyValue()", 1000);    
	}
	function saveOriginalKeyValue(){
		overallKeyValue = $('#keyUpdate').val();
	}
	/**
	删除
	 */
	function deleteShort(shortID) {
		$.messager.confirm('删除', '确认删除该快捷键吗?', function(r) {
			if (r) {
				$.tools.post("short!ajaxDelete", {
					id : shortID
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

	function insertShortOpen() {
		//隐藏图片输入框
		$("#iconUpdate").hide();
		$("#shortFormDialog").panel({
			title : "快捷键信息信息新增"
		});
		$('#shortForm').form('clear');
		$('#areaNo').combobox('select',areaNo);
		$('#shortFormDialog').window('open');
	}
	//flag:俩中保存方式一种是保存，一种是提交审核
	function saveOrUpdateShort() {
	
		$('#shortForm').submit();
	}
	$(document).ready(function() {
					$('#tt').datagrid({	
						onDblClickRow:function(index,row){
							editShort(row.id,row.type);
						},	
					    title : '快捷键管理',
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
							"type":'hd',
							"areaNo" : $("#areaNos").combobox('getValue')
						},
						/**数据格式定义**/
						columns : [ [
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
									field : 'cityEnName',
									title : '地区名称',
									width : 40
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
									width : 20,
									align : 'center',
									formatter : function(value,
											rec) {
											var str1='<img width="60" height="40" src=';
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
									width : 30,
									align : 'center',
									formatter : function(value,
											rec,index) {
										return '<a href="#" onclick=editShort('+rec.id+',"'+rec.type+'")>修改</a><a href="#" onclick=deleteShort('+rec.id+')> 删除</a>';
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
			定义表单数据
			 */
		$('#shortForm').form(
				{
					url : 'short!ajaxSaveOrUpdate?_rnd=' + Math.random(10000000000),
					onSubmit : function() {
						var namena = $("#namenaUpdate").val();
									if (namena == '') {
									    $.messager.alert('消息提醒', "请输入快捷键名称！", 'warning');
										$("#namenaUpdate").focus();
										return false;
									}
									var type = $("#typeUpdate").val();
									if(type==""){
										$.messager.alert('消息提醒',"请填写快捷键类型！", 'warning');
										return false;
									}
									if($("#areaNo").combobox('getValue')=='00'){
										$.messager.alert('消息提醒', '请选择具体某个市', 'warning');
										return false;
									}
									if($("#areaNo").combobox('getValue')==''){
										$.messager.alert('消息提醒', '请选择所属地区', 'warning');
										return false;
									}
									
									var key = $("#keyUpdate").val();
									
									var tempFlag = false;
									if (key=='') {
										$.messager.alert('消息提醒',"请填写快捷键所在位置！", 'warning');
										return false;
									}

									if(key!=overallKeyValue){
										var keyIsExist =  keyValueIsExist($("#areaNo").combobox('getValue'),type,key)
										if(keyIsExist=='1'){
											$.messager.alert('消息提醒',"当前快捷键位置已存在", 'warning');
											tempFlag = true;
										}
									}
									if(tempFlag){
										return false;
									}
									var url = $("#urlUpdate").val();
									if (url == ''&&key!="4") {
										$.messager.alert('消息提醒', "请输入快捷键链接地址！", 'warning');
										$("#urlUpdate").focus();
										return false;
									}
									var icon = $("#iconUpdate").val();
									var iconFile = $("iconFileUpdate").val();
									if(icon==''&&iconFile==''){
										$.messager.alert('消息提醒',"请填写快捷键图片！", 'warning');
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
							$('#shortFormDialog').window('close');
							searchList();
						}
					}
				});
			});
	function typeSelectChange(){
		var typeValue = $("#typeUpdate").val();
		if(typeValue=="sd"){
			$("#tr-sd").show();
			$("#tr-hd").hide();
		}else{
			$("#tr-sd").hide();
			$("#tr-hd").show();
		}
	}
	
	function keyValueIsExist(areaNo,type,keyValue){
		var value = "0";
		var dataInfo = {"areaNo":areaNo,"type":type,"key":keyValue};
		$.ajax({
			async:false,
			type: "post",
			data: dataInfo,
			url: "short!keyIsExist",
			dataType: "json",
			success: function(data) {
				if (data.result != 'success') {
					$.messager.alert('消息提醒', data.resume, 'warning');
					maxLocationNum = "系统异常";
				} else {
					value = data.resume;
				}
			}
		});
		return value;
	}
	
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
						<TH width=20 align="left">
							审核状态
						</TH>
						<td width=40>
							<select id="checkStateSearch" name="checkState">
								<option value="">全部</option>
								<option value="待审核">待审核</option>
								<option value="审核通过">审核通过</option>
								<option value="审核不通过">审核不通过</option>
							</select> 
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
					plain="true" onclick="insertShortOpen()">新增</a>
				</div>
			</div>
			
			</DIV>
		<!-- 数据处理 -->
		<div style="display:none;">
		<div id="shortFormDialog" class="easyui-dialog" modal="true"
			style="padding: 5px; width: 820px; height: 560px;" closed="true"
			title="" iconCls="icon-ok" toolbar="#dlg-toolbar"
			buttons="#dlg-buttons">
			<div id="dlg-toolbar" style="padding: 5px; height: 26px;">
			
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-cancel"
					onclick="javascript:$('#shortFormDialog').window('close');">取消</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="saveOrUpdateShort()">保存</a>
			</div>
			<div class="form-data">
				<form id="shortForm" action="" method="post" enctype="multipart/form-data" >
					<table>
						<tr>
							<th align="right">
								快捷键名称
								<font color="red">*</font>
							</th>
							<td>
								<input name="id" id="idUpdate"  type="hidden">
								<input name="namena" id="namenaUpdate" class="text" size="20">
							</td>
							<th align="right">
								类型
								<font color="red">*</font>
							</th>
							<td>
								<select name="type" id="typeUpdate" onchange="typeSelectChange()">
									<option value="sd">标清</option>
									<option value="hd">高清</option>
								</select> 
							</td>
						</tr>
						<tr>
							<th align="right">
								跳转链接
								<font color="red"></font>
							</th>
							
							<td colspan="3">
								<input name="url" id="urlUpdate" type="text" class="text" size="63"><font color="red"></font>
							</td>
							
							
						</tr>
						<tr>
							<th align="right">
								快捷键
								<font color="red">*</font>
							</th>
							
							<td colspan="3">
								<input  name="key" id="keyUpdate" type="text" class="text" size="21">&nbsp;&nbsp;&nbsp;<font color="red">广告位的快捷键值为：4</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								图标
								<font color="red">*</font>
							</th>
							<td >
								<input name="icon" id="iconUpdate" type="text"  class="text" size="30"><font color="red"></font>
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
								请选择文件
								<font color="red">*</font>
							</th>
							<td >
								<input name="iconFile" id="iconFileUpdate" type="file"  size="21"><font color="red"></font>
							</td>
						</tr>	
						<tr id="tr-hd">
							<td align="right">
								高清：
							</td>
							<th>
								<span style="color:red">宽: 186,高：106&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png</span>  
							</th>
							<td align="right">
								高清：
							</td>
							<th>
								<span style="color:red">宽: 186,高：106&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png</span>  
							</th>
						</tr>
						<tr id="tr-sd">
							<td align="right">
								标清：
							</td>
							<th>
								<span style="color:red">宽: 120,高：68&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png</span>  
							</th>
							<td align="right">
								标清：
							</td>
							<th>
								<span style="color:red">宽 ： 120,高：68&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png</span>  
							</th>
							
						</tr>
						<tr>
							<td align="right">
								广告位：
							</td>
							<th>
								<span style="color:red">长与宽不做限制 </span>
							</th>
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