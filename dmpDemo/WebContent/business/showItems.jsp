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
<script>
$(function(){
	$(document).ready(function(){
		$('#submit').click(function(){
			var param = {
				id:parseInt($('#idd').val()),
				flag:parseInt($('#flag').val()),	
				analysis:$('#analysis').val(),
				ps:$('#ps').val(),
				rl1:$('#rl1').val(),
				rl2:$('#rl2').val(),
				rl3:$('#rl3').val()
			};
			
			
			$.post(
				"offer_modify.action",
				param,
				function(data){
					alert(data['status']);
					if(data['status'] == 'success')
						location.reload(true);
				},
				"json"
			);
		});
		
		$('#refresh').click(function(){
			var param = 
			{
				marker_s:$('#marker_s option:selected').text()
			};
			$.post(
					"offer_get.action",
					param,
					function(data){
						if(data['status'] == -1)
							alert('这个人录完了！');
						else{
							$('#idd').val(data['id']);
							$('#flag').val(data['flag']);
							$('#marker').val(data['marker']);
							$('#applicant').val(data['applicant']);
							$('#currentschool').val(data['currentschool']);
							$('#gpa').val(data['gpa']);
							$('#toefl').val(data['toefl']);
							$('#gre').val(data['gre']);
							$('#major').val(data['major']);
							$('#research').val(data['research'][0]['content']);
						}
					},
					"json"
				);
		});
	})
});
</script>
<title>offer-守缺</title>
</head>

<body>
<div>
<div style="height:400px;">
<div style="margin-left:30px;float:left;width:1100px;">
	id:<input id="idd" style="width:50px;height:20px;" disabled="true">&nbsp&nbsp
	<input id="flag" style="display:none"/>
	录入者:<input id="marker" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	申请人者:<input id="applicant" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	现在学校:<input id="currentschool" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	<br><br>
	专业:<input id="major" style="width:200px;height:25px;" disabled="true">&nbsp&nbsp
	gpa:<input id="gpa" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	toefl/ielts总分:<input id="toefl" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	gre/gmat总分:<input id="gre" style="width:100px;height:25px;" disabled="true">&nbsp&nbsp
	<br><br>
	科研：<textarea id="research" style="width:800px;height:300px;"></textarea>
	
</div>
<div style="float:left">
<span>查询条件:</span><select id="marker_s">
	<option>xuyue</option>
	<option>xurun</option>
	<option>mochengkang</option>
	<option>fanlei</option>
	<option>jianzhi11</option>
	<option>jianzhi33</option>
	<option>jianzhi66</option>
	<option>jianzhi88</option>
	<option>other</option>
</select>
<div id = "refresh" style="margin-top:100px;width:135px;height:125px;background-color:#666666;font-size:30px;line-height:125px;border-radius:5px;user-select:none;color:#FFFFFF;text-align:center;cursor:pointer" >按一下</div>
</div>
</div>
<br><br><br><br>
<h3>咨询师分析</h3>
<textarea id="analysis" style="width:1300px;height:300px;"></textarea>
<h3>文书 -- PS</h3>
<textarea id="ps" style="width:1300px;height:300px;"></textarea>
<h3>文书 -- RL1</h3>
<textarea id="rl1" style="width:1300px;height:300px;"></textarea>
<h3>文书 -- RL2</h3>
<textarea id="rl2" style="width:1300px;height:300px;"></textarea>
<h3>文书 -- RL3</h3>
<textarea id="rl3" style="width:1300px;height:300px;"></textarea>

<br>
<br>

<input type="button" id="submit" style="width:50px:height:40px;cursor:pointer" value="Submit" ></input>
<br><br>
</div>

</body>
</html>