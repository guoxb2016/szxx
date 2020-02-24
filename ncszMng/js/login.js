/**
 * guoxb
 * 2019-09-11
 */
$(function(){
	var storage = window.localStorage;
	for(var i=localStorage.length - 1 ; i >=0; i--){
		if(localStorage.key(i)=="username" && localStorage.getItem(localStorage.key(i))!= ""){
			$("#loginSacct").val(localStorage.getItem(localStorage.key(i)));
			$("#autoLogin").addClass('icon-check');
		}
		if(localStorage.key(i)=="password" && localStorage.getItem(localStorage.key(i))!= ""){
			$("#loginPwd").val(localStorage.getItem(localStorage.key(i)));
		}

	}
	// 是否自动登录
	$('.icon-nocheck, .login-tip').click(function () {
		$('.icon-nocheck').toggleClass('icon-check');
		if(!$("#autoLogin").hasClass('icon-check')){
			//否则清空'window.localStorage'
			storage["username"] =  "";
			storage["password"] = "";
		}
	});
	$("#loginBtn").click(function(){
			var username = $('#loginSacct').val();
			var password = $('#loginPwd').val();
			// 这里进行登录操作
			$.ajax({
				url: config.weburl + '/sys/mLogin',
				dataType: 'json',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify({
					"username": username,
					"password": password
				}),
				success: function (ret) {
					if (ret.success) {
						storage['token'] = ret.result.token;
						storage['userid'] = ret.result.userInfo.id;
						storage['orgCode'] = ret.result.userInfo.orgCode;
						//自动记住账号密码
						var checki=$("#autoLogin");
						if($("#autoLogin").hasClass('icon-check')){
							//存储到loaclStage
							storage["username"] = username;
							storage["password"] = password;
						}else{
							//否则清空'window.localStorage'
							storage["username"] =  "";
							storage["password"] = "";
						}
						window.location.href = 'index.html';
					} else {
						$.alert("账号或密码错误,登录失败!");
					}
				}
			});
		});
});