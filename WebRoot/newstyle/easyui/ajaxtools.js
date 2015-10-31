(function($) {
	$.tools = {
		post : function(url, data, _success) {
			url.indexOf('?') > -1 ? url + "&_rnd=" + Math.random(10000000000)
					: url + "?_rnd=" + Math.random(10000000000);
			$.post(url, data, function(data) {
				var returndata = $.parseJSON(data);
				// 还需要判断对象
				if (returndata.result == 'faild') {
					$.messager.alert('消息提醒', returndata.resume, 'warning');
				} else {
					_success(returndata);
				}
			});
		},
		get : function(url, data, _success) {
			url.indexOf('?') > -1 ? url + "&_rnd=" + Math.random(10000000000)
					: url + "?_rnd=" + Math.random(10000000000);
			$.get(url + "?_rnd=" + Math.random(10000000000), data, function(
					data) {
				var returndata = $.parseJSON(data);
				// 还需要判断对象
				if (returndata.result == 'faild') {
					$.messager.alert('消息提醒', returndata.resume, 'warning');
				} else {
					_success(returndata);
				}
			});
		}
	};
})(jQuery);