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
	$.ajax({
		url:config.weburl+'summary/sanitationSummary/queryByNew',
		type:'get',
		dataType:'json',
		data:{'sysOrgCode':storage['orgCode'].toString()},
		timeout:3000,
		success:function(data){
			if(data.success&&data.result!=null){
				//layer.msg("这是最新上报数据，请修改后再次上报!");
				//批量赋值
				$('input').each(function(){
					$(this).val(data.result[$(this).attr("name")]);
				});
			}else{
				layer.msg("您首次上报，请认真填写数据!");
			}
			$('input[name="createBy"]').val(storage['userid'].toString());
			$('input[name="sysOrgCode"]').val(storage['orgCode'].toString());
		},
		error:function (e) {
			layer.msg("获取失败,请联系管理员!");
		}
	});
	//监听提交
	layui.use(['form', 'layedit', 'laydate'], function(){
		var form = layui.form
			,layer = layui.layer
			,layedit = layui.layedit
			,laydate = layui.laydate;
		form.on('submit(form)', function(data){
			$.ajax({
				url:config.weburl+'summary/sanitationSummary/edit',
				type:'put',
				dataType:'json',
				contentType: 'application/json',
				data:JSON.stringify(data.field),
				timeout:3000,
				success:function(data){
					if(data.success){
						layer.msg("数据提交成功!");
						window.location.href = "hwForm5.html";
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