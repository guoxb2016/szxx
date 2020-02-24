/**
 * guoxb 
 * 2019-09-11
 */
$(function(){
	var param = window.location.search;
	var vue = new Vue({
		el:'#app',
		data:{
			loan:{},
			loanRecords:[]
		},
		created:function(){
			$.ajax({
				url: config.weburl+"summary/toiletSummary/queryById" + param,
				dataType: 'json',
				success:  (data) => {
					this.loan = data.result;
					}
			});
		}
	});

});