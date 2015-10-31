function getUserCity(cardNo){
	var dataInfo = {"cardNo":cardNo};
	$.ajax({
		async:false,
		type: "post",
		data: dataInfo,
		url: "localhost:8080/Navigation1.0/navigation/customerInfo!getUserCityName",
		dataType: "json",
		success: function(data) {
			if (data.result != 'success') {
				$.messager.alert('消息提醒', data.resume, 'warning');
			} 
		}
	});
}