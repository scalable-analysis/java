/* js for 获取用户浏览过itemid列表 
 * 绘图框架：Highcharts
 */
$(function() {
	var chart;
	var seriesData = new Array();
	var xAxisData =new Array();
		$(document).ready(function() {		
			
			loadInit();
			 var initParam ={
						size:"100",
						startTime:'1390150800',
						endTime:'1440150801'
				};
			showChart(initParam);
			
			 var submit = document.getElementById("submit");
			 var previous = document.getElementById("previous");
			 var next = document.getElementById("next");
			 /*事件注册  */
				if(window.attachEvent){//IE浏览器
					previous.attachEvent("onclick",getPrevious);
					next.attachEvent("onclick",getNext);
					submit.attachEvent("onclick",search);
				}
				else{//firefox,chrome浏览器等
					previous.addEventListener("click",getPrevious,false);
					next.addEventListener("click",getNext,false);
					submit.addEventListener("click", search, false);
				}
			});

	function loadInit(){
		if(document.getElementById("endTime").value == "")
			document.getElementById("endTime").value = (new Date()).toLocaleDateString();
		if(document.getElementById("startTime").value == ""){
			var start = (new Date()).getTime() - 30 * 3600 * 24 * 1000;
			var dateTmp = new Date(start);
			document.getElementById("startTime").value = dateTmp.toLocaleDateString();
		}
	};
	
	function showChart(param) {	
		
		if(chart != null){
			chart.destroy();	
		}
			
		var option = {
			chart : {
				type : 'column',
				renderTo : 'itemChart'
			},
			title : {
				text : '获取app下item列表'
			},
			subtitle : {
				text : 'dmpDemo'
			},
			xAxis: { 				
			},
			yAxis : {
				min : 0,
				title : {
					text : '点击次数'
				}
			},
			credits: {
	            enabled: true,
	            text: 'social-touch',
	            href: 'http://www.social-touch.com'
	        },			
			plotOptions : {
				column : {
					pointPadding : 0.2,
					borderWidth : 0
				}
			},
			series : [
					{
						name : 'item'
			}],
		};
	
		$.ajax({
			url : 'searchAppItem.action',// 从后端获取数据
			type: "post",
			data:param,// 发送到服务端的参数
			dataType : "json",
			success : function(data) {			
				//alert("seriesData++"+seriesData);
				var resData = eval(data);
			//	alert("dataRes++"+resData);
			//	alert("length_+"+resData.length);
				var item;
				seriesData = [];
				xAxisData = [];
				for(var i=0;i<resData.length;i++){
					item = resData[i];
					seriesData.push(item.count);
					xAxisData.push(item.name);
				}
			//	alert("seriesData++length"+seriesData.length);
				option.series[0].data = seriesData;
				option.xAxis.categories = xAxisData;
				chart = new Highcharts.Chart(option);
				if(seriesData.length == 0)
					alert("未能获取到数据，请填写适当值!");
				showItemTable(xAxisData,seriesData);
			},
			error:function (){
	              alert("获取数据失败!");
	        },
			cache : false
		});	
	};

	function search() {
		
		var size = document.getElementById("itemSize").value;	
		
		var startTime = (new Date(document.getElementById("startTime").value)).getTime()/1000;
		var endTime = (new Date(document.getElementById("endTime").value)).getTime()/1000;

		if(size == null || size == ""){
			alert("size不能为空！");
		}
		else if(isNaN(size)){
			alert("size不能包含字符！");
			document.getElementById("itemSize").value = "";
		}
		else if(size.indexOf(".") != -1){
			alert("size值应为整数！");
			document.getElementById("itemSize").value = "";
		}
		else if(size<0){
			alert("size值不能为负数！");
			document.getElementById("itemSize").value = "";
		}
		else{
			 var param ={
						size:size,
						startTime:startTime,
						endTime:endTime
				};
			 showChart(param);
		}
		 
	};
	
	function showItemTable(a,b){
		if(a.length > 10){
			for(var i=0;i<10;i++){		
				document.getElementById("td"+i+"0").innerHTML = a[i];
				document.getElementById("td"+i+"1").innerHTML = b[i];
			}
		}
		else{			
			for(var i=0;i<10;i++){		
				document.getElementById("td"+i+"0").innerHTML = null;
				document.getElementById("td"+i+"1").innerHTML = null;
			}
			
			for(var i=0;i<a.length;i++){
				document.getElementById("td"+i+"0").innerHTML = a[i];
				document.getElementById("td"+i+"1").innerHTML = b[i];
			}
		}
				
		if(parseInt(a.length%10) == 0)
			document.getElementById("totalPages").value = parseInt(a.length/10);
		else
			document.getElementById("totalPages").value = parseInt(a.length/10)+1;
		if(a.length == 0)
			document.getElementById("currentPage").value = 0;
		document.getElementById("currentPage").value = 1;
		noneDisplayEmptyTd();
	};
	
	
	function getPrevious(){
		var currentPage = document.getElementById("currentPage").value;
		currentPage = parseInt(currentPage);
		if(currentPage > 1){
			var base = 10 * (currentPage-2);		
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = xAxisData[i+base];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i+base];
			}
			document.getElementById("currentPage").value = currentPage - 1;
		}
		else if(parseInt(currentPage) == 1){
			alert("已经是第一页！");
		}
		noneDisplayEmptyTd();
	};

	function getNext(){
		var totalPages = document.getElementById("totalPages").value;
		var currentPage = document.getElementById("currentPage").value;
		
		currentPage = parseInt(currentPage);

		if(currentPage+1 < parseInt(totalPages)){
			var base = 10 * parseInt(currentPage);
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = xAxisData[i+base];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i+base];
			}
			document.getElementById("currentPage").value = currentPage + 1;
		}
		else if(currentPage + 1 == parseInt(totalPages)){
			var base = 10 * parseInt(currentPage);
			var tailPageSize = xAxisData.length - base;
			
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = null;
				document.getElementById("td"+i+"1").innerHTML = null;
			}
			for(var i=0;i<tailPageSize;i++){
				document.getElementById("td"+i+"0").innerHTML = xAxisData[i+base];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i+base];
			}
			document.getElementById("currentPage").value = currentPage + 1;
		}
		else if(parseInt(currentPage) == parseInt(totalPages)){
			alert("已经是最后一页！");
		}
		noneDisplayEmptyTd();
	};
	
	function noneDisplayEmptyTd(){
		
		for(var i=0;i<10;i++){
			var item = document.getElementById("td"+i+"0");
			var count = document.getElementById("td"+i+"1");
			
			if(item.innerHTML == null || item.innerHTML == undefined || item.innerHTML == "")
				item.style.display = "none";
			else
				item.style.display = "";	
			if(count.innerHTML == null || count.innerHTML == undefined || count.innerHTML == "")
				count.style.display = "none";
			else
				count.style.display = "";	
		}
	};
	
});