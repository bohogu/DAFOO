<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
	//session 객체에 저장된 id 값 가져와서 변수에 저장
	String id = (String)session.getAttribute("id");
%>

<!DOCTYPE html>


<html lang="zxx">
<link rel="stylesheet" href="../assets/css/base.css">
<link rel="stylesheet" href="../assets/css/layout.css">
<head>

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


<body>

						<!--         <section class="cart_area"> -->
						<div class="container">
							<div class="cart_inner">
								<h2 class="member_title">회원탈퇴</h2><!-- .member_title -->
								<form action="MemberDeletePro.me" method="post">
			                    	비밀번호 확인 <input type="password" name="pass"> <input type="submit" value="탈퇴" class="genric-btn primary circle">
			                    </form>
							
							</div>
						</div>


    
</body>

</html>