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
		<div class="container" data-aos="fade-up">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
				
					<tr>
						<th colspan="6"><h4>${bb.title }</h4></th>
					</tr>
					
					<tr>
						<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
						<td><h5>${bb.nick }</h5></td>
						<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성일</h5></td>
						<td><h5>${bb.date }</h5></td>
						<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
						<td><h5>${bb.hit }</h5></td>
					</tr>
					
					<tr>
						<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>내용</h5></td>
						<td colspan="5" style="text-align: left; height:300px;"><h5>${bb.content }</h5></td>
					</tr>
					
				</thead>
				
				<tbody>
				
					<tr>
						<td colspan="6" style="text-align: right;">
						<a href="#">댓글</a>
						<%-- <c:if test="${bb.nick eq nick }"> --%>
						<a href="${context}/BoardUpdate.bo?bnum=${bb.bnum}">수정</a>
						<a href="${context}/BoardDeleteAction.bo?bnum=${bb.bnum}&bgroup=${bb.bgroup}" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
						<%-- </c:if> --%>
						<a href="${context}/BoardList.bo?bgroup=${bb.bgroup}" id="list">목록</a>
						</td>
					</tr>
					
					<c:if test="${!empty rb.num}">
					<tr>
						<td colspan="1" style="width: 110px; text-align: center;"><h5>${rb.nick }</h5></td>
						<td colspan="4"><h5>${rb.content }</h5>
						<c:if test="${rb.nick eq nick }">
							<label style="float: right; border: none;" for="rup" type="button">수정</label>
							<label style="float: right; border: none;" for="rdel" type="button" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</label>
						</c:if>
						</td>
						<td colspan="1" style="width: 110px; text-align: center;"><h5>${rb.date }</h5></td>
					</tr>
					</c:if>
					<tr>
						<td style="width:110px;"><h5>${bb.nick }</h5></td>
						<td colspan="6">
							<div style="text-align: left" contenteditable="true" dir="auto" aria-label="댓글 작성"></div>
						</td>
					</tr>
					<tr style="text-align: right">
						<td colspan="6">
							<input type="submit" value="작성">
							<input type="reset" value="취소">
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
	</section>
</body>
</html>