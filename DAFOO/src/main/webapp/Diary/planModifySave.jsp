<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="dbCon.jsp" %>

<%
String pdate = request.getParameter("pdate");
String title = request.getParameter("title");
String content = request.getParameter("content");
%>

<%
String  sql  = " update plan set title='"+title+"',content='"+content+"' ";
		sql += " 	where  pdate='"+pdate+"'";
	   
int result = stmt.executeUpdate(sql);

%>
<script>
alert("수정완료!");
self.close();
opener.location = "planList.jsp";
</script>
