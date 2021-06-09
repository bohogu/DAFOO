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
	   sql += "	where  pdate='"+pdate+"' ";
ResultSet rs = stmt.executeQuery(sql);

String title = "";
String content = "";
//System.out.println("a      b"); // --> a b --> &nbsp;
if( rs.next() ) {
	title = rs.getString("title");
	content = rs.getString("content");
	content = content.replace("\n","<br>");  // \n -> <br>
	content = content.replace(" ","&nbsp;"); // (빈공간) -> &nbsp;
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
	<title>일정등록</title>
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

function fn_modify() {
	location="planModify.jsp?pdate=<%=pdate %>";
}

function fn_delete() {
	if( confirm(" 정말 삭제하시겠습니까? ") ) {   // [확인] [취소]
		location = "planDelete.jsp?pdate=<%=pdate %>";
	}
}

</script>

<style>
body {
	font-size:9pt;
	font-family:맑은 고딕;
	color:#333333;
}
table {
	width:670px;
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

<table>
	<caption><h1>일지등록</h3></caption>


<input type="text" width="60%" style="width:98%;height: 50px;"/>
	<tr>
		<th width="20%"><h2>제목</h2></th>
		<td><%=title %></td>
	</tr>
	<tr>
		<th><h1>내용</h1></th>
		<td height="450px" style="width:98%;height:450px;" valign="top"><%=content %></td>
	</tr>
</table>
<div class="div1">
	<button type="button" onclick="fn_modify();">수정</button>
	<button type="button" onclick="fn_delete();">삭제</button>
	<button type="button" onclick="self.close();">닫기</button>
</div>

</body>
</html>


