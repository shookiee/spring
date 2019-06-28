<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
$(document).ready(function(){
	// select box option 설정
<%--	$("#lang").val("${param.lang}")--%>
	$("#lang").val("${lang}")
	
	$("#lang").on("change", function(){
// 		console.log("select box changed");
		$("#frm").submit();
	});
});
</script>

<%-- lang : ${param.lang } --%>

<form id="frm" action="/locale/view" method="post">
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
	</select>
</form>

GREETING : <spring:message code="GREETING"/> !_! <br>
VISITOR : <spring:message code="VISITOR">
					<spring:argument><mark>brown</mark></spring:argument>
				</spring:message>