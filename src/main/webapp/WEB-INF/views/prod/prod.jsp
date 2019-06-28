<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>LPROD 상세조회</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<script>
$(document).ready(function(){

	
})
</script>

</head>

<body>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">  

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${prod_name } 거래처 정보</h2>

						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>거래처코드</th>
									<th>거래처명</th>
									<th>거래처 주소</th>
								</tr>



								<c:forEach items="${buyerList }" var="buyer">
									<tr>
										<td>${buyer.buyer_id}</td>
										<td>${buyer.buyer_name}</td>
										<td>${buyer.buyer_addr1}</td>
									</tr>
								</c:forEach>
							</table>
					



					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>