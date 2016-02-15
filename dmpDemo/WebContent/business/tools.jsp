<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../resources/JS/jquery.1.9.1.min.js" type="text/javascript"></script>
<script>
$(function(){
	$(document).ready(function(){
		$('#tran_sub').click(function(){
			var param = {
				tran_type:parseInt($('#tran_type option:selected').val()),
				tran_cnt:$('#tran_cnt').val()
			}
			
			$.post(
					"tran_add.action",
					param,
					function(data){
						alert(data);
						if(data == 'success')
							location.reload(true);
					},
					"text"
				);
		});
	});
});
</script>
<title>数据小组-smart tools</title>
<style type="text/css">
.div-trans{
	height:800px;
	width:1000px;
	background-color:#ffaaff;
	border:1px #fefefe;
	border-radius:0px 50px 0px;
	
}
#tran_cnt{
	margin:20px 30px;
	width:90%;
	height:40%;
	
}
.div-cnt{
	margin:20px 30px;
	width:90%;
	border: 2px solid #661111;
	background-color: #eeeeee;
}
#tran_type{
	margin-top:35px;
	margin-left:35px;
	width:150px;
	height:50px;
}

#tran_sub{
	margin-top:-50px;
	margin-left:45%;
	width:135px;
	height:125px;
	background-color:#666666;
	font-size:30px;
	line-height:125px;
	border-radius:5px;
	color:#FFFFFF;
	text-align:center;
	cursor:pointer
}
</style>
</head>
<body>
<div class="div-trans">
<div class="div-cnt">
<h4>Description:</h4>
<p>每两行为一对，英文写在前面一行，中文写作后面一行, 如下：</p>
<p>&nbsp&nbspChinese<br>&nbsp&nbsp中国人<br>&nbsp&nbspchinese<br>&nbsp&nbsp瓷器</p>
</div>
<textarea id="tran_cnt"></textarea>
<select id="tran_type">
<option value="1">program</option>
<option value="2">college</option>
<option value="3">school</option>
<option value="4">department</option>
<option value="5">faculty</option>
</select>
<div id="tran_sub">按一下</div>
</div>
</body>
</html>