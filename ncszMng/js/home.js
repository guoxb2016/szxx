/**
 * home js
 */
$(function () {
	$("#type").focus(function(){
		document.activeElement.blur();
	});
	$("#sysOrg").picker({
		title: "请选择抽查区县",
		cols: [
			{
				textAlign: 'center',
				values: ['东湖区', '高新区', '青山湖区', '红谷滩区', '西湖区', '青云谱区', '新建区', '湾里区', '经开区', '进贤县', '南昌县', '安义县']
			}
		]
	});
	$("#type").select({
		title: "请选择类别",
		multi: true,
		items: ["主干道","次干道","背街小巷","开放社区","垃圾站","公厕"]
	});
	var MAX = 5, MIN = 1;
	$('.weui-count__decrease').click(function (e) {
		var $input = $(e.currentTarget).parent().find('.weui-count__number');
		var number = parseInt($input.val() || "0") - 1
		if (number < MIN) number = MIN;
		$input.val(number)
	});
	$('.weui-count__increase').click(function (e) {
		var $input = $(e.currentTarget).parent().find('.weui-count__number');
		var number = parseInt($input.val() || "0") + 1
		if (number > MAX) number = MAX;
		$input.val(number)
	});
	//一键抽查
	$('#check').click(function(){
		var sysOrgName = $('#sysOrg').val();
		var type = $('#type').val();
		var roadType = $('#road').val();
		var count = $('#count').val();
		if(sysOrgName == null||sysOrgName==""){
			$.toptip('请选择抽查区县', 'error');
			return ;
		}
		if(type == null||type==""){
			$.toptip('请选择类别', 'error');
			return ;
		}
		$.showLoading("随机筛选中，请耐心等待！");
		$.ajax({
			url: 'http://10.10.11.153:8087/jeecg-boot/check/random/random',
			dataType: 'json',
			type: 'post',
			data: {
				"sysOrgName": sysOrgName,
				"type":type,
				"roadType":roadType,
				"count":count
			},
			success: function (ret) {
				$.hideLoading();
				if(ret.success){
					$('.weui-msg__title').text(sysOrgName);
					var types = type.split(",");
					var desc = "";
					$("#result").children().remove();
					for(var tp in types){
						desc = desc + " " + types[tp] +"（"+ret.result[types[tp]].length+"）";
						$("#result").append('<div class="weui-panel weui-panel_access">' +
							'<div class="weui-panel__hd" style="text-align: left;font-size: 18px">'+types[tp]+"（"+ret.result[types[tp]].length+"）"+'</div>' +
							'<div class="weui-panel__bd" id="'+tp+'">' +
							'</div>'+
							'</div>');
						for(var i in ret.result[types[tp]]){
							if(types[tp] == "主干道"||types[tp] == "次干道"||types[tp] == "背街小巷"){
								$("#"+tp).append(
									'<div class="weui-form-preview">'+
									'<div class="weui-form-preview__hd">'+
									'<label class="weui-form-preview__label">名称</label>'+
									'<em class="weui-form-preview__value">'+ ret.result[types[tp]][i].name+'</em>'+
									'</div>'+
									'<div class="weui-form-preview__bd">'+
									'<div class="weui-form-preview__item">'+
									'<label class="weui-form-preview__label">起止点</label>'+
									'<span class="weui-form-preview__value">'+ ret.result[types[tp]][i].startEnd+'</span>'+
									'</div>'+
									'</div>'+
									'</div>');
							}else{
								$("#result").append(
									'<div class="weui-form-preview">'+
									'<div class="weui-form-preview__hd">'+
									'<label class="weui-form-preview__label">名称</label>'+
									'<em class="weui-form-preview__value">'+ ret.result[types[tp]][i].name+'</em>'+
									'</div>'+
									'<div class="weui-form-preview__bd">'+
									'<div class="weui-form-preview__item">'+
									'<label class="weui-form-preview__label">地址</label>'+
									'<span class="weui-form-preview__value">'+ ret.result[types[tp]][i].address+'</span>'+
									'</div>'+
									'</div>'+
									'</div>');
							}
					}
					$('.weui-msg__desc').text(desc);
					}
					$("#h_popup").popup();
				}
			}
		});
	});
	$('.h_close').click(function () {
		$.closePopup();
	});
});
