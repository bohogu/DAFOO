<%@page import="file.calendarDAO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.*" %>
   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 업로드 처리 서버페이지 fileUploadAction.jsp</title>
</head>
<body>

 <%@ include file="dbCon.jsp" %>

		<%
		String directory = application.getRealPath("/upload/");
			
				int maxSize = 1024 * 1024 * 1024;
				
				MultipartRequest multipartRequest;
				
				
				multipartRequest = new MultipartRequest(request, 
														directory, 
														maxSize,
														new DefaultFileRenamePolicy());
				
			
				String fileName = multipartRequest.getOriginalFileName("file");
				
				String fileRealName = multipartRequest.getFilesystemName("file");
				
				
				new calendarDAO().upload(fileName,fileRealName);
				 
				out.write("업로드한 원본 파일명 : " + fileName + "<br>");
				out.write("업로드한 실제 파일명 : " + fileRealName + "<br>");
		%>





<%
String pdate = request.getParameter("pdate");
String title = request.getParameter("title");
String content = request.getParameter("content");
%>

<%
String sql = "INSERT INTO plan(userid,pdate,title,content) ";
	   sql+= "VALUES('min','"+pdate+"','"+title+"','"+content+"')";
int result = stmt.executeUpdate(sql);
%>

</body>
</html>


<script>
alert("일정 저장완료!");
self.close();
opener.location = "planList.jsp";
</script>

