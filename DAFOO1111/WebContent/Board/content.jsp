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
					<th colspan="4"><h4>${bb.title }</h4></th>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
					<td colspan="3"><h5>${bb.title }</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
					<td colspan="3"><h5>${bb.nick }</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성일</h5></td>
					<td><h5>${bb.date }</h5></td>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
					<td><h5>${bb.hit }</h5></td>
				</tr>
				<tr>
					<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>내용</h5></td>
					<td colspan="3" style="text-align: left; height:300px;"><h5>${bb.content }</h5></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5" style="text-align: right;">
					<%-- <c:if test="${bb.nick eq nick }"> --%>
					<a href="${context}/BoardUpdate.bo?bnum=${bb.bnum}" class="btn btn-primary">수정</a>
					<a href="${context}/BoardDeleteAction.bo?bnum=${bb.bnum}" class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
					<%-- </c:if> --%>
					<a id="list" class="btn btn-primary">목록</a>
					<script type="text/javascript">
						$(function(){
							$("#list").click(function(){
								window.history.back();
							});						
						});
					</script>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
    </section><!-- End Features Section -->




</body>
</html>