var uvKeyList = new Array();
var uvValueList = new Array();
var pvKeyList = new Array();
var pvValueList = new Array();
var PVChart = null;
var UVChart = null;
var pvData = "[]";
var uvData = "[]";
var pvpage = 0;
var uvpage = 0;

$(function() {
	
	$(document).ready(function() {
		loadSelect();
		var submit = document.getElementById("actAnySubmit");
		var PVPrevious = document.getElementById("PVPrevious");
		var PVNext = document.getElementById("PVNext");
		var UVPrevious = document.getElementById("UVPrevious");
		var UVNext = document.getElementById("UVNext");
		
		/*事件注册  */
		if(window.attachEvent){//IE浏览器
			submit.attachEvent("onclick",doSub);
			PVPrevious.attachEvent("onclick",doPVPrevious);
			PVNext.attachEvent("onclick",doPVNext);
			UVPrevious.attachEvent("onclick",doUVPrevious);
			UVNext.attachEvent("onclick",doUVNext);
		}
		else{//firefox,chrome浏览器等
			submit.addEventListener("click",doSub,false);
			PVPrevious.addEventListener("click",doPVPrevious,false);
			PVNext.addEventListener("click",doPVNext,false);
			UVPrevious.addEventListener("click",doUVPrevious,false);
			UVNext.addEventListener("click",doUVNext,false);
		}
	});
			
	function doSub(){
		uvKeyList.length = 0;
		uvValueList.length = 0;
		pvKeyList.length = 0;
		pvValueList.length = 0;
		pvpage = 0;
		uvpage = 0;
		pvData = "[]";
		uvData = "[]";
		var itemID = document.getElementById("actAnyItemid").value;
		var actionID = document.getElementById("actAnyActionid").value;
		if(document.getElementById("actAnyEndTime").value == "")
			document.getElementById("actAnyEndTime").value = (new Date()).toLocaleDateString();
		if(document.getElementById("actAnyStartTime").value == ""){
			var start = (new Date()).getTime() - 30 * 3600 * 24 * 1000;
			var dateTmp = new Date(start);
			document.getElementById("actAnyStartTime").value = dateTmp.toLocaleDateString();
		}
		var startTime = (new Date(document.getElementById("actAnyStartTime").value)).getTime()/1000;
		var endTime = (new Date(document.getElementById("actAnyEndTime").value)).getTime()/1000;
		var flag = document.getElementById("actAnyDayOrHour").value;
		itemID = (itemID == -1 ? "" : itemID);
		actionID = (actionID == -1 ? "" : itemID);
		var param = {
				itemid:itemID,
				actionid:actionID,
				flag:flag,
				startTime:startTime,
				endTime:endTime
		};
		$.ajax({
			url : 'searchUv.action',// 从后端获取UV数据
			type: "post",
			data: param,// 发送到服务端的参数
			dataType : "json",
			async:true,
			success : function(data,status) {
				var i = 0;
				   for(var a in data){  
				     uvKeyList[i] = a;
				     uvValueList[i] = data[a];
				     i++;
				   }    
				doSub2(param);
			},
			error: function (){
	              alert("UV获取数据失败!");
	              doSub2(param);
	        },
			cache : false
		});
		
	}
	function doSub2(param)
	{
		$.ajax({
			url : 'searchPv.action',// 从后端获取PV数据
			type: "post",
			async:true,
			data: param,// 发送到服务端的参数
			dataType : "json",
			success : function(data,status) {
				   var i = 0;
				   for(var a in data){
					   pvKeyList[i] = a;
					   pvValueList[i] = data[a];
					   i++;
				   }   
					doSub3();
			},
			error: function (){
	              alert("PV获取数据失败!");
	              doSub3();
	        },
			cache : false
		});
	}
	
	function doSub3()
	{
		refreshPVTable();
		refreshUVTable();
		initData();
		refreshChart();
	}
	
	function doPVPrevious()
	{
		if(pvpage > 0){
			pvpage--;
			refreshPVTable();
		}
	}
	
	function doPVNext()
	{
		if(pvpage < (pvKeyList.length/9 - 1))
		{
			pvpage++;
			refreshPVTable();
		}
	}
	
	function doUVPrevious()
	{
		if(uvpage > 0){
			uvpage--;
			refreshUVTable();
		}
	}
	
	function doUVNext()
	{
		if(uvpage < (uvKeyList.length/9 - 1))
		{
			uvpage++;
			refreshUVTable();
		}
	}
		
	function refreshPVTable()
	{
		clearPVTable();
		var pvTable = document.getElementById("PVTable");
		document.getElementById("PVText").innerHTML = "第" + (pvpage + 1) + "页/共" + Math.ceil(pvKeyList.length/9) + "页";
		var i = 0;
		var index = 0;
		i = 0 + pvpage * 9;
		for (var j = 0;j < 9 && i < pvKeyList.length; i++,j++)
		{
			var date = new Date(parseInt(pvKeyList[i])*1000);
			date = date.getFullYear().toString() +"/" +(date.getMonth()+1).toString() + "/"+date.getDate().toString() +" "+date.getHours() + "时";
			index = j + 2;
			document.getElementById("Pl"+index+"co1").innerHTML = date;
			document.getElementById("Pl"+index+"co2").innerHTML = pvValueList[i];
		}
		
	}
	function refreshUVTable()
	{
		clearUVTable();
		var uvTable = document.getElementById("UVTable");
		document.getElementById("UVText").innerHTML = "第" + (uvpage + 1) + "页/共" + Math.ceil(uvKeyList.length/9) + "页";
		var i = 0;
		var index = 0;
		i = 0 + uvpage * 9;
		for (var j = 0; j < 9 && i < uvKeyList.length; i++, j++)
		{
			var date = new Date(parseInt(uvKeyList[i])*1000);
			date = date.getFullYear().toString() +"/" +(date.getMonth()+1).toString() + "/"+date.getDate().toString() +" "+date.getHours() + "时";
			
			index = j + 2;
			document.getElementById("Ul"+index+"co1").innerHTML = date;
			document.getElementById("Ul"+index+"co2").innerHTML = uvValueList[i];
		}
	}
	function clearPVTable(){
		var pvTable = document.getElementById("PVTable");
		for (var i = 0; i < 9; i++)
		{
			pvTable.rows[i+1].cells[0].innerHTML = "";
			pvTable.rows[i+1].cells[1].innerHTML = "";
		}
	}
	function clearUVTable(){
		var uvTable = document.getElementById("UVTable");
		for (var i = 0; i < 9; i++)
		{
			uvTable.rows[i+1].cells[0].innerHTML = "";
			uvTable.rows[i+1].cells[1].innerHTML = "";
		}
	}
	function loadSelect(){
		var itemKey = new Array();
		var itemValue = new Array();
		
		var itemid = document.getElementById("actAnyItemid");
		var actionid = document.getElementById("actAnyActionid");
		itemid.options.length=0; 
		actionid.options.length=0; 
		
		$.ajax({
			url:"getItemsSelect.action",
			dataType:'json',
			
			success:function(data){
				itemid.options.add(new Option("空值",-1));
				for(var item in data){					
					itemid.options.add(new Option(data[item],item));
				}
			
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
				actionid.options.add(new Option("空值",-1));
				for(var item in data){					
				
					actionid.options.add(new Option(data[item],item));
					
				}
				
			},
			error:function (){
	              alert("获取数据失败：" + errorThrown);
	        },
			cache : false
		});
	}
	
	Highcharts.setOptions({
	    global: {
	        useUTC: false
	    }
	});
	
	function initData(){
		if(pvKeyList.length != 0)
		{
			pvData = "[";
			for(var i = 0; i < pvKeyList.length; i++)
				{
					pvData +="[" + pvKeyList[i] + "000," + pvValueList[i] + "]";
					if(i < pvKeyList.length - 1)
						pvData += ",";
				}
			pvData += "]";
		}
		if(uvKeyList.length != 0)
		{
			uvData = "[";
			for(var i = 0; i < uvKeyList.length; i++)
				{
					uvData +="[" + uvKeyList[i] + "000," + uvValueList[i] + "]";
					if(i < uvKeyList.length - 1)
						uvData += ",";
				}
			uvData += "]";
		}
	}	
	
	function refreshChart()
	{	
		PVChart = $('#PVChart').highcharts({
			chart: {
	            zoomType: 'x',
	            spacingRight: 20
	        },
	        title: {
	            text: 'PVChart'
	        },
	        subtitle: {
	            text: document.ontouchstart === undefined ?
	                'Click and drag in the plot area to zoom in' :
	                'Pinch the chart to zoom in'
	        },
	        xAxis: {
	            type: 'datetime',
	            maxZoom: 14 * 24 * 3600000, // fourteen days
	            title: {
	                text: null
	            }
	        },
	        yAxis: {
	            title: {
	                text: '访问量'
	            }
	        },
	        tooltip: {
	            shared: true
	        },
	        legend: {
	            enabled: false
	        },
	        plotOptions: {
	            area: {
	                fillColor: {
	                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
	                    stops: [
	                        [0, Highcharts.getOptions().colors[0]],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                    ]
	                },
	                lineWidth: 1,
	                marker: {
	                    enabled: false
	                },
	                shadow: false,
	                states: {
	                    hover: {
	                        lineWidth: 1
	                    }
	                },
	                threshold: null
	            }
	        },

	        series: [{
	            type: 'area',
	            name: '访问量',
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: pvKeyList.length == 0 ? 0 : parseInt(pvKeyList[0]) * 1000,
	            data: eval(pvData)
	        }]
		    });
		
		UVChart = $('#UVChart').highcharts({
			chart: {
	            zoomType: 'x',
	            spacingRight: 20
	        },
	        title: {
	            text: 'UVChart'
	        },
	        subtitle: {
	            text: document.ontouchstart === undefined ?
	                'Click and drag in the plot area to zoom in' :
	                'Pinch the chart to zoom in'
	        },
	        xAxis: {
	            type: 'datetime',
	            maxZoom: 14 * 24 * 3600000, // fourteen days
	            title: {
	                text: null
	            }
	        },
	        yAxis: {
	            title: {
	                text: '访问量'
	            }
	        },
	        tooltip: {
	            shared: true
	        },
	        legend: {
	            enabled: false
	        },
	        plotOptions: {
	            area: {
	                fillColor: {
	                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
	                    stops: [
	                        [0, Highcharts.getOptions().colors[0]],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                    ]
	                },
	                lineWidth: 1,
	                marker: {
	                    enabled: false
	                },
	                shadow: false,
	                states: {
	                    hover: {
	                        lineWidth: 1
	                    }
	                },
	                threshold: null
	            }
	        },

	        series: [{
	            type: 'area',
	            name: '访问量',
	            pointInterval: 24 * 3600 * 1000,
	            pointStart: uvKeyList.length == 0 ? 0 : parseInt(uvKeyList[0]) * 1000,
	            data: eval(uvData)
	        }]
		    });
		
		PVhart.redraw();
		UVChart.redraw();
		}
});
	

