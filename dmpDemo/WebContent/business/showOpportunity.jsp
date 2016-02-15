<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../resources/JS/jquery.1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$(document).ready(function(){
			$('#oppo_sub').click(function(){
				if($('#institute_id').val().length < 3){
					alert('id有误');
					return;
				}
				if($('#title').val().length < 3){
					alert('项目名字有误');
					return;
				}
				if($('#facultytype').val().length < 3){
					alert('专业类型有误');
					return;
				}
				
				//提交参数
				 var param = {
					insId:parseInt($('#institute_id').val().trim()),
					title:$('#title').val().trim(),
					facultytype:$('#facultytype').val().trim(), 
					type:$('#type option:selected').text().trim(),
					applyFee:$('#applyFee').val().trim(), 
					link:$('#link').val().trim(),
				 	feeUrl:$('#feeUrl').val().trim(),  
					fee:$('#fee').val().trim(),
					introduce:$('#introduce').val().trim(),
					benefit:$('#benefit').val().trim(),
					attention:$('#attention').val().trim(),
					requirement:$('#requirement').val().trim(), 
					term:$('#term').val().trim(), 
					deadline:$('#deadline').val().trim(),
				};
				
				$.post(
					"opportunity_add.action",
					param,
					function(data){
						alert(data);
						if(data == 'success')
							location.reload(true);
					},
					"text"
					);
			});
			
			$('#oppo_subT').click(function(){				
				//提交参数
				 var param = {
					id:parseInt($('#id').val()),
					t_title:$('#titlet').val().trim(),
					title:$('#titleT').val().trim(),
					t_fee:$('#feet').val().trim(),
					fee:$('#feeT').val().trim(),
					t_introduce:$('#introducet').val().trim(),
					introduce:$('#introduceT').val().trim(),
					t_benefit:$('#benefitt').val().trim(),
					benefit:$('#benefitT').val().trim(),
					t_attention:$('#attentiont').val().trim(), 
					attention:$('#attentionT').val().trim(), 
					t_requirement:$('#requirementt').val().trim(),
					requirement:$('#requirementT').val().trim(),
					t_term:$('#termt').val().trim(), 
					term:$('#termT').val().trim(), 
					t_deadline:$('#deadlinet').val().trim(),
					deadline:$('#deadlineT').val().trim(),
				};
				
				$.post(
						"opportunity_tran.action",
						param,
						function(data){
							alert(data);
							if(data == 'success'){
								location.reload(true);
								document.getElementById("main").style.display = "none";
								document.getElementById("mainT").style.display = "block";
							}
						},
						"text"
					);
			});
			
			$('#oppo_get').click(function(){
				$.post(
						"opportunity_get.action",
						function(data){
							if(data['status'] == -1)
								alert('已经全部翻译完了');
							else{
						
								$('#id').val(data['id']);
								$('#institute_idT').val(data['institute_id']);
								$('#titleT').val(data['title']);
								$('#facultytypeT').val(data['facultytype']);
								$('#typeT').val(data['type']);
								$('#applyFeeT').val(data['applyFee']);
								$('#feeUrlT').val(data['feeUrl']);
								$('#linkT').val(data['link']);
								$('#feeT').val(data['fee']);
								$('#introduceT').val(data['introduce']);
								$('#benefitT').val(data['benefit']);
								$('#attentionT').val(data['attention']);
								$('#requirementT').val(data['requirement']);
								$('#termT').val(data['term']);
								$('#deadlineT').val(data['deadline']);
								
								$('#titlet').val(data['zh_title']);
								$('#feet').val(data['zh_fee']);
								$('#introducet').val(data['zh_introduce']);
								$('#benefitt').val(data['zh_benefit']);
								$('#attentiont').val(data['zh_attention']);
								$('#requirementt').val(data['zh_requirement']);
								$('#termt').val(data['zh_term']);
								$('#deadlinet').val(data['zh_deadline']);
								alert('还剩 ' + data['count'] + ' 个没翻译完  . ');
							}
						},
						"json"
					);
			});
			
			$('.nav_oppo').click(function(){
				if($(this).text() == '录入'){
					document.getElementById("mainT").style.display = "none";
					document.getElementById("main").style.display = "block";
				}else if($(this).text() == '翻译'){
					document.getElementById("main").style.display = "none";
					document.getElementById("mainT").style.display = "block";
				}else{
					alert('something is wrong when click. ');
					return false;
				}
			});	
			
		});
	});
</script>
<style type="text/css">
.header{
	position: absolute;
	top:0px;
	left: 0px;
	height: 66px;
	width: 100%;
	min-width: 700px;
	background-color: #007799;
	border-bottom: 1px solid #005500;
}
.main{
	width:95%;
	margin-top:100px;
	padding-bottom:40px;
	background-color:#bbff00;
	filter:alpha(opacity=80); 
	-moz-opacity:0.8; 
	-khtml-opacity: 0.8; 
	opacity: 0.8;
	border:0px solid #FF4500;
	border-radius: 50px 0px;
}

#main{
	display:none;
}

#institute_id , #applyFee , #facultytype , #typeT , #institute_idT , #applyFeeT , #facultytypeT{
	width:100px;
	height:30px;
}

#link , #feeUrl , #linkT , #feeUrlT{
	width:500px;
	height:30px;
}

.textarea_css{
	margin-left:10px;
	width:800px;
	height:150px;
}

#oppo_sub , #oppo_subT , #oppo_get{
	margin-top:50px;
	margin-left:35%;
	padding-top:20px;
	width:235px;
	height:225px;
	background-color:#666666;
	font-size:50px;
	line-height:225px;
	border-radius:155px 50px 25px 60px;
	
	text-align:center;
	cursor:pointer;
	color:#FFFFFF;
}
#oppo_subT , #oppo_get{
	margin-top:50px;
	margin-left:20%;
    float:left;
    border-radius:15px 130px;
}
    
.nav_oppo{
	width:65px;
	height:50px;
	line-height:50px;
	float:left;
	border-radius:3px;
	background-color:#AAAAAA;
	margin-left:30px;
	margin-top:7px;
	border-right:3px solid #CCEEFF;
	color:#FF00ff;
	text-align:center;
	cursor:pointer;
	font-size:25px;
}


</style>

<title>机会产品</title>
</head>
<body>
<div class="header">
<div class="nav_oppo">翻译</div>
<div class="nav_oppo">录入</div>
</div>

<!-- nav 1 -->
<div class="main" id="main">
<div id="oppo_sub">Duang</div>
<br><br><br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp学校ID:<input id="institute_id" />
&nbsp&nbsp项目名称:<input id="title" />
&nbsp&nbsp专业类型:<input id="facultytype" />
申请费用：<input id="applyFee" />
<br><br>
&nbsp&nbsp&nbsp项目类型:<select id="type">
<option>Visiting</option>
<option>Exchange</option>
<option>Summer school</option>
<option>Research</option>
</select>
项目链接:<input id="link"/>
<br><br>
&nbsp&nbsp&nbsp项目费用详情链接:<input id="feeUrl" />
<br><br><br>
&nbsp&nbsp&nbsp项目费用：<textarea id="fee" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp项目介绍：<textarea id="introduce" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp项目好处：<textarea id="benefit" class="textarea_css"></textarea>

<br><br><br>
&nbsp&nbsp&nbsp注意事项：<textarea id="attention" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp项目要求：<textarea id="requirement" class="textarea_css"></textarea>

<br><br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp学期：<textarea id="term" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp截至日期：<textarea id="deadline" class="textarea_css"></textarea>
</div>

<!-- nav 2 -->
<div class="main" id="mainT">
<div style="border:1px;width:1000px;height:250px;margin:20px 30px;">
<div id="oppo_get" >Get</div>
<div id="oppo_subT">PiaPia</div>
</div>
<br><br><br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp学校ID:<input id="institute_idT" disabled="true"/>
&nbsp&nbsp项目名称:<input id="titleT" />
&nbsp&nbsp(翻译)项目名称:<input id="titlet" />
&nbsp&nbsp专业类型:<input id="facultytypeT" disabled="true"/>
申请费用：<input id="applyFeeT" disabled="true"/>
<br><br>
项目类型:<input id="typeT" disabled="true"/>

项目链接:<input id="linkT" disabled="true"/>
id:<input id="id" disabled="true"/>
<br><br>
&nbsp&nbsp&nbsp项目费用详情链接:<input id="feeUrlT" />
<br><br><br>
&nbsp&nbsp&nbsp项目费用：<textarea id="feeT" class="textarea_css"></textarea>
<br>
&nbsp&nbsp&nbsp(翻译)项目费用：<textarea id="feet" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp项目介绍：<textarea id="introduceT" class="textarea_css"></textarea>
<br>
&nbsp&nbsp&nbsp(翻译)项目介绍：<textarea id="introducet" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp项目好处：<textarea id="benefitT" class="textarea_css"></textarea>
<br>
&nbsp&nbsp&nbsp(翻译)项目好处：<textarea id="benefitt" class="textarea_css"></textarea>

<br><br><br>
&nbsp&nbsp&nbsp注意事项：<textarea id="attentionT" class="textarea_css"></textarea>
<br>
&nbsp&nbsp&nbsp(翻译)注意事项：<textarea id="attentiont" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp项目要求：<textarea id="requirementT" class="textarea_css"></textarea>
<br>
&nbsp&nbsp&nbsp(翻译)项目要求：<textarea id="requirementt" class="textarea_css"></textarea>

<br><br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp学期：<textarea id="termT" class="textarea_css"></textarea>
<br>
(翻译)&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp学期：<textarea id="termt" class="textarea_css"></textarea>
<br><br><br>
&nbsp&nbsp&nbsp截至日期：<textarea id="deadlineT" class="textarea_css"></textarea>
<br>
&nbsp&nbsp&nbsp(翻译)截至日期：<textarea id="deadlinet" class="textarea_css"></textarea>
</div>
</body>
</html>