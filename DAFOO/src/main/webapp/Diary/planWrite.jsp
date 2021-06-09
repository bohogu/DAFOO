<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>일정등록</title>
  	<link rel="stylesheet" href="../css/jquery-ui.css">
  	<script src="../script/jquery-1.12.4.js"></script>
  	<script src="../script/jquery-ui.js"></script>
  	

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	
  	
  	
</head>
<script>
$( function() {
    $( "#pdate" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
} );
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
	padding:10px; 
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

<form name="frm" method="post" action="planWriteSave.jsp" enctype="multipart/form-data">
<table>
	<caption><h1>일지등록</h3></caption>
	<tr>
		<th width="20%"><h2>날짜</h2></th>
		<td width="80%"><input height="400px;" type="date" name="pdate" id="pdate" style="width:98%;height: 50px;"></td>
	</tr>
	<tr>
		<th><h2>제목</h2></th>
		<td><input type="text" name="title" style="width:98%;height: 50px;"></td>
	</tr>
	<tr>
		<th><h1>내용</h1></th>
		<td><textarea name="content" style="width:98%;height:450px;"></textarea></td>
	</tr>
</table>





<div class="div1">
	<td><input type="file" name="file"></td>
	<button type="submit">저장</button>
	<button type="button" onclick="self.close();">닫기</button>
	
</div>
</form>

</body>
</html>



