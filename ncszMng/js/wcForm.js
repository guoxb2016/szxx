/**
 * guoxb 
 * 2020-02-21
 */
$(function(){
	var storage = window.localStorage;
	$("#test2").focus(function(){
		document.activeElement.blur();
	});
	$("#test3").focus(function(){
		document.activeElement.blur();
	});
	var calendar = new LCalendar();
	var calendar1 = new LCalendar();
	calendar.init({
		'trigger': '#test2',//标签id
		'type': 'ym',//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择
		'minDate':'1900-1',//最小日期 注意：该值会覆盖标签内定义的日期范围
		'maxDate':'2099-12'//最大日期 注意：该值会覆盖标签内定义的日期范围
	});
	calendar1.init({
		'trigger': '#test3',//标签id
		'type': 'ym',//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择
		'minDate':'1900-1',//最小日期 注意：该值会覆盖标签内定义的日期范围
		'maxDate':'2099-12'//最大日期 注意：该值会覆盖标签内定义的日期范围
	});
	$('input[name="createBy"]').val(storage['userid'].toString());
	$('input[name="sysOrgCode"]').val(storage['orgCode'].toString());
	//监听提交
	layui.use(['form', 'layedit', 'laydate'], function(){
		var form = layui.form
		,layer = layui.layer
		,layedit = layui.layedit
		,laydate = layui.laydate;
		form.on('submit(form)', function(data){
			$.ajax({
				url:config.weburl+'summary/toiletSummary/add',
				type:'post',
				dataType:'json',
				contentType: 'application/json',
				data:JSON.stringify(data.field),
				timeout:3000,
				success:function(data){
					if(data.success){
						layer.msg("数据提交成功!");
						window.location.href = "wcList.html";
					}else{
						layer.msg("数据提交失败，请再次提交!");
					}
				},
				error:function (e) {
					layer.msg("提交失败,请联系管理员!");
				}
			});
			return false;
		});
	});
});