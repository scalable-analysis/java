<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../resources/CSS/frame.css" type="text/css" rel="stylesheet">
<link href="../resources/CSS/left.css" type="text/css" rel="stylesheet">
<link href="../resources/CSS/header.css" type="text/css" rel="stylesheet">
<link href="../resources/CSS/footer.css" type="text/css" rel="stylesheet">
<script src="../resources/JS/jquery.1.9.1.min.js" type="text/javascript"></script>
<script src="../resources/JS/MyJS/common.js" type="text/javascript"></script> 
<script src="../resources/JS/MyJS/left.js" type="text/javascript"></script> 


</head>
<body>
<div id="header" class="header"></div>
	<jsp:include page="../common/header.jsp"/>


<div id="container" class="container">
      <jsp:include  page="../common/left.jsp"/>


<div id="contentBackground" class="contentBackground"></div>
      <div id="content" class="content">
      <iframe id="iframe" class="iframe"></iframe>
      </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>


<script type="text/javascript">
	var cWidth = document.body.clientWidth;
	document.getElementById("header").style.width =  document.body.clientWidth + 'px';
	document.getElementById("content").style.width = (document.body.clientWidth - 185) + 'px';
	document.getElementById("contentBackground").style.width = (document.body.clientWidth - 185) + 'px';
	document.getElementById("footer").style.width = document.body.clientWidth + 'px';
	document.getElementById("iframe").src = "../business/showWelcome.jsp"
 </script>
</html>