/**
 * guoxb 
 * 2020-02-21
 */
$(function(){
    //点击示意图切换
    $('.nav_process_item').click(function () {

    });
	//查询最新上报的数据回填
	var storage = window.localStorage;
	var param = window.location.search;
	//查询最新上报的数据回填
	var storage = window.localStorage;
	var url = "";
	if(param == null || param ==""){
		$('input[name="createBy"]').val(storage['userid'].toString());
		$('input[name="sysOrgCode"]').val(storage['orgCode'].toString());
		url = config.weburl+'summary/sanitationSummary/add';
	}else {
		//所有的加入ID
		$('.nav_process_item .nav_step').each(function(){
			var $data = $(this).attr('href');
			$(this).attr('href',$data+param)
		});
		url = config.weburl+'summary/sanitationSummary/edit';
		$.ajax({
			url:config.weburl+'summary/sanitationSummary/queryById'+param,
			type: 'get',
			dataType: 'json',
			data: {'sysOrgCode': storage['orgCode'].toString()},
			timeout: 3000,
			success: function (data) {
				if (data.success && data.result != null) {
					//layer.msg("这是最新上报数据，请修改后再次上报!");
					var d = data.result['step'];
					if(d != null&&d!=""){
						var t = d.split(",");
						for(var i in t){
							$($('.nav_process_item')[t[i]-1]).addClass('process');
							if(t[i] == $('input[name="step"]').val()){
								$('input[name="step"]').val("");
							}
						}
					}
					//批量赋值
					$('input').each(function () {
						$(this).attr("readonly",'true');
						if($(this).attr("name")=='step'){
							var $data = $(this).val();
							$(this).val($data +"," + data.result[$(this).attr("name")]);
						}else {
							$(this).val(data.result[$(this).attr("name")]);
						}
					});
				} else {
					layer.msg("您首次上报，请认真填写数据!");
				}
				$('input[name="createBy"]').val(storage['userid'].toString());
				$('input[name="sysOrgCode"]').val(storage['orgCode'].toString());
			},
			error: function (e) {
				layer.msg("获取失败,请联系管理员!");
			}
		});
	}
	//监听提交
	layui.use(['form', 'layedit', 'laydate'], function(){
		var form = layui.form
			,layer = layui.layer
			,layedit = layui.layedit
			,laydate = layui.laydate;
		form.on('submit(form)', function(data){
			window.location.href = "hwDetail5.html?id="+ data.field.id;
			return false;
		});
	});
});