/**
 * 通用js
 */
//登陆标志
var token = "";
var config = {

		//网站名称 (左上角显示的文字LOGO)
		webname: '南昌市市政信息上报系统',
		weburl : "http://10.10.11.153:8087/jeecg-boot/",
		//layer全局提示层
		layerMsg: {
			offset: 't', //坐标 (详细说明 https://www.layui.com/doc/modules/layer.html#offset)
			shade: [0.4, '#000'] //遮罩 (详细说明 https://www.layui.com/doc/modules/layer.html#shade)
		}
}
$(function(){
	var hrefs = window.location.href.split("/");
	var location = hrefs[hrefs.length-1];
	//判断是否登录过,解决分享无权限的问题
	var storage = window.localStorage;
	token = storage['token'];
	//后台请求token是否有效
	$.ajax({
		url: config.weburl + '/sys/permission/getUserPermissionByToken',
		dataType: 'json',
		type: 'GET',
		data: {
			"token": token
		},
		success: function (ret) {
			if (ret.success) {
				//判断当前路径是否在权限范围内
				var persion = false;
				for(var i in ret.result.menu){
					if("phone" == ret.result.menu[i].component){
						for(var j in ret.result.menu[i].children){
							var componets = ret.result.menu[i].children[j].component.split(":");
							if(location.startsWith(componets[1])||location.startsWith("index")){
								persion = true;
								break;
							}
						}
					}
				}
				if(!persion){
					window.location.href = 'login.html';
				}
			}else{
				window.location.href = 'login.html';
			}
		}
	});
});