/**
 *列表
 */
var loading = false;  //状态标记
var allLoad = false
var pageNo = 1;
var pageSize = 10;
var count = 0;
var param = window.location.search;
$(function(){
	var storage = window.localStorage;
	var orgCode = storage['orgCode'].toString();
	if(orgCode == 'A02'){
		orgCode = "";
		//上报按钮隐藏
		$('#footer').css('display','none');
	}
	//默认加载第一页
	loadMore($('#searchInput').val(),orgCode);
	//滚动加载
	$(document.body).infinite().on("infinite", function() {
		if(loading) {
			return;
		}
		loading = true;
		if(!allLoad){
			loadMore($('#searchInput').val(),orgCode);
		}
	});
	//搜索
	$("#searchInput").on('keypress',function(e) {  
        var keycode = e.keyCode;  
        if(keycode=='13') {  
            e.preventDefault();    
            //请求搜索接口  
            $("#menu_con").children().remove();
    		pageNo = 1;
    		loadMore($('#searchInput').val(),orgCode);
        }  
	});
});
/**
 * 根据页码和页数加载
 * @param pageNo
 * @param pageSize
 */
function loadMore(code,orgCode){
	$.ajax({
		url: config.weburl + '/summary/sanitationSummary/list',
		dataType: 'json',
		data: {
			pageNo : pageNo,
			pageSize : pageSize,
			sysOrgCode : orgCode,
			bianhao: code
		},
		success: function (ret) {
			if(ret.success&&ret.result != null){
				for(var i=0;i<ret.result.records.length;i++){
					count ++;
					var status = "未完成";
					var url = "hwForm.html";
					var d = ret.result.records[i].step;
					if(d != null&&d!=""){
						var t = d.split(",");
						if(t.length == 6){
							status = '完成';
							url = "hwDetail.html";
						}
					}
					$("#menu_con").append('<a href="'+url+'?id='+ret.result.records[i].id+'">'
							+'<div class="weui-form-preview">'
							+'<div class="weui-form-preview__hd" style="margin-top: 0.3em;">'
							+'<label class="weui-form-preview__label">上报时间：'+ret.result.records[i].createTime+'</label><em'
						    +'class="weui-form-preview__value">&ensp;'+status+'</em>'
							+'</div>'
							+'<div class="weui-form-preview__bd">'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">区县</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].sysOrgName+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">保洁员（操作工）</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].grBaojy+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">扫洒司机</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].grSijiSais+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">垃圾运输司机</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].grSijiLajysh+'</span>'
							+'</div>'
							+'</div>'
							+'</div>'
							+'</a>');
				}
				//已加载完成
				if(count == ret.result.total){
					allLoad = true ;
					//销毁加载器
					$(document.body).destroyInfinite()
					//隐藏加载指示器的HTML
					$('.more').css("display",'none');
					//显示加载完成指示器
					$('.none').css("display",'display');
				}
				pageNo ++;
				loading = false;
			}
		}
	});
}