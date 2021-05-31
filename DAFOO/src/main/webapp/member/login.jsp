 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
	<title>DaFoo 로그인</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

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
				<div class="limiter">
					<div class="container-login100">
						<div class="wrap-login100">
							<div class="login100-form-title"
								style="background-image: url(../MemberCss/images/bg-01.jpg);">
								<span class="login100-form-title-1"> </span>
							</div>

							<form class="login100-form validate-form"
								action="MemberLoginPro.me" method="post" novalidate="novalidate">
								<div class="wrap-input100 validate-input m-b-26"
									data-validate="Username is required">
									<span class="label-input100">아이디</span> <input class="input100"
										type="text" id="name" name="name" placeholder="아이디를 입력해주세요">
									<span class="focus-input100"></span>
								</div>

								<div class="wrap-input100 validate-input m-b-18"
									data-validate="Password is required">
									<span class="label-input100">비밀번호</span> <input
										class="input100" type="password" id="password" name="password"
										placeholder="비밀번호를 입력해주세요"> <span
										class="focus-input100"></span>
								</div>

								<div class="flex-sb-m w-full p-b-30">
									<div class="contact100-form-checkbox">
										<input class="input-checkbox100" id="ckb1" type="checkbox"
											name="remember-me"> <label class="label-checkbox100"
											for="ckb1"> 로그인상태유지 </label>
									</div>

									<div>
										<a href="MemberAgreeForm.me" class="txt1"> 회원가입 </a>
									</div>
								</div>

								<div class="container-login100-form-btn"
									onclick="'location.href= main.jsp'">
									<button class="login100-form-btn" type="submit" value="submit">
										로그인</button>
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