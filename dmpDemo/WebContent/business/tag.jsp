<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../resources/CSS/contentCSS/contentCommon.css"
	type="text/css" rel="stylesheet">
<link href="../resources/CSS/contentCSS/showActAny.css" type="text/css"
	rel="stylesheet">
<script src="../resources/JS/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="../resources/JS/jquery.1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(document).ready(function(){
			$('#oppo_sub').click(function(){
				
				var lche = document.getElementsByName("languageTag");
				var iche = document.getElementsByName("innerTag");
				var oche = document.getElementsByName("outterTag");
				var lchec = "";
				var ichec = "";
				var ochec = "";
				for(var i = 0 ; i < lche.length ; i++){
					if(lche[i].checked == true)
						lchec += lche[i].value +",";
				}
				for(var i = 0 ; i < iche.length ; i++){
					if(iche[i].checked == true)
						ichec += iche[i].value +",";
				}
				for(var i = 0 ; i < oche.length ; i++){
					if(oche[i].checked == true)
						ochec += oche[i].value +",";
				}
				if($('#othCmpy').val() != 'other' && $('#othCmpy').val().length > 2)
					ichec += $('#othCmpy').val().trim()+",";
				if($('#othCmpyI').val() != 'other' && $('#othCmpyI').val().length > 2)
					ichec += $('#othCmpyI').val().trim()+",";
				
				
				var param = {
					flag:parseInt($('#flag').val()),
					cv:parseInt($('#cv').val()),
					id:$('#idd').val(),
					lchec:lchec,
					ichec:ichec,
					ochec:ochec,
				}
				$.post(
					"offer_tag.action"	,
					param,
					function(data){
						alert(data);
						if(data == 'success')
							location.reload(true);
					},
					"text"
					);
				
			});
			
			$('#refresh').click(function(){
				$.post(
						"offer_tagGet.action",
						function(data){
							if(data['status'] == -1)
								alert('已经录完了');
							else{
								$('#idd').val(data['id']);
								$('#applicant').val(data['applicant']);
								$('#currentschool').val(data['currentschool']);
								$('#gpa').val(data['gpa']);
								$('#toefl').val(data['toefl']);
								$('#gre').val(data['gre']);
								$('#major').val(data['major']);
								$('#flag').val(data['flag']);
								$('#cv').val(data['cv']);
								$('#rl1').val(data['rl1']);
								$('#rl2').val(data['rl2']);
								$('#rl3').val(data['rl3']);
							}
						},
						"json"
					);
			});
		});
	});
</script>
<style type="text/css">

#oppo_sub , #refresh{
	margin-top:5px;
	margin-left:200px;
	margin-bottom:5%;
	width:235px;
	height:225px;
	line-height:225px;
	background-color:#FFC8B4;
	cursor:pointer;
	font-size:55px;
	color:white;
}
#oppo_sub{
	float:left;
	border-radius:150px 15px;
	
}
#refresh{
	float:left;
	border-radius:100px 15px 3px 20px;
	
}
</style>
</head>
<body>
<div style="border:1px;width:1000px;height:250px;margin:20px 30px;">
<div id = "refresh">&nbspRefresh</div>
<div id="oppo_sub" >&nbspSubmit</div>
</div>
<div id="main">

	<div style="margin-left:30px;width:1100px;border:1px solid;padding:10px;margin-top:50px 30px;">
	id:<input id="idd" style="width:50px;height:20px;" disabled="true">&nbsp&nbsp
	<input id="flag" style="display:none"/>
	cv:<input id="cv"/ style="width:50px;height:20px;" disabled="true">
	现在学校:<input id="currentschool" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	<br><br>
	专业:<input id="major" style="width:200px;height:25px;" disabled="true">&nbsp&nbsp
	gpa:<input id="gpa" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	toefl/ielts总分:<input id="toefl" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	gre/gmat总分:<input id="gre" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	<br><br><br>
	<h3>文书 -- RL1</h3>
<textarea id="rl1" style="width:1300px;height:300px;"></textarea>
<h3>文书 -- RL2</h3>
<textarea id="rl2" style="width:1300px;height:300px;"></textarea>
<h3>文书 -- RL3</h3>
<textarea id="rl3" style="width:1300px;height:300px;"></textarea>
	<br><br><br>
	标签：<br>
	<input type="checkbox" name="languageTag" value="高GPA">高GPA</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="低GPA">低GPA</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="高语言成绩">高语言成绩</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="低语言成绩">低语言成绩</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="高toefl/ielts">高toefl/ielts</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="低toefl/ielts">低toefl/ielts</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="高gre/gmat">高gre/gmat</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="languageTag" value="低gre/gmat">低gre/gmat</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br>
	
	<input type="checkbox" name="outterTag" value="海外顶尖本科">海外顶尖本科</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="海外一般本科">海外一般本科</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="985高校">985高校</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="211高校">211高校</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="行业领域学校">行业领域学校</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="普通一本">普通一本</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="普通二本">普通二本</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="非211非985高校">非211非985高校</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="国际活动经历">国际活动经历</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="校园活动狂人">校园活动狂人</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="志愿者达人">志愿者达人</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="海外交换经历">海外交换经历</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="海外项目实习">海外项目实习</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="世界500强实习">世界500强实习</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="BAT实习">BAT实习</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国内知名企业实习">国内知名企业实习</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="普通企业实习">普通企业实习</input>&nbsp&nbsp&nbsp&nbsp
	<input type="text" id="othCmpyI" value="other"/>
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="海外工作经历">海外工作经历</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="世界500强工作">世界500强工作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="BAT工作">BAT工作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国内知名企业工作">国内知名企业工作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="普通企业工作">普通企业工作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="text" id="othCmpy" value="other"/>
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="奖学金霸主">奖学金霸主</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国家级奖学金">国家级奖学金</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="省级奖学金">省级奖学金</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="校级奖学金">校级奖学金</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="科研能力强">科研能力强</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国际级实验室/项目">国际级实验室/项目</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国家级实验室/项目">国家级实验室/项目</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="省级实验室/项目">省级实验室/项目</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="校级实验室/项目">校级实验室/项目</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="SCI一作">SCI一作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="SCI二作">SCI二作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="SCI三作">SCI三作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="SCI四作">SCI四作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="EI一作">EI一作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="EI二作">EI二作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="EI三作">EI三作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="优秀毕业生论文">优秀毕业生论文</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国内核心一作">国内核心一作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="非核心一作二作">非核心一作二作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="非核心三作四作">非核心三作四作</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="基本实现目标，顺利结题">基本实现目标，顺利结题</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="未有阶段性结果，草草结尾">未有阶段性结果，草草结尾</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="竞赛狂魔">竞赛狂魔</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国际级竞赛">国际级竞赛</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="国家级竞赛">国家级竞赛</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="省级竞赛">省级竞赛</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="校级竞赛">校级竞赛</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="outterTag" value="牛人推荐信">国外牛人教授推荐信</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="国内牛人推荐信">国内牛人推荐信</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="牛人推荐信">校内教授推荐信</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="outterTag" value="讲师推荐信">讲师推荐信</input>&nbsp&nbsp&nbsp&nbsp
	
	<br><br><br>
	
	<input type="checkbox" name="innerTag" value="跨专业申请">跨专业申请</input>&nbsp&nbsp&nbsp&nbsp
	<input type="checkbox" name="innerTag" value="个人有上线产品">个人有上线产品</input>&nbsp&nbsp&nbsp&nbsp

	<br><br><br>
</div>
</div>
</body>
</html>
