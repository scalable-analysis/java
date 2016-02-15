/* js for 获取用户浏览过itemid列表 
 * 绘图框架：Highcharts
 */
$(function() {
	
	var chart;
	var seriesData = new Array();
	
		$(document).ready(function() {
			
			loadSelect();
			loadInit();
			
			var initParam = {
				itemid:"4",
				actionid:"1",
				expkey:"4",
				startTime:"1310150800",
				endTime:"1590150801"
			};
			showChart(initParam);
			 
			 var submit = document.getElementById("submit");
			 var previous = document.getElementById("previous");
			 var next = document.getElementById("next");
			 var selectIdOrType = document.getElementById("selectIdOrType");
			 /*事件注册  */
				if(window.attachEvent){//IE浏览器
					previous.attachEvent("onclick",getPrevious);
					next.attachEvent("onclick",getNext);
					submit.attachEvent("onclick",search);
					selectIdOrType.attachEvent("onchange",changeSelectIdOrType);
				}
				else{//firefox,chrome浏览器等
					previous.addEventListener("click",getPrevious,false);
					next.addEventListener("click",getNext,false);
					submit.addEventListener("click", search, false);
					selectIdOrType.addEventListener("change", changeSelectIdOrType, false);
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
	
	function loadSelect(){
		var actionid = document.getElementById("actionid");
		var expkey = document.getElementById("expkey");
		var itemid = document.getElementById("itemid");		 		
		document.getElementById("itemtype").style.display = "none";
	
		actionid.options.length=0; 
		itemid.options.length=0; 
		expkey.options.length=0; 
		
		$.ajax({
			url:"getItemsSelect.action",
			dataType:'json',
			success:function(data){		
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
				for(var item in data){						
					actionid.options.add(new Option(data[item],item));					
				}			
			},				
			error:function (){
				alert("获取数据失败!");
			},
			cache : false
		});	
			
		$.ajax({
			url:"getExpkeysSelect.action",
			dataType:'json',
			success:function(data){
				for(var item in data){						
					expkey.options.add(new Option(data[item],item));					
				}			
			},
			error:function (){
				alert("获取数据失败!");
			},
			cache : false
		});	
	};

	function changeSelectIdOrType(){
		
		var valueSelect = document.getElementById("selectIdOrType").value;
		if(valueSelect == "itemid"){
			document.getElementById("itemtype").style.display = "none";
			document.getElementById("itemid").style.display = "";
		 }
		else if(valueSelect == "itemtype"){
			document.getElementById("itemid").style.display = "none";
			document.getElementById("itemtype").style.display = "";
		 }
	};
	function showChart(param) {
		if(chart != null){
			chart.destroy();	
		}
		var option = {
		chart: {
            renderTo: 'itemChart',        //在哪个区域呈现，对应HTML中的一个元素ID
            plotBackgroundColor: null,    //绘图区的背景颜色
            plotBorderWidth: null,        //绘图区边框宽度
            plotShadow: false            //绘图区是否显示阴影            
        },
        
        //图表的主标题
        title: {
            text: '获取用户行为扩展信息统计'
        },
        //当鼠标经过时的提示设置
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            percentageDecimals: 1
        },
        credits: {
            enabled: true,
            text: 'social-touch',
            href: 'http://www.social-touch.com'	
        },
        //每种图表类型属性设置
        plotOptions: {
            //饼状图
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        //Highcharts.numberFormat(this.percentage,2)格式化数字，保留2位精度
                        return '<b>'+ this.point.name +'</b>: '+Highcharts.numberFormat(this.percentage,2) +' %';
                    }
                }
            }
        },
			series: [{
                type: 'pie',
                name: '占有比例'
            }]
		};
	
		$.ajax({
			url : 'searchExpandStatis.action',// 从后端获取数据
			type: "post",
			data:param,// 发送到服务端的参数
			dataType : "json",
			success : function(data) {
			var resData = eval(data);
			var item;
			seriesData = [];
			for(var i=0;i<resData.length;i++){
				item = resData[i];
				seriesData.push([item.name,item.count]);
			}
			
			if(seriesData.length <= 15){
				
				option.series[0].data = seriesData;
			}
			/* 如果數據量大於15，則從大至少其它的都作為其它*/
			else{
				var seriesDataTmp = new Array();
				for(var i=0;i<15;i++){
					seriesDataTmp.push(seriesData[i]);
				}
				var othersTotal = 0;
				for(var i=15;i<seriesData.length;i++){
					//alert("parseInt+ "+seriesData[i][0]);
					othersTotal += parseInt(seriesData[i][1]);
				}
				seriesDataTmp.push(['其它',othersTotal]);
				option.series[0].data = seriesDataTmp;
				}
				
				chart = new Highcharts.Chart(option);
				
				showItemTable();
			},
			error:function (){
	              alert("获取数据失败!");
	        },
			cache : false
		});
	
	};


	function search() {
		
		var actionid = document.getElementById("actionid").value;
		var expkey = document.getElementById("expkey").value;
		
		
		var selectIdOrType = document.getElementById("selectIdOrType").value;
		
		var startTime = (new Date(document.getElementById("startTime").value)).getTime()/1000;
		var endTime = (new Date(document.getElementById("endTime").value)).getTime()/1000;

		// alert("size++"+itemIdOrType);	 
		// alert("actionid++"+actionid);
		 //alert("expkey++"+expkey);
		//alert("selectIdOrType++"+selectIdOrType);
		 if(selectIdOrType == "itemid"){
			 var itemid = document.getElementById("itemid").value;
			 var param ={
					itemid:itemid,
					actionid:actionid,
					expkey:expkey,
					startTime:startTime,
					endTime:endTime
			};
			 showChart(param);
		 }
		 else if(selectIdOrType == "itemtype"){
			 var itemtype = document.getElementById("itemtype").value;
			 if(itemtype == "" || itemtype == null){
				 alert("itemtype不能为空！");
			 }
			 else{
				 var param ={
						 	itemtype:itemtype,
							actionid:actionid,
							expkey:expkey,
							startTime:startTime,
							endTime:endTime
					};
				 showChart(param);
			 }
			 
		 }
		 
	};
	
	function showItemTable(){
		
		if(seriesData.length > 10){
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = seriesData[i][0];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i][1];
			}
		}
		else{		
			/* 擦除表格数据*/
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = null;
				document.getElementById("td"+i+"1").innerHTML = null;
			}	
			for(var i=0;i<seriesData.length;i++){
				document.getElementById("td"+i+"0").innerHTML = seriesData[i][0];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i][1];
			}
		}
		/*总页数*/
		if(parseInt(seriesData.length%10) == 0)
			document.getElementById("totalPages").value = parseInt(seriesData.length/10);
		else
			document.getElementById("totalPages").value = parseInt(seriesData.length/10)+1;
		document.getElementById("currentPage").value = 1;
		noneDisplayEmptyTd();
	};
	
	function getPrevious(){
		var totalPages = document.getElementById("totalPages").value;
		var currentPage = document.getElementById("currentPage").value;
		currentPage = parseInt(currentPage);
		if(currentPage > 1){
			var base = 10 * (currentPage-2);
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = seriesData[i+base][0];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i+base][1];
			}
			document.getElementById("currentPage").value = currentPage -1;
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
		
		if(currentPage + 1 < parseInt(totalPages)){
			var base = 10 * parseInt(currentPage);
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = seriesData[i+base][0];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i+base][1];
			}
			document.getElementById("currentPage").value = currentPage + 1;
		}
		else if(currentPage + 1 == parseInt(totalPages)){
			
			var base = 10 * parseInt(currentPage);
			var tailPageSize = seriesData.length % 10;
			
			for(var i=0;i<10;i++){
				document.getElementById("td"+i+"0").innerHTML = null;
				document.getElementById("td"+i+"1").innerHTML = null;
			}
			for(var i=0;i<tailPageSize;i++){
				document.getElementById("td"+i+"0").innerHTML = seriesData[i+base][0];
				document.getElementById("td"+i+"1").innerHTML = seriesData[i+base][1];
			}
			document.getElementById("currentPage").value = currentPage + 1;
		}
		else if(parseInt(currentPage) == parseInt(totalPages)){
			alert("已经是最后一页");
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