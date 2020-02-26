/**
 * 通用js
 */
//登陆标志
var token = "";
var config = {

		//网站名称 (左上角显示的文字LOGO)
		webname: '南昌市市政信息上报系统',
		weburl : "http://localhost:8087/jeecg-boot/",
		//layer全局提示层
		layerMsg: {
			offset: 't', //坐标 (详细说明 https://www.layui.com/doc/modules/layer.html#offset)
			shade: [0.4, '#000'] //遮罩 (详细说明 https://www.layui.com/doc/modules/layer.html#shade)
		}
}
$(function(){
	//判断是否登录过
	var storage = window.localStorage;
	token = storage['token'];
});