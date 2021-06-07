<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String USERID = (String) session.getAttribute("SessionUserId");
if( USERID == null ) {
%>
	<script>
	alert("로그인 이후에 이용가능합니다.");
	location="../member/loginWrite.jsp";
	</script>
<%
	return;
}
%>