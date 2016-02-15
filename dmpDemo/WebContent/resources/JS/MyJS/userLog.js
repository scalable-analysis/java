/* js for 获取用户浏览过userlog列表 
 * 绘图框架：Highcharts
 */
$(function(){

	var currentLength = 0;
	var lastLength = 0;
		$(document).ready(function() {			
			//loadInit();
			
			recordDefault();
			var previous = document.getElementById("previous");
			//var page = document.getElementById("page");
			var next = document.getElementById("next");
			var submit = document.getElementById("submit");
			
			 /*事件注册  */
				if(window.attachEvent){//IE浏览器
					previous.attachEvent("onclick",getPrevious);
					//page.attachEvent("onkeyup",yy);
					next.attachEvent("onclick",getNext);
					submit.attachEvent("onclick",checkValid);
				}
				else{//firefox,chrome浏览器等
					//page.addEventListener("keyup",yy,false);
					previous.addEventListener("click",getPrevious,false);
					next.addEventListener("click",getNext,false);
					submit.addEventListener("click",checkValid,false);
				}
	});
		
	function recordDefault(){
		loadSelect();
		if(document.getElementById("endTime").value != "")
			
			document.getElementById("endTime").value = (new Date(document.getElementById("endTime").value * 1000)).toLocaleDateString();
			
		if(document.getElementById("startTime").value != ""){
			
			document.getElementById("startTime").value =  (new Date(document.getElementById("startTime").value * 1000)).toLocaleDateString();
		}
		
		if(document.getElementById("endTime").value == "")
			document.getElementById("endTime").value = (new Date()).toLocaleDateString();
		if(document.getElementById("startTime").value == ""){
			var start = (new Date()).getTime() - 30 * 3600 * 24 * 1000;
			var dateTmp = new Date(start);
			document.getElementById("startTime").value = dateTmp.toLocaleDateString();
		}
		
	};
	
	function loadItem(){
		var itemDefault = document.getElementById("itemid");
		var actionDefault = document.getElementById("actionid");
		
		var requestItemId = document.getElementById("requestItemId").value;
		var requestActionId = document.getElementById("requestActionId").value;
		
		
		for(var i=0; i<itemDefault.options.length; i++){
			if(itemDefault.options[i].value == parseInt(requestItemId)){
				itemDefault.options[i].selected = true;
			}
		}
		for(var i=0; i<actionDefault.options.length; i++){
			if(actionDefault.options[i].value == parseInt(requestActionId)){
				actionDefault.options[i].selected = true;
			}
		}
	};

	function loadInit(){
		var endTime = Date.parse(new Date()).toString().substr(0, 10);
		var startTime = endTime - 30 * 24 * 3600;
		window.location.href = "searchUserLog.action?itemid=1&actionid=1&page=1&pageRecord=10&startTime="+startTime+"&endTime="+endTime;
	};
	
	function loadSelect(){
		
		var itemid = document.getElementById("itemid");
		var actionid = document.getElementById("actionid");
		itemid.options.length=0; 
		actionid.options.length=0; 
		
		$.ajax({
			url:"getItemsSelect.action",
			dataType:'json',
			
			success:function(data){		
				for(var item in data){
					itemid.options.add(new Option(data[item],item));
				}
				loadItem();
			},
			error:function (){
	              alert("获取数据失败：" + errorThrown);
	        },
			cache : false
		});
		$.ajax({
			url:"getActionsSelect.action",
			dataType:'json',
			success:function(data){				
				for(var item in data){									
					actionid.options.add(new Option(data[item],item));					
				}				
			},
			error:function (){
	              alert("获取数据失败：" + errorThrown);
	        },
			cache : false
		});	
	};

	function checkValid(){
		var page = document.getElementById("page").value;
		var pageRecord = document.getElementById("pageRecord").value;
		var actionid = document.getElementById("actionid").value;
		var itemid = document.getElementById("itemid").value;
		
			
		var startTime = (new Date(document.getElementById("startTime").value)).getTime()/1000;
		var endTime = (new Date(document.getElementById("endTime").value)).getTime()/1000;


		if(toValid(page) == false){
			
			var alertPage = "page值无效 ";
			//若输入无效值则重置
			document.getElementById("page").value = "";
			alert(alertPage);
			}
		else if(toValid(pageRecord) == false){
				var alertPageRecord = "pageRecord值无效 ";
				document.getElementById("pageRecord").value = "";
				alert(alertPageRecord);
			}
		else if(parseInt(pageRecord) <= 0 || parseInt(pageRecord) > 500){	
				alert("每页显示条数应大于0小于等于500");
				document.getElementById("pageRecord").value = "";
			}

		else{
			if(page == "")
				document.getElementById("page").value = 1;
			if(pageRecord == "")
				document.getElementById("pageRecord").value = 10;
			
			window.location.href = "searchUserLog.action?itemid="+itemid+"&actionid="+actionid+"&page="+page+"&pageRecord="+pageRecord+"&startTime="+startTime+"&endTime="+endTime;
			document.getElementById("page").value = page;
			document.getElementById("pageRecord").value = pageRecord;
		}
		
		};
	function getPrevious(){
		if(document.getElementById("page").value == 1){
			alert("已经是第一页！");
		}
		else{
			window.location.href="display_getPrevious.action";
		}
	}

	function getNext(){
		currentLength =  document.getElementById('itemTbody').getElementsByTagName('tr').length;
		
		if(currentLength < document.getElementById("pageRecord").value){
			alert("已经是最后一页！");
		}
		else{
			lastLength =  document.getElementById('itemTbody').getElementsByTagName('tr').length;
			
			window.location.href="display_getNext.action";
		}
		
	}
	
	function toValid(num){
			
		if(isNaN(num)){
			return false;
		}
		else if(num.indexOf(".") != -1){
			return false;
		}		
		else
			return true;
		}
});