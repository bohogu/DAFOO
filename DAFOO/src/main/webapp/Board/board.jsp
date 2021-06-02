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
					<a href="${context}/BoardList.bo?bgroup=2" type="button">공지사항</a>
					<a href="${context}/BoardList.bo?bgroup=1" type="button">자유게시판</a>
					<a href="${context}/BoardList.bo?bgroup=3" type="button">리뷰</a>
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
			<c:forEach var="bb" items="${requestScope.v }">
				<tr>
					<td>
					<a href="${context}/BoardContent.bo?bnum=${bb.bnum}">${bb.title}</a></td>
					<td>${bb.nick}</td>
					<td>${bb.date}</td>
					<td>${bb.hit}</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5" style="text-align: right;">
						<a href="${context}/BoardWrite.bo?bgroup=${bgroup}" type="submit">글쓰기</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
    </section><!-- End Features Section -->




</body>
</html>