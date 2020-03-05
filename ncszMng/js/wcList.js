/**
 *公厕列表
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
	//删除
	$("body").on('click','.delete-swipeout',function(){
		var id = $(this).attr('data');
		$.ajax({
			url: config.weburl + '/summary/toiletSummary/delete',
			dataType: 'json',
			type: 'delete',
			data: {
				id: id
			},
			success: function (ret) {
				$.alert("数据删除成功!");
				window.location.reload();
			}
		});
	});
});
/**
 * 根据页码和页数加载
 * @param pageNo
 * @param pageSize
 */
function loadMore(code,orgCode){
	$.ajax({
		url: config.weburl + '/summary/toiletSummary/list',
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
					$("#menu_con").append(
						   '<div class="weui-cell weui-cell_swiped">'
							+' <div class="weui-cell__bd">'
							+'<a href="wcDetail.html?id='+ret.result.records[i].id+'">'
							+'<div class="weui-form-preview">'
							+'<div class="weui-form-preview__hd" style="margin-top: 0.3em;">'
							+'<label class="weui-form-preview__label">名称：'+ret.result.records[i].mingcheng+'</label> <em'
							+'class="weui-form-preview__value">'+ret.result.records[i].xianqu+'</em>'
							+'</div>'
							+'<div class="weui-form-preview__bd">'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">编号</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].bianhao+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">公厕类型</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].leixing+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">录入时间</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].createTime+'</span>'
							+'</div>'
							+'<div class="weui-form-preview__item">'
							+'<label class="weui-form-preview__label">地址</label> <span'
							+'class="weui-form-preview__value">'+ret.result.records[i].dizhi+'</span>'
							+'</div>'
							+'</div>'
							+'</div>'
							+'</a>'
							+'</div>'
							+'<div class="weui-cell__ft">'
							+'<a class="weui-swiped-btn weui-swiped-btn_warn delete-swipeout" data='+ret.result.records[i].id+' href="javascript:">删除</a>'
							+'</div>'
							+'</div>');
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
				$('.weui-cell_swiped').swipeout();
				pageNo ++;
				loading = false;
			}
		}
	});
}