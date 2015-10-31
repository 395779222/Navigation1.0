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
	//新增时默认选中该用户的所属地区
	var areaNo="${areaNo}"
	/*调整位置*/
	function exportExcel(){
		if($("#areaNos").combobox('getValue')=='00'){
			$.messager.alert('消息提醒', '请选择具体某个市', 'warning');
			return false;
		}
		if($("#typeSearch").val()==''){
			$.messager.alert('消息提醒', '请选择类型', 'warning');
			return false;
		}
		window.location.href="navigation!navigationExport?type="+$("#typeSearch").val()+"&areaNo="+$("#areaNos").combobox('getValue');
	}
	function uploadExcel(){
		$('#upExcelForm').form('clear');
		$('#uploadWin').window('open');
	}
	function uploadExcelSubmit(){
		$('#upExcelForm').submit();
	}
	function searchList() {
		$('#tt').datagrid('load', {
			"namena" : $("#namenaSearch").val(),
			"type":$("#typeSearch").val(),
			"num":$("#numSearch").val(),
			"areaNo" : $("#areaNos").combobox('getValue')	
		});
	}
	/**
	删除
	 */
	function deleteNavigation(navigationID) {
		$.messager.confirm('删除', '确认删除该导航吗?', function(r) {
			if (r) {
				$.tools.post("navigation!ajaxDelete", {
					id : navigationID
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

	function insertNavigationOpen() {
		$("#navigationFormDialog").panel({
			title : "导航信息新增"
		});
		$('#navigationForm').form('clear');
		$('#areaNo').combobox('select',areaNo);
		$('#iconUpdate').hide();
		$('#icon2Update').hide();
		$('#navigationFormDialog').window('open');
	}
	//flag:俩中保存方式一种是保存，一种是提交审核
	function saveOrUpdateNavigation(flag) {
	//将俩种方式转化为不同的导航状态：待审核，与保存
		$("#stateUpdate").val(flag);
		$('#navigationForm').submit();
	}
	function edit(id,type){
		$("#typeUpdate").val(type);
		if(type=="sd"){
			$("#tr-sd").show();
			$("#tr-hd").hide();
		}else{
			$("#tr-sd").hide();
			$("#tr-hd").show();
		}
		$("#navigationFormDialog").panel({title : "导航信息修改"});
		$('#navigationForm').form('clear');
		$('#navigationForm').form('load','navigation!ajaxGetBean?id=' + id + "&_rnd="	+ Math.random(10000000000));
		$('#iconUpdate').show();
		$('#icon2Update').show();
		$('#navigationFormDialog').window('open');
	}
	function getMaxLoaction(areaNo,type,location){
		var maxLocationNum = 0;
		var dataInfo = {"areaNo":areaNo,"type":type,"location":location};
		$.ajax({
			async:false,
			type: "post",
			data: dataInfo,
			url: "navigation!getMaxLoaction",
			dataType: "json",
			success: function(data) {
				if (data.result != 'success') {
					$.messager.alert('消息提醒', data.resume, 'warning');
					maxLocationNum = "系统异常";
				} else {
					maxLocationNum = parseInt(data.resume);
				}
			}
		});
		return maxLocationNum;
	}
	$(document).ready(function() {
		$('#tt').datagrid({	
		onDblClickRow:function(index,row){
				edit(row.id,row.type);
			},	
		    title : '导航管理管理',
			iconCls : 'icon-save',
			pagination : true,
			toolbar : "#tb",
			fit : true,
			fitColumns : true,
			singleSelect : true,
			method : 'post',
			remoteSort : true,
			url : 'navigation!ajaxList?_rnd='
						+ Math.random(10000000000),
			nowrap : false,
			striped : true,
			 
			remoteSort : false,
			queryParams : {
				"namena" : $("#namenaSearch").val(),
				"type":'hd',
				"num":$("#numSearch").val(),
				"areaNo" : $("#areaNos").combobox('getValue')
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
						/*formatter : function(value,rec) {
							var str1='<input  type="text"';
								 str1=str1+'value="'+value;
								 str1=str1+'"/>';
							return str1;
						}*/
					},
					{
						field : 'location',
						title : '所在位置',
						width : 30,
						/*formatter : function(value,rec) {
							var str1='<input  type="text"';
								 str1=str1+'value="'+value;
								 str1=str1+'"/>';
							return str1;
						}*/
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
						field : 'domain',
						title : '域名',
						width : 40	
					},
					{
						field : 'redirect',
						title : '实际地址',
						width : 60
					},
					{
						field : 'icon',
						title : '未选中图标',
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
						field : 'icon2',
						title : '选中图标',
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
						field : 'state',
						title : '状态',
						width : 40,
						formatter : function(val,rec) {
							if(val=="审核通过"){
								return "<span style=\"color:green\">"+val+"</span>";
							}if(val=="待审核"||val=="审核不通过"){
								return  "<span style=\"color:red\">"+val+"</span>";
							}else{
								return  "<span>"+val+"</span>";
							}
						}	
					},
					{
						field : 'updateTime',
						title : '更新时间',
						width : 40
					},
					{
						field : 'opt',
						title : '操作',
						width : 40,
						align : 'center',
						formatter : function(value,
								rec,index) {
							return '<a href="#" onclick=deleteNavigation('+rec.id+')> 删除</a><a href="#" onclick=edit('+rec.id+',"'+rec.type+'")> 修改</a>';
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
		
		$('#upExcelForm').form({  
            url:"navigation!upLoadExcel",  
        	onSubmit: function(){  
               var excelName = $("#uploadExcel").val();
               if(excelName==""){
               		$.messager.alert('消息提醒',"请选择将要导入的excel！", 'warning');
					return false;
               }
        	},  
        	success:function(data){  
        		var returndata = $.parseJSON(data);
                if (returndata.result == 'faild') {
						$.messager.alert('消息提醒',returndata.resume,'warning');
				}else {
					$.messager.alert('消息提醒','上传成功','warning');
					$('#uploadWin').window('close');
					searchList();
				}  
        	}  
    	});  
		//上传文件表单提交验证
		/**
			定义表单数据
			 */
		$('#navigationForm').form(
				{
					url : 'navigation!ajaxSaveOrUpdate?_rnd=' + Math.random(10000000000),
					onSubmit : function() {
						var areaNo = $("#areaNo").combobox('getValue');
						var namena = $("#namenaUpdate").val();
						if (namena == '') {
						    $.messager.alert('消息提醒', "请输入导航名称！", 'warning');
							$("#namenaUpdate").focus();
							return false;
						}
						var url = $("#urlUpdate").val();
						if (url == '') {
						$.messager.alert('消息提醒', "请输入导航链接地址！", 'warning');
							$("#urlUpdate").focus();
							return false;
						}
						var num = $("#numUpdate").val();
						if(!(num>0&&num<100)){
							$.messager.alert('消息提醒',"请正确填写导航所在页面！", 'warning');
							return false;
						}
						var type = $("#typeUpdate").val();
						 if (type==''||type==null) {
							$.messager.alert('消息提醒',"请选择导航类型！", 'warning');
							return false;
						} 
						//若为空则
						var location = $("#locationUpdate").val();
						var maxLocationFlag = false;
						if (location=='') {
							$.messager.alert('消息提醒',"请填写导航所在位置！", 'warning');
							return false;
						}
						else{
							var maxLocationNum = getMaxLoaction(areaNo,type,location);
							if(maxLocationNum=="系统异常"){
								maxLocationFlag = true;
							}
							else if(parseInt(location)>(parseInt(maxLocationNum)+1)){
								if(maxLocationNum==0||maxLocationNum=="0"){
									maxLocationNum=1;
								}
								if(!window.confirm('当前导航位置大于位置：'+maxLocationNum+'确定提交？')){
                 					maxLocationFlag = true;
            					 }
							}
						}
						if(maxLocationFlag){
							return false;
						}
						//若不是数字
						if(!(/^[0-9]{1,2}$/.test(location))){
							$.messager.alert('消息提醒',"请以正确的形式填写导航所在位置！", 'warning');
							return false;
						}
						
						 if(location<15*(num-1)+1||location>15*num){
							$.messager.alert('消息提醒',"导航所在位置与所在页面不符！", 'warning');
							return false;
						} 
						var icon1FileName = $("#icon1File").val();
						var icon2FileName = $("#icon2File").val();				
						var tempFlag = ((icon1FileName==''&&icon2FileName!='')||(icon1FileName!=''&&icon2FileName==''));
						//若是修改，只要改一张图片，另一张也要改(俩张图片是有关联的)
						if($("#iconUpdate").val()!=''&&$("#iconUpdate").val()!=null&&tempFlag){
							$.messager.alert('消息提醒','存在图片未选择!', 'warning');
							return false;
						}
						//若是新增则必须上传俩张图片
						if(($("#iconUpdate").val()==''||$("#iconUpdate").val()==null)&&(icon1FileName==''||icon2FileName=='')){
							$.messager.alert('消息提醒','存在图片未选择!', 'warning');
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
							$('#navigationFormDialog').window('close');
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
		function emergency(){
			var type = $("#typeUpdate").val();
		    if (type==''||type==null) {
				$.messager.alert('消息提醒',"请选择导航类型！", 'warning');
				return false;
			} 
			if($("#areaNo").combobox('getValue')=='00'){
				$.messager.alert('消息提醒', '请选择具体某个市', 'warning');
				return false;
			}
			var emergencyRedirect="";
			$.messager.confirm('备份', '将会覆盖原先地址,确定使用应急地址？', function(r) {
				if (r) {	
					var dataInfo = {
						"type":type,
						"areaNo":$("#areaNo").combobox('getValue')
					};
					$.ajax({
						async:false,
						type: "post",
						data: dataInfo,
						url: "emergency!ajaxGetEmergencyRedirect",
						dataType: "json",
						success: function(data) {
							if (data.result != 'success') {
								$.messager.alert('消息提醒', data.resume, 'warning');
								emergencyRedirect = "系统异常";
							} else {
								emergencyRedirect = data.resume;
								$("#redirectUpdate").val(emergencyRedirect);
							}
						}
					});
					
				}
				
			});	
		}
	function pagePreLook(){
		var type = $("#typeSearch").val();
	    if (type==''||type==null) {
			$.messager.alert('消息提醒',"请选择导航类型！", 'warning');
			return false;
		} 
		if($("#areaNos").combobox('getValue')=='00'){
			$.messager.alert('消息提醒', '请选择具体某个市', 'warning');
			return false;
		}
		window.open('getNavigation!getPreLookNavigation?areaNo='+$("#areaNos").combobox('getValue')+'&type='+type,'','height=800,width=1024,scrollbars=yes,status =yes');
		
	}
	
</script>
	</head>		
		<body>
		<DIV id=tb class=searchBox>
			<TABLE>
				<TBODY>
				<tr>
				<TH width=55 align="left">
							类型
						</TH>
						<TD align="left"  width=70>
							<select id="typeSearch" name="type" style="color:blue">
								<option value="">全部</option>
								<option value="sd">标清</option>
								<option value="hd" selected="selected">高清</option>
							</select> 
						</TD>
				<th width=60>
								所属地区
							</th>
							<td width=60>
								${areaStrings }
							</td>
				<TH width=40 align="left">
							业务名称
						</TH>
						<TD width=70 align="left">
							<input type="text" size="15" class="text" name="namena"
								id="namenaSearch" value="">
						</TD>
						<TH width=55 align="left">
							所在页面
						</TH>
						<TD width=70 align="left">
							<input type="text" size="15" class="text" name="num"
								id="numSearch" value="">
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
					plain="true" onclick="insertNavigationOpen()">新增</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				plain="true" onclick="uploadExcel()">导入</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				plain="true" onclick="exportExcel()">导出</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
				plain="true" onclick="pagePreLook()">导航页面预览</a>
				</div>
			</div>
			
			</DIV>
		<!-- 数据处理 -->
		<div style="display:none;">
		<div id="navigationFormDialog" class="easyui-dialog" modal="true"
			style="padding: 5px; width: 820px; height: 560px;" closed="true"
			title="" iconCls="icon-ok" toolbar="#dlg-toolbar"
			buttons="#dlg-buttons">
			<div id="dlg-toolbar" style="padding: 5px; height: 26px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="saveOrUpdateNavigation('待审核')">提交审核</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-cancel"
					onclick="javascript:$('#navigationFormDialog').window('close');">取消</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="saveOrUpdateNavigation('保存')">保存</a>
			</div>
			<div class="form-data">
				<form id="navigationForm" action="" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th align="right">
								业务名称
								<font color="red">*</font>
							</th>
							<td>
								<input name="state" id="stateUpdate"  type="hidden">
								<input name="updateFlag" id="updateFlag" type="hidden" value="保存"/>
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
								<font color="red">*</font>
							</th>
							
							<td colspan="">
								<input name="url" id="urlUpdate" type="text" class="text" size="21"><font color="red"></font>
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
							域名
							</th>
							<td colspan="3">
								<input name="domain" id="domainUpdate" type="text" class="text" size="40"><font color="red"></font>
							</td>
						
						</tr>
						<tr>
							<th align="right">
								实际地址
							</th>
							<td colspan="3">
							  <input name="redirect" id="redirectUpdate" type="text" class="text" size="40">
							  <input type="button" value="应急地址" onclick="emergency()"><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*建议在点击前备份</font>
							</td>
						</tr>
							<tr>
							<th align="right">
								所在页面<font color="red">*</font>
							</th>
							<td>
								<input name="num" id="numUpdate" type="text" class="text" size="20">
							</td>
							
							<th align="right">
								所在位置<font color="red">*</font>
							</th>
							<td>
								<input name="location" id="locationUpdate" type="text" class="text" size="20">
							</td>							
							</tr>
						<tr>
						</tr>
							<tr>
							<th align="right">
								未选中图片
							</th>
							<td>
								<input name="icon" readonly="true" id="iconUpdate" type="text" class="text" size="30">
							</td>
							
							<th align="right">
								选中图片<font color="red"></font>
							</th>
							<td>
								<input readonly="true" name="icon2" id="icon2Update" type="text" class="text" size="30">
							</td>							
							</tr>
						<tr>
							<th align="right">
								未选中图片文件<font color="red">*</font>
							</th>
							<td>
								<input type="file" name="uploadFiles" id="icon1File"></input>  
							</td>
							<th align="right">
								选中图片文件<font color="red">*</font>
							</th>
							<td>
								<input type="file" name="uploadFiles" id="icon2File"></input>  
							</td>
						</tr>
						<tr id="tr-hd">
							<td align="right">
								高清：
							</td>
							<th>
								<span style="color:red">宽: 161,高：116&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png,gif</span>  
							</th>
							<td align="right">
								高清：
							</td>
							<th>
								<span style="color:red">宽: 161,高：116&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png,gif</span>  
							</th>
						</tr>
						<tr id="tr-sd">
							<td align="right">
								标清：
							</td>
							<th>
								<span style="color:red">宽: 105,高：75&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png,gif</span>  
							</th>
							<td align="right">
								标清：
							</td>
							<th>
								<span style="color:red">宽: 105,高：75&nbsp;&nbsp;</span><span style="color:red">图片格式：jpg,png,gif</span>  
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
		
     	<div id="uploadWin" class="easyui-window" title="导航信息上传" closed="true" 
     		style="padding: 5px; width: 350px; height: 150px;">
     		<div id="dlg-toolbar" style="padding: 5px; height: 26px;">
     			<a href="#" class="easyui-linkbutton" iconCls="icon-save"
					plain="true" onclick="uploadExcelSubmit()">导入</a>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-cancel"
					onclick="javascript:$('#uploadWin').window('close');">取消</a>
     		</div>
     		<div class="form-data">
     			<form id="upExcelForm" method="POST" enctype="multipart/form-data"> 
	     			<table>
	     				<tr>
	     					<th style="width:90px" align="">请选择文件：</th>
	     					<td><input type="file" name="excelFile" id="uploadExcel"/></td>   
	     				</tr>
	     			</table> 
     			</form>  
     		</div>          
     	</div>  
	</body>
</html>