<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="subHeader" class="subHeader">
	<div id="headerLeftPart" class="headerLeftPart">
		<div id="logo" class="logo">
			<img src="../resources/images/logo.png">
		</div>
	</div>

	<div id="headerRightPart" class="headerRightPart">
		<div id="personalInfo" class="personalInfo">
			<div id="headerRightImgSettingDiv" class="headerRightImgSettingDiv">
			<img id="headerRightImgSetting" class="headerRightImgSetting" src="../resources/images/icons/setting.png" />
			</div>
			<img id="headerRightImgPhoto" class="headerRightImgPhoto" src="../resources/images/pic.png" />
			<span>${session.USER}</span>
			<img id = "headerRightImgLogout" src="../resources/images/icons/lgtt.png" />
		</div>
	</div>
</div>
