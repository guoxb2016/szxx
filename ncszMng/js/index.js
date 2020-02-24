/**
 * index js
 */
$(function () {
    //判断是否登录过
    var storage = window.localStorage;
    var token = storage['token'];
    //根据token获取权限
   $.ajax({
		url: config.weburl + '/sys/permission/getUserPermissionByToken',
		dataType: 'json',
		type: 'GET',
		data: {
			"token": token
		},
		success: function (ret) {
			if (ret.success) {
				//根据权限进行显影
				$('.media_item').each(function () {
					var $data = $(this).attr('data');
					for(var i in ret.result.menu){
						if("phone" == ret.result.menu[i].component){
							for(var j in ret.result.menu[i].children){
								if($data == ret.result.menu[i].children[j].component){
									$(this).css('display','flex')
								}
							}
						}
					}
				});
			} else {
				window.location.href = 'login.html';
			}
		}
	});
});