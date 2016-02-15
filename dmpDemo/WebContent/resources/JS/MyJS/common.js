 $(function(){
	   
	   var setting = document.getElementById("headerRightImgSetting");
	   var logout = document.getElementById("headerRightImgLogout");
		$(document).ready(function(){
			
			/* 事件注册*/
			if(window.attachEvent){//IE
				setting.attachEvent("onmouseover",settingMouseOver);
				setting.attachEvent("onmouseout",settingMouseOut);
				setting.attachEvent("onclick",settingClick);
				logout.attachEvent("onmouseover",logoutMouseOver);
				logout.attachEvent("onmouseout",logoutMouseOut);
				logout.attachEvent("onclick",logoutClick);
			}
			else{//firefox,google等
				setting.addEventListener("mouseover",settingMouseOver);
				setting.addEventListener("mouseout",settingMouseOut);
				setting.addEventListener("click",settingClick);
				logout.addEventListener("mouseover",logoutMouseOver);
				logout.addEventListener("mouseout",logoutMouseOut);
				logout.addEventListener("click",logoutClick);
			}
		});
		
		function settingMouseOver(){
			setting.src = "../resources/images/icons/settingH.png";
		};
		function settingMouseOut(){
			setting.src = "../resources/images/icons/setting.png";
		};
		function settingClick(){
			window.location.href = "../common/setting.jsp";
		};
		function logoutMouseOver(){
			logout.src = "../resources/images/icons/lgt.png";
		};
		function logoutMouseOut(){
			logout.src = "../resources/images/icons/lgtt.png";
		};
		function logoutClick(){
			window.location.href = "../";
		};
		
	});