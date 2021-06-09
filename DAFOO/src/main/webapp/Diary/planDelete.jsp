 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="dbCon.jsp" %>
<%
String pdate = request.getParameter("pdate");
if( pdate == null ) {
%>
	<script>
	alert("잘못된 경로로의 접근!!");
	self.close();
	</script>
<%
	return;
}
String sql = "DELETE FROM plan WHERE userid='min' AND pdate='"+pdate+"'";
int result = stmt.executeUpdate(sql);  // 삭제완료 : result = 1; 
if( result == 1 ) {
%>
	<script>
	alert("삭제 완료");
	self.close();
	opener.document.location="planList.jsp";
	</script>
<%
} else {
%>
	<script>
	alert("삭제 실패");
	self.close();
	</script>
<%
}
%>


