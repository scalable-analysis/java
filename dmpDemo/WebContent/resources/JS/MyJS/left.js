 	$(function(){
	
	var iframe = document.getElementById("iframe");
	
	var navListItem1 = document.getElementById("navListItem1");
	var navListItem2 = document.getElementById("navListItem2");
	var navListItem3 = document.getElementById("navListItem3");
	var navListItem4 = document.getElementById("navListItem4");
	var navListItem5 = document.getElementById("navListItem5");
	var navListItem6 = document.getElementById("navListItem6");
	var logo = document.getElementById("logo");
	
	$(document).ready(function(){
		/*事件注册  */
		if(window.attachEvent){//IE浏览器
			navListItem1.attachEvent("onclick",function(){itemClick("../business/offer.jsp")});
			navListItem2.attachEvent("onclick",function(){itemClick("../business/showItems.jsp")});
			navListItem3.attachEvent("onclick",function(){itemClick("../business/showOpportunity.jsp")});
			navListItem4.attachEvent("onclick",function(){itemClick("../business/tag.jsp")});
			navListItem5.attachEvent("onclick",function(){itemClick("../business/shuigeOffer.jsp")});
			navListItem6.attachEvent("onclick",function(){itemClick("../business/tools.jsp")});
			logo.attachEvent("onclick",function(){itemClick("../business/showWelcome.jsp")});
		}
		else{//firefox,chrome浏览器等
			navListItem1.addEventListener("click",function(){itemClick("../business/offer.jsp")},false);
			navListItem2.addEventListener("click",function(){itemClick("../business/showItems.jsp")},false);
			navListItem3.addEventListener("click",function(){itemClick("../business/showOpportunity.jsp")},false);
			navListItem4.addEventListener("click",function(){itemClick("../business/tag.jsp")},false);
			navListItem5.addEventListener("click",function(){itemClick("../business/shuigeOffer.jsp")},false);
			navListItem6.addEventListener("click",function(){itemClick("../business/tools.jsp")},false);
			logo.addEventListener("click",function(){itemClick("../business/showWelcome.jsp")},false);
		}
	});
	
	
	function itemClick(url){
		iframe.src = url;
	};
});