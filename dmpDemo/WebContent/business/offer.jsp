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
		
			
			if($('#major').val() == -1 && $('#depOther').val().trim().length < 2){
				alert("请选择专业!")
				return false;
			}
			
			
			//基本信息
			var marker = $('#marker option:selected').val();
			var applicant = "无";
			if($('#name').val().trim().length > 1)
				applicant = $('#name').val().trim();
			var school = $('#school').val().trim();
			var year = $('#year option:selected').text();
			var term = $('#term option:selected').text();
			var degree = $('#degree').val().trim();
			var dep= $('#major option:selected').val();
			var depOther = $('#depOther').val().trim();
			var major = $('#major option:selected').text().split('--')[0];
			
			//三围考试成绩
			
			var gpa = $('#gpa').val().trim();
			if(gpa.length>0 && isNaN(gpa)){
				alert("gpa 需要输入数值型！");
				return false;
			}
			if(isNaN($('#ti_total').val().trim()) || isNaN($('#ti_r').val().trim()) || isNaN($('#ti_l').val().trim()) || isNaN($('#ti_s').val().trim()) || isNaN($('#ti_w').val().trim())){
				alert("托福/雅思 需要输入数值型！")
				return false;
			}
			var ti = "toefl";
			if($('input[name="ti"]:checked').val() == 'ielts' )
				ti = "ielts";
			var ti_total = $('#ti_total').val().trim();
			var ti_l = $('#ti_l').val().trim();
			var ti_s = $('#ti_s').val().trim();
			var ti_r = $('#ti_r').val().trim();
			var ti_w = $('#ti_w').val().trim();
			
			
			if(isNaN($('#gg_total').val().trim()) || isNaN($('#gg_v').val().trim()) || isNaN($('#gg_q').val().trim())){
				alert("gre/gmat 需要输入数值型！")
				return false;
			}
			var gg = "gre";
			if($('input[name="gg"]:checked').val() == 'gmat' )
				gg = "gmat";
			var gg_total = $('#gg_total').val().trim();
			var gg_v = $('#gg_v').val().trim();
			var gg_q = $('#gg_q').val().trim();
			var gg_aw = $('#gg_aw').val().trim();
			
			var result_count = $('#result_count').val();
		
			var sch_result = "";
			if($('#result_count').val() == 0 || $('#result_count').val().trim().length < 1){
				alert("请填写正确的录取结果学校数量!")
				return false;
			}
			
			for(var i = 1 ; i < parseInt(result_count)+1 ; i++){
				var schhh = $('#nsch'+i).val().trim();
				var rschh = $('#rsch'+i+' option:selected').val();
				
				if(i == result_count)
					sch_result += (schhh+"---"+rschh);
				else
					sch_result += (schhh+"---"+rschh+"&&&&");
			}
		
			var param = {
					applicant:applicant,
					marker:marker,
					school:school,
					year:year,
					term:term,
					degree:degree,
					major:major,
					dep:dep,
					depOther:depOther,
					gpa:gpa,
					ti:ti,
					ti_total:ti_total,
					ti_l:ti_l,
					ti_w:ti_w,
					ti_r:ti_r,
					ti_s:ti_s,
					gg:gg,
					gg_total:gg_total,
					gg_v:gg_v,
					gg_q:gg_q,
					gg_aw:gg_aw,
					result_count:result_count,
					sch_result:sch_result,
					background:$('#background').val(),
					drea1:$('#drea1').val().trim(),
					brea1:$('#brea1').val().trim(),
					crea1:$('#crea1').val().trim(),
					drea2:$('#drea2').val().trim(),
					brea2:$('#brea2').val().trim(),
					crea2:$('#crea2').val().trim(),
					drea3:$('#drea3').val().trim(),
					brea3:$('#brea3').val().trim(),
					crea3:$('#crea3').val().trim(),
					dcmp1:$('#dcmp1').val().trim(),
					bcmp1:$('#bcmp1').val().trim(),
					ccmp1:$('#ccmp1').val().trim(),
					dcmp2:$('#dcmp2').val().trim(),
					bcmp2:$('#bcmp2').val().trim(),
					ccmp2:$('#ccmp2').val().trim(),
					dcmp3:$('#dcmp3').val().trim(),
					bcmp3:$('#bcmp3').val().trim(),
					ccmp3:$('#ccmp3').val().trim(),
					dins1:$('#dins1').val().trim(),
					bins1:$('#bins1').val().trim(),
					cins1:$('#cins1').val().trim(),
					dins2:$('#dins2').val().trim(),
					bins2:$('#bins2').val().trim(),
					cins2:$('#cins2').val().trim(),
					dins3:$('#dins3').val().trim(),
					bins3:$('#bins3').val().trim(),
					cins3:$('#cins3').val().trim(),
					dwork1:$('#dwork1').val().trim(),
					bwork1:$('#bwork1').val().trim(),
					cwork1:$('#cwork1').val().trim(),
					dwork2:$('#dwork2').val().trim(),
					bwork2:$('#bwork2').val().trim(),
					cwork2:$('#cwork2').val().trim(),
					dwork3:$('#dwork3').val().trim(),
					bwork3:$('#bwork3').val().trim(),
					cwork3:$('#cwork3').val().trim()
			};
			//alert("执行到这一步");
			 $.post("offer_add.action",
					  param,
					  function(data,status){
					    alert("Status: " + status);
					    location.reload(true);
				}); 
		});
		
	});
})
</script>
<title>offer</title>
</head>

<body>

<div style="overflow-y:auto">
<span>录入者:</span><select id="marker">
	<option>xuyue</option>
	<option>xurun</option>
	<option>fanlei</option>
	<option>jianzhi11</option>
	<option>jianzhi22</option>
	<option>jianzhi33</option>
	<option>jianzhi44</option>
	<option>jianzhi55</option>
	<option>jianzhi66</option>
	<option>jianzhi77</option>
	<option>jianzhi88</option>
	<option>jianzhi99</option>
	<option>jianzhi110</option>
	<option>jianzhi111</option>
	<option>jianzhi112</option>
	<option>jianzhi113</option>
	<option>jianzhi114</option>
</select>&nbsp&nbsp&nbsp&nbsp&nbsp
<span>真名:</span><input id="name" placeholder="无"  style="width:120px;height:30px;margin-right:25px;"/>
<span>现在就读学校:</span><input id="school" style="width:200px;height:30px;margin-right:25px;"/>
<span>年份:</span><select id="year">
	<option>2010</option>
	<option>2007</option>
	<option>2008</option>
	<option>2009</option>
	<option>2011</option>
	<option>2012</option>
	<option>2013</option>
	<option>2014</option>
	<option>2015</option>
	<option>2016</option>
</select>
<span>学期:</span>
<select id="term">
	<option>Fall</option>
	<option>Spring</option>
	<option>Summer</option>
	<option>Winter</option>
</select>
<br>
<span>学位:</span><input type="text" placeholder="MS" id="degree" style="width:100px;height:25px;"/>
<br><br>
<span>专业:</span>
<select id="major">
<option value="﻿-1">unknown--未知</option>
<option value="﻿3">pharmacy--药学</option>
<option value="2">fine_arts--艺术学</option>
<option value="1">public_management_administration--政府公共管理学</option>
<option value="4">anthropology--人类学</option>
<option value="6">mechanical_engineering--机械工程</option>
<option value="5">computer_engineering--计算机工程</option>
<option value="7">accounting--会计学</option>
<option value="8">architecture--建筑学</option>
<option value="9">religion--宗教学</option>
<option value="11">psychology--心理学</option>
<option value="10">materials--材料工程和科学</option>
<option value="12">political_science--政治学</option>
<option value="13">medicine--医学</option>
<option value="14">biology--生命科学</option>
<option value="16">philosophy--哲学</option>
<option value="15">nuclear_engineering--核能工程</option>
<option value="17">agriculture--农学</option>
<option value="18">literature--文学</option>
<option value="19">environmental_science--环境科学</option>
<option value="20">supply_chain_and_logistics--物流管理学</option>
<option value="21">criminology--犯罪学</option>
<option value="22">health--卫生学</option>
<option value="23">biomedical_engineering--生命工程</option>
<option value="24">social_work--社会工作学</option>
<option value="25">public_policy_analysis--公共政策分析学</option>
<option value="26">public_health--公共卫生学</option>
<option value="27">economics--经济学</option>
<option value="28">language--语言文学</option>
<option value="29">english--英语</option>
<option value="30">management--管理学</option>
<option value="31">actuarial_science--精算学</option>
<option value="32">financial_engineering--金融工程</option>
<option value="33">physics--物理</option>
<option value="34">sociology--社会学</option>
<option value="35">biological_agricultural_engineering--农业工程</option>
<option value="36">education--教育学</option>
<option value="37">mathematics--数学</option>
<option value="38">computer_science--计算机科学</option>
<option value="39">statistics--统计学</option>
<option value="40">electrical_engineering--电机/电子工程</option>
<option value="41">public_affairs--公共事务学</option>
<option value="42">aerospace--航空航天工程和科学</option>
<option value="43">law--法学</option>
<option value="44">geosciences--地球科学</option>
<option value="45">environmental_engineering--环境工程</option>
<option value="47">marketing--市场营销学</option>
<option value="46">communication--大众传媒与新闻学</option>
<option value="49">strategy--战略管理学</option>
<option value="50">epidemiology--流行病学</option>
<option value="48">urban_planning--城市规划</option>
<option value="51">civil_engineering--土木工程</option>
<option value="52">information_systems--信息系统学</option>
<option value="53">mba--工商管理学</option>
<option value="54">chemical_engineering--化学工程</option>
<option value="55">entrepreneurship--创业学</option>
<option value="56">history--历史学</option>
<option value="57">industrial_engineering--工业工程/运筹学</option>
<option value="58">chemistry--化学</option>
<option value="60">international_business--国际贸易</option>
<option value="61">finance--金融学</option>
<option value="59">human_resource_management--人力资源管理</option>
<option value="62">operations--运筹管理学</option>
<option value="63">area_studies--区域研究学</option>
<option value="64">dentistry--牙医学</option>
<option value="65">Veterinary Science--兽医学</option>
<option value="66">journalism--新闻传媒</option>
<option value="67">public_relations--公共关系</option>
<option value="68">tesol--对外英语教学</option>
</select>
<input id="depOther" style="width:150px;height:25px;" />
<br><br>
<h3>成绩</h3>
<div>
	<span>GPA:</span><input id="gpa" style="width:50px;height:25px;" />
</div>
<br>
<div>
	<input type="radio" name="ti" value="toefl" id="toefl" checked="checked">托福</input>
	<input type="radio" name="ti" value="ielts" id="ielts" >雅思</input>
	&nbsp&nbsp&nbsp&nbsp&nbsp总分：<input type="text" id="ti_total" style="width:50px;height:20px;margin-left:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbspreading：<input type="text" id="ti_r" style="width:50px;height:20px;margin-right:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbspwriting：<input type="text" id="ti_w" style="width:50px;height:20px;margin-right:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbspspeaking：<input type="text" id="ti_s" style="width:50px;height:20px;margin-right:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbsplistening：<input type="text" id="ti_l" style="width:50px;height:20px;margin-right:10px;">
</div>
<br>
<div>
	<input type="radio" name="gg" value="gre" id="gre" checked="checked">GRE</input>
	<input type="radio" name="gg" value="gmat" id="gmat" >GMAT</input>
	&nbsp&nbsp&nbsp&nbsp&nbsp总分：<input type="text" id="gg_total" style="width:50px;height:20px;margin-left:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbspv：<input type="text" id="gg_v" style="width:50px;height:20px;margin-right:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbspq：<input type="text" id="gg_q" style="width:50px;height:20px;margin-right:10px;">
	&nbsp&nbsp&nbsp&nbsp&nbspaw：<input type="text" id="gg_aw" style="width:50px;height:20px;margin-right:10px;">
</div>

<h3>背景</h3>
<textarea id="background" style="width:700px;height:250px;"></textarea>


<h3>录取学校结果</h3>
<span>数量:</span><input id="result_count" value="8"/>
<div>
	<span>学校:&nbsp</span><input id="nsch1"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch1" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch2"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch2" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch3"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch3" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch4"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch4" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch5"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch5" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch6"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch6" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch7"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch7" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch8"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch8" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch9"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch9" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch10"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch10" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch11"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch11" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<span>学校:&nbsp</span><input id="nsch12"  style="width:450px;height:25px; margin-bottom:3px;"/>
	<span>结果:&nbsp</span><select id="rsch12" style="padding-bottom:2px;"><option value="0">offer</option><option value="1">ad</option><option value="2">reject</option></select>
	<br/>
	<br/>
</div>



<br>
<br>
<h3>科研</h3>
<div style="border:1px solid #aaaaaa">
	<span>&nbsp&nbsp时间:</span><input id="drea1" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="brea1" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="crea1" style="margin-top:10px;"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="drea2" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="brea2" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="crea2"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="drea3" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="brea3" style="width:400px;height:30px;margin-bottom:10px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="crea3"></textarea>
	
	<br>
</div>

<h3>实习</h3>
<div style="border:1px solid #aaaaaa">
	<span>&nbsp&nbsp时间:</span><input id="dins1" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bins1" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="cins1" style="margin-top:10px;"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="dins2" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bins2" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="cins2"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="dins3" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bins3" style="width:400px;height:30px;margin-bottom:10px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="cins3"></textarea>
	
	<br>
</div>

<h3>竞赛</h3>
<div style="border:1px solid #aaaaaa">
	<span>&nbsp&nbsp时间:</span><input id="dcmp1" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bcmp1" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="ccmp1" style="margin-top:10px;"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="dcmp2" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bcmp2" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="ccmp2"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="dcmp3" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bcmp3" style="width:400px;height:30px;margin-bottom:10px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="ccmp3"></textarea>
	
	<br>
</div>

<h3>工作&活动Activity</h3>
<div style="border:1px solid #aaaaaa">
	<span>&nbsp&nbsp时间:</span><input id="dwork1" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bwork1" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="cwork1" style="margin-top:10px;"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="dwork2" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bwork2" style="width:400px;height:30px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="cwork2"></textarea>
	
	<br>
	
	<span>&nbsp&nbsp时间:</span><input id="dwork3" style="width:150px;height:30px;"/>
	<span>&nbsp&nbsp内容:</span><input id="bwork3" style="width:400px;height:30px;margin-bottom:10px;"/>
	<span>&nbsp&nbsp详情:</span><textarea id="cwork3"></textarea>
	
	<br>
</div>
<br>

<input type="button" id="submit" style="width:50px:height:40px;cursor:pointer" value="Submit" ></input>
<br><br>
</div>

</body>
</html>