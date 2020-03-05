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
	if(orgCode = 'A02'){
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
		url: config.weburl + '/summary/sanitationEpSummary/list',
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
					$("#menu_con").append('<a href="ephwDetail.html?id='+ret.result.records[i].id+'">'
							+'<div class="weui-form-preview">'
							+'<div class="weui-form-preview__hd" style="margin-top: 0.3em;">'
							+'<label class="weui-form-preview__label">上报时间：'+ret.result.records[i].createTime+'</label><em'
							+'class="weui-form-preview__value">'+ret.result.records[i].sysOrgName+'</em>'
							+'</div>'
							+'<div class="weui-form-preview__bd">'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">环卫作业车辆</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].vehicle+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">环卫作业人员</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].personTime+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">生活垃圾无害化处理</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].garbageDisposal+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">口罩废弃桶</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].kzKouzhFeiqt+'</span>'
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