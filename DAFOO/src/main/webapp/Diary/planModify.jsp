<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ include file="dbCon.jsp" %>

<%
String pdate = request.getParameter("pdate");
if( pdate == null ) {
%>
	<script>
	alert("잘못된 경로로의 접근");
	self.close();
	</script>
<%
	return;
}

String sql = " select title,content from plan ";
	   sql += "	where pdate='"+pdate+"' ";
ResultSet rs = stmt.executeQuery(sql);

String title = "";
String content = "";

if( rs.next() ) {
	title = rs.getString("title");
	content = rs.getString("content");
} else {
%>
	<script>
	alert("잘못된 경로로의 접근");
	self.close();
	</script>
<%
	return;
}

%>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>일정수정</title>
  	<link rel="stylesheet" href="../css/jquery-ui.css">
  	<script src="../script/jquery-1.12.4.js"></script>
  	<script src="../script/jquery-ui.js"></script>
</head>
<script>
$( function() {
    $( "#pdate" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
} );

function fn_submit() {
	var f = document.frm;
	if( f.pdate.value == "" ) {
		alert("날짜를 입력해주세요.");
		f.pdate.focus();
		return false;
	}
	if( f.title.value == "" ) {
		alert("제목을 입력해주세요.");
		f.title.focus();
		return false;
	}
	f.submit();
}


</script>

<style>
body {
	font-size:9pt;
	font-family:맑은 고딕;
	color:#333333;
}
table {
	width:380px;
	border-collapse:collapse;  /* 셀간격을 없앰 */
}
th,td {
	border:1px solid #cccccc;
	padding:5px; 
}
caption {
	font-size:14pt;
	font-weight:bold;
	margin-bottom:5px;
}
.div1 {
	width:380px;
	text-align:center;
	margin-top:10px;
}

</style>

<body>
<form name="frm" method="post" action="planModifySave.jsp">

	<input type="hidden" name="pdate" value="<%=pdate%>">

<table>
	<caption>일지수정</caption>
	<tr>
		<th width="20%">날짜</th>
		<td width="80%"><%=pdate %></td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
		<input type="text" name="title" value="<%=title %>" style="width:98%;"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td height="120" valign="top">
		<textarea name="content" style="width:98%;height:150px;"><%=content %></textarea></td>
	</tr>
</table>
<div class="div1">
	<button type="submit" onclick="fn_submit();return false;">저장</button>
	<button type="button" onclick="self.close();">닫기</button>
</div>
</form>
</body>
</html>


