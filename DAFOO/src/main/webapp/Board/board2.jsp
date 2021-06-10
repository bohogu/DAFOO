<%@ page import="java.util.ArrayList"%>
<%@ page import="board.db.BoardDAO" %>
<%@ page import="board.db.BoardBean" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="context" value="${pageContext.request.contextPath }"/>
	
 	<section id="features" class="padd-section text-center">
		<c:set var="bgroup" value="${requestScope.bgroup}"/>
		<div class="container" data-aos="fade-up">
        	<div class="section-title text-center">
				<c:if test="${bgroup == 2}">
					<h2>공지사항</h2>
				</c:if>
				<c:if test="${bgroup == 1}">
					<h2>자유게시판</h2>
				</c:if>
				<c:if test="${bgroup == 3}">
					<h2>리뷰</h2> 
				</c:if>
				<p class="separator">
					<a href="${context}/BoardList2.bo?bgroup=2" type="button">공지사항</a>
					<a href="${context}/BoardList2.bo?bgroup=1" type="button">자유게시판</a>
					<a href="${context}/BoardList2.bo?bgroup=3" type="button">리뷰</a>
				</p>
			</div>
		<table class="table table-bordered table-hover" style="text-align: center;">
			<thead>
				<tr>
					<th style="background-color: #fafafa; color: #000000;"><h5>제목</h5></th>
					<th style="background-color: #fafafa; color: #000000; width: 100px"><h5>작성자</h5></th>
					<th style="background-color: #fafafa; color: #000000; width: 100px"><h5>작성일</h5></th>
					<th style="background-color: #fafafa; color: #000000; width: 70px"><h5>조회수</h5></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="bb" items="${v}" begin="${page.beginPerPage}" end="${page.beginPerPage + page.numPerPage -1}" step="1">
				<tr>
					<td>
					<a href="${context}/BoardContent.bo?bnum=${bb.bnum}">${bb.title}</a></td>
					<td>${bb.nick}</td>
					<td>${bb.date}</td>
					<td>${bb.hit}</td>
				</tr>
			</c:forEach>
				<tr>
					<td>
						<c:if test="${page.totalBlock > 0 }">
							<c:if test="${nowBlock >0 }">
								<a href="${context}/BoardList2.bo?bgroup=${bgroup}&nowBlock=${page.nowBlock -1}&nowPage=${ (page.nowBlock-1)*page.pagePerBlock}">
				<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-chevron-double-left" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8.354 1.646a.5.5 0 0 1 0 .708L2.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
  <path fill-rule="evenodd" d="M12.354 1.646a.5.5 0 0 1 0 .708L6.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
</svg>
			</a>
							</c:if>
						</c:if>
						
						<c:set var="doneLoop" value="false"/>
						
						<c:forEach var="cnt" begin="0" end="${page.pagePerBlock -1}">
							<c:if test="${(page.nowBlock * page.pagePerBlock) + cnt == totalPage}">
								<c:set var="doneLoop" value="true"/>
							</c:if>
							<a href="${context}/BoardList2.bo?bgroup=${bgroup}&nowBlock=${page.nowBlock}&nowPage=${(page.nowBlock*page.pagePerBlock)+cnt}" style="font-size:25px;">
	 						${(page.nowBlock * page.pagePerBlock)+1+cnt}
							 </a>	
							 <c:if test="${(page.nowBlock*page.pagePerBlock)+1+cnt == totalRecord }">
							 	<c:set var="doneLoop" value="true"/>
							 </c:if>
						</c:forEach>
						<c:if test="${page.totalBlock > page.nowBlock+1}">
							<a href="${context}/BoardList2.bo?bgroup=${bgroup}&nowPage=${(page.nowBlock+1)*page.pagePerBlock}&nowBlock=${page.nowBlock +1}" style="font-size:25px;"><svg xmlns="http://www.w3.org/2000/svg" width="25px" height="25px" fill="currentColor" class="bi bi-chevron-double-right" viewBox="0 0 16 16">
								  <path fill-rule="evenodd" d="M3.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L9.293 8 3.646 2.354a.5.5 0 0 1 0-.708z"/>
								  <path fill-rule="evenodd" d="M7.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L13.293 8 7.646 2.354a.5.5 0 0 1 0-.708z"/>
								</svg>
								</a>
						</c:if>
					</td>
					<td colspan="4" style="text-align: right;">
						<a href="${context}/BoardWrite.bo?bgroup=${bgroup}" type="submit">글쓰기</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
    </section><!-- End Features Section -->




</body>
</html>