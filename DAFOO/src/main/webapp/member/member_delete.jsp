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

    <!-- Required meta tags -->

<body>
    <!--::header part start::-->
   
    <!-- Header part end-->
   
	

    <!--================ 메뉴 영역 =================-->
    <section class="cart_area">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="left_sidebar_area">
						<aside class="left_widgets p_filter_widgets">
							<div class="l_w_title"></div>
							<div class="side_menu">
								<h3>마이페이지</h3>
								<ul class="list side_list">					
									<li><a href="MemberInfo.me">My 정보</a></li>
									<li class="active"><a href="MemberDelete.me">회원 탈퇴</a></li>
								</ul>
							</div><!-- .side_menu -->
						</aside>
					</div>
				</div>
				<!--================ 메뉴 영역 =================-->
				<div class="col-lg-9">
					<div class="row align-items-center latest_product_inner">
						<!--         <section class="cart_area"> -->
						<div class="container">
							<div class="cart_inner">
								<h2 class="member_title">회원탈퇴</h2><!-- .member_title -->
								<form action="/member/MemberDeletePro.me" method="post">
			                    	비밀번호 확인 <input type="password" name="pass"> <input type="submit" value="탈퇴" >
			                    </form>
							
							</div>
						</div>
						<!--   </section> -->

					</div>
				</div>
			</div>
		</div>
	</section>
    <!--================End Category Product Area =================-->
	

    <!--::footer_part start::-->
  
    <!--::footer_part end::-->

    
</body>

</html>