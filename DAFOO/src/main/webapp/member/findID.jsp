<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../assets/css/style.css" rel="stylesheet">
</head>
<style>
		#layout{
		margin:0 auto;
		width:100%;
	} 


</style>
<body>

	<table id="layout">
		<tr style="height: 75px;">

			<td><jsp:include page="../inc/top.jsp" /></td>
		</tr>
		<tr>
			<td>
	<div class="limiter"  >
		<div class="container-login100">
						<div class="wrap-login100">
							<div class="login100-form-title">
								<span class="login100-form-title-1"> </span>
							</div>
								<form class="login100-form validate-form"
								action="MemberFindID.me" method="post" novalidate="novalidate">

							<div class="wrap-input100 validate-input m-b-26"
									data-validate="Email is required">
							<span class="label-input100">이메일</span> <input class="input100" 
								type="text" id ="email"  placeholder="이메일을 작성해주세요 " name = "email" >
							<span class="focus-input100"></span>
							</div>
							
								<div>
									<button class="login100-form-btn" type="submit" value="submit"> 아이디 찾기</button>
								</div>
								</form>
							</div>	
						</div>
					</div>	
			</td>
		</tr>
	</table>



</body>
</html>