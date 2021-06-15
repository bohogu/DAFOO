<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
	//session 객체에 저장된 id 값 가져와서 변수에 저장
	String id = (String)session.getAttribute("id");
%>

<!DOCTYPE html>


<html lang="zxx">
<link href="../assets/css/style.css" rel="stylesheet">
<jsp:include page="../inc/top.jsp"></jsp:include>
<head>
<style>
		#layout{
		margin:0 auto;
		width:100%;
	} 


</style>
	<%
		if (id == null) {
	%>
	<script type="text/javascript">
		alert("로그인이 필요합니다.");
		location.href = "MemberLogin.me";
	</script>
	<%
		}
	%>
	
</head>

<body>
   <div class="page-wrapper bg-gra-01 p-t-180 p-b-100 font-poppins">
        <div class="wrapper wrapper--w780">
            <div class="card card-3">
                <div class="card-heading"></div>
                <div class="card-body">
                        <h1 class="title"> 회원탈퇴  </h1>
                       	
                        <div class="p-t-10">
                           <form action="MemberDeletePro.me" method="post">  
                           <input class="input--style-3" type="password" placeholder="비밀번호 확인" name="pass">
                           	<input type="submit" value="탈퇴" class="btn btn--pill btn--red" >					
                           </form>  	 						
                       </div>
                      </div>
  					</div>
  				</div>	
              </div>   
			
			

    
</body>

</html>