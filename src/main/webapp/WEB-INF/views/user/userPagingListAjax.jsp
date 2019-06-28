<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.userTr:hover {
	cursor: pointer;
}
</style>
<script>
	$(document).ready(function() {
		// 사용자 tr 태그 이벤트 등록
		$("#userListTbody").on("click", ".userTr", function() {
			console.log("userTr click");
			//userId를 획득하는 방법
			// 			$(this).find(".userId").text();
			// 			$(this).data("userid");

			// 사용자 id를 #userId 값으로 설정해주고
			var userId = $(this).find(".userId").text();
			$("#userId").val(userId);

			//#frm을 이용하여 submit();
			$("#frm").submit();
		})
		
		$("ul").on("click", "li", function(){
			console.log("click");
		})
		
		// 첫번째 페이지의 사용자 정보를 요청
		userPagingListAjax(1, 10);
	})
	

	
// 데이터 응답을 json으로 받는 경우	
function userPagingListAjax(page, pageSize) {
	$.ajax({
		url : "/user/pagingListAjax",
		method : "post",
		data : "page=" + page + "&pageSize=" + pageSize,
		success : function(d) {
			console.log(d);
			// 사용자 리스트
			var html = "";
			d.data.userList.forEach(function(user) {
				// html 생성
				html += "<tr class='userTr' data-userId='" + user.userId + "'>";
				html += "	<td class='userId'>" + user.userId + "</td>";
				html += "	<td>" + user.name + "</td>";
				html += "	<td>" + user.alias + "</td>";
				html += "	<td></td>";
				html += "</tr>";
			})
			
			// 페이지네이션 생성
			var pHtml="";
			console.log(d.pageVO);
			var pageVo = d.pageVO;
			
			if(pageVo.page == 1) {
				 pHtml += "<li class='disabled'><span>«</span></li>";

			} else {
				console.log(pageVo.page)
				  pHtml += "<li><a href='javascript:userPagingListAjax(" + (pageVo.page - 1) + "," + pageVo.pageSize + ")'>«</a></li>";
				 
			}
			
			for( var i = 1; i <= d.data.paginationSize; i++) {
				if(pageVo.page == i) {
					 pHtml += "<li class='active'><span>" + i + "</span></li>"; 

				} else {
					 pHtml += "<li><a href='javascript:userPagingListAjax(" + i +"," + pageVo.pageSize+")'>" + i + "</a></li>";

				}
			}
			
			
			if(pageVo.page == d.data.paginationSize) {
				pHtml += "<li class='disabled'><span>»</span></li>";
			} else {
				pHtml += "<li><a href='javascript:userPagingListAjax(" + (pageVo.page + 1) + "," + pageVo.pageSize + ")'>»</a></li>";
			}
			
// 			console.log("html : ", html);
// 			console.log("pHtml : ", pHtml);
			$("#userListTbody").html(html);
			$(".pagination").html(pHtml);
		}
	});		
}
</script>


<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자(tiles)</h2>


		<!-- 사용자 상세조회 : userId가 필요 -->
		<form id="frm" method="get" action="${cp }/user/user">
			<input type="hidden" id="userId" name="userId" />
		</form>


		<div class="table-responsive">
			<table class="table table-striped">
				
				<thead>
					<tr>
						<th>사용자 아이디</th>
						<th>사용자 이름</th>
						<th>사용자 별명</th>
						<th>등록일시</th>
					</tr>
				</thead>
				
				<tbody id="userListTbody">
				</tbody>
			
			</table>
		</div>

		<form id="downFrm" action="${cp }/user/userListExcel">
			<button type="submit" id="downBtn" class="btn btn-default pull-right">엑셀다운</button>
			<input type="hidden" id="filename" name="filename" value="userList">
		</form>
		<a href="${cp }/user/form" class="btn btn-default pull-right">사용자
			등록</a>


		<div class="text-center">


			<ul class="pagination">

				<%-- 	<%
									// 내가 현재 몇번째 페이지에 있는가?
									PageVO pageVo = (PageVO) request.getAttribute("pageVO");
									int paginationSize = (Integer) request
											.getAttribute("paginationSize");
								%> --%>
			</ul>

		</div>
	</div>
</div>