<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String member_id = (String) session.getAttribute("id");

	
%>

<!DOCTYPE html>
<html lang="zxx">

<head>
<style type="text/css">
.oldprice {
	text-decoration: line-through;
	color: #BDBDBD;
}
</style>

<%
	if (member_id == null) {
%>
<script type="text/javascript">
	alert("로그인이 필요합니다.");
	location.href = "MemberLogin.me";
</script>
<%
	}
%>


<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>DAFOO</title>
<link href="../assets/css/style.css" rel="stylesheet">
<jsp:include page="../inc/top.jsp"></jsp:include>
</head>
<style>
		#layout{
		margin:0 auto;
		width:100%;
	} 


</style>
<body>
   <div class="page-wrapper bg-gra-01 p-t-180 p-b-100 font-poppins">
        <div class="wrapper wrapper--w780">
            <div class="card card-3">
                <div class="card-heading"></div>
                <div class="card-body">
                        <h1 class="title">마이 페이지 </h1>
                       	<div class="p-t-10">
                            <a href="MemberInfo.me"><button  class="btn btn--pill btn--yello" type="submit"> 내정보 </button></a>
                            
                        </div>
                        <div class="p-t-10">
                            <a href="MemberDelete.me"><button class="btn btn--pill btn--blue" type="submit"> 회원탈퇴 </button></a>
                            
                       </div>
                      </div>
  					</div>
  				</div>	
              </div>          		
                        		



</body>

</html>