<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">
<head>

<title>DaFoo 회원가입</title>

<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
 <!-- Template Main CSS File -->
 <link href="../assets/css/style.css" rel="stylesheet">
</head>
<style>
	#layout{
		margin:0 auto;
		width:100%;
	}
	
</style>

</head>

</head>
<!-- 우편번호 검색   -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
		function execPostCode() {
			new daum.Postcode({
				oncomplete: function(data) {
	                
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	
	                
	                if(data.userSelectedType === 'R'){
	                    
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                   
	                    document.getElementById("extraAddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("extraAddress").value = '';
	                }
	
	                
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("address").value = addr;
	               
	                document.getElementById("detailAddress").focus();
	            }
		    }).open();
		}
		</script>


	

<!-- 아이디/비밀번호 검사   -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
		// submit 전 최종 상태(아이디, 패스워드 규칙 일치 여부) 저장할 전역변수 설정
		
		
		
		var checkIdResult = false, checkPasswdResult = false, retryPasswdResult = false, retryEmailResult = false; 
	
		function checkId(idForm) { // 파라미터 this 로 전달된 ID 입력폼을 매개변수에 저장
			var id = idForm.value; // ID 입력폼의 입력값을 가져와서 변수에 저장
			console.log(id);
		
			var element = document.getElementById('checkIdResult');
			
	
			var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/g;

				if(regex.exec(id)) { // 정규표현식과 일치할 경우
				// 아이디 중복 확인()
				$(document).ready(function(){
					$.ajax('CheckId.me',{
						data:{id:$('#myId').val()},
						success:function(rdata){
							$('#checkIdResult').html(rdata);
						}
					});
				});
				
				checkIdResult = true; // 전역변수 true 로 변경
			} else { // 일치하지 않을 경우
				element.innerHTML = "사용 불가";
				checkIdResult = false; // 전역변수 false 로 변경(필수!)
			}
			
		}
		
		
		function checkPasswd(passwdForm) { // 파라미터 this 로 전달된 ID 입력폼을 매개변수에 저장
			var passwd = passwdForm.value; // ID 입력폼의 입력값을 가져와서 변수에 저장
			
		
			var element = document.getElementById('checkPasswdResult');
			
			var lengthRegex = /^[A-Za-z0-9!@#$%]{4,16}$/;
			var upperCaseRegex = /[A-Z]/;
			var lowerCaseRegex = /[a-z]/;
			var digitRegex = /[0-9]/;
			var specRegex = /[!@#$%]/;
			
		
			if(lengthRegex.exec(passwd)) {
				
				var count = 0;
				
				if(upperCaseRegex.exec(passwd)) count++;
				if(lowerCaseRegex.exec(passwd)) count++;
				if(digitRegex.exec(passwd)) count++;
				if(specRegex.exec(passwd)) count++;
				
				if(count == 4) {
					element.innerHTML = "사용 가능(안전)";
					checkPasswdResult = true; // 전역변수 true 로 변경
				} else if(count == 3) {
					element.innerHTML = "사용 가능(보통)";
					checkPasswdResult = true; // 전역변수 true 로 변경
				} else if(count == 2) {
					element.innerHTML = "사용 가능(위험)";
					checkPasswdResult = true; // 전역변수 true 로 변경
				} else {
					element.innerHTML = "사용 불가(두 가지 이상 조합)";
					checkPasswdResult = false; // 전역변수 false 로 변경
				}
	
			} else {
				element.innerHTML = "사용 불가";
				checkPasswdResult = false; // 전역변수 false 로 변경
			}
			
		}
		
		// 비밀번호 재확인
		function retryPasswd(retryPasswdForm) {
			var passwd = document.getElementById('pass').value;
			var retryPasswd = retryPasswdForm.value;
			var element = document.getElementById('retryPasswdResult');
			if (passwd != retryPasswd) {
				element.innerHTML = "비밀번호가 일치하지 않습니다.";
				
			} else {
				element.innerHTML = "";
				retryPasswdResult = true;
				
			}
			
		}
		
		// 이메일 재확인
		function retryEmail(retryEmailForm) {
			var email = document.getElementById('email').value;
			var retryEmail = retryEmailForm.value;
			var element = document.getElementById('retryEmailResult');
			if (email != retryEmail) {
				element.innerHTML = "이메일이 일치하지 않습니다.";
				
			} else {
				element.innerHTML = "";
				retryEmailResult = true;
				
			}
			
		}
		
		// 아이디, 패스워드 정규표현식 체크 후 정상이면 true 리턴(submit), 아니면 false 리턴
		// 비밀번호, 이메일 일치 유무 확인
		function check() {
			if (checkIdResult) {
				if (checkPasswdResult) {
					if (retryPasswdResult) {
						if (retryEmailResult) {
							return true;
							
						} else {
							alert('이메일 일치 확인 필수!');
							return false;
							
						}
						
					} else {
						alert('비밀번호 일치 확인 필수!');
						return false;
						
					}
					
				} else {
					alert('비밀번호 규칙 확인 필수!');
					return false;
					
				}
				
			} else {
				alert('아이디 규칙 확인 필수!');
				return false;
				
			}
			
			
			if(checkIdResult && checkPasswdResult && retryPasswdResult) {
				return true;
			} else {
				alert('아이디 또는 패스워드 규칙 확인 필수!');
				return false;
			}
		}
	
	</script>
<script type="text/javascript">
function mailCheck() {
	var to = document.getElementById("email").value;
	window.open("member/SendMail.jsp?to=" + to, "메일인증 창","width=500, height=500");
	
	}



</script>	
	

</head>

<body>

<jsp:include page="../inc/main.jsp"></jsp:include>

	<table id="layout">
		<tr style="height: 75px;">
			<td><jsp:include page="../inc/top.jsp" /></td>
		</tr>
		<tr>
			<td>
				<!--================main_part Area =================-->
				<div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
					<div class="wrapper wrapper--w960">
						<section class="member_form">

							<div class="card card-2" style="flex-direction: row;">
								<div class="card-heading"></div>
								<div class="card-body" style="font-weight: 400; font-size: 14px;">
									<h2 class="title" style="font-size: 30px;">회원가입</h2>
									<form action="MemberWritePro.me" id="join" method="post"
										name="fr" onsubmit="return check()">
										<div class="input-group">
											아이디 <input class="input--style-2" type="text"
												name="id" class="id" id="myId" required="required"
												placeholder="4~12자 영문과 숫자 조합" onkeyup="checkId(this)">
											<span id="checkIdResult"></span>
										</div>
										<div class="input-group">
											닉네임 <input class="input--style-2" type="text"
												name="nick" id="nick" placeholder="닉네임을 입력해주세요"
												required="required">
										</div>

										<div class="input-group">
											비밀번호 <input class="input--style-2" type="password"
												name="pass" id="pass" required="required"
												placeholder="4~16자 영문(대.소문자),숫자,특수문자"
												onkeyup="checkPasswd(this)"><span
												id="checkPasswdResult"></span>
										</div>
										<div class="input-group">
											비밀번호 재확인 <input class="input--style-2"
												type="password" name="rePass" id="rePass"
												required="required" onkeyup="retryPasswd(this)"><span
												id="retryPasswdResult"></span>
										</div>

										<div class="input-group">
											이름 <input class="input--style-2" type="text"
												name="name" id="name" placeholder="이름을입력해주세요" 
												required="required">
										</div>


										<div class="input-group">
											나이 <input class="input--style-2" type="text"
												name="age" id="age" required="required"
												placeholder="나이를 입력해주세요">

										</div>
									
										<div class="input-group">
											 성별<input type="radio" name="gender" value="male" id="male" required="required">남 
											<input type="radio" name="gender" value="female" id="female">여
										</div>

										


										<div class="input-group">
											키 <input class="input--style-2" type="text"
												name="height" id="height" required="required"
												placeholder="키를 입력해주세요">

										</div>


										<div class="input-group">
											몸무게 <input class="input--style-2" type="text"
												name="weight" id="weight" required="required"
												placeholder="체중을 입력해주세요">

										</div>


										<div class="input-group">
											전화번호<input class="input--style-2" type="text"
												name="phone" required="required"
												placeholder="ex.000-0000-0000">
										</div>

										<div class="input-group">
											이메일 <input class="input--style-2" type="email"
												name="email" id="email" required="required">
										</div>

										<div class="input-group">
											이메일 재확인 <input class="input--style-2" type="email"
												name="reEmail" id="reEmail" required="required"
												onkeyup="retryEmail(this)"><span
												id="retryEmailResult"></span>
										</div>
										<div class="input-group"><input type="button" value="인증요청" onclick="mailCheck();"> </div>
										
										<div class="input-group">
											주소 <input class="input--style-2" type="text"
												name="postcode" id="postcode" class="id" placeholder="우편번호"
												readonly="readonly"> <input type="button"
												value="우편번호검색" onclick="execPostCode()">
										</div>
										<div class="input-group">

											<input class="input--style-2" type="text" name="address"
												id="address" placeholder="주소" size="46" readonly="readonly">
										</div>
										<div class="input-group">

											<input class="input--style-2" type="text"
												name="detailAddress" id="detailAddress" placeholder="상세주소">
											<input type="text" name="extraAddress" id="extraAddress"
												placeholder="참고항목" readonly="readonly">
										</div>
										<div id="buttons">
											<input type="submit" value="가입하기"
												class="btn btn--radius btn--green" style="background: #57b846;  "> <input
												type="reset" value="취소" class="btn btn--radius btn--red" style="background: #ff0000;">
										</div>
									</form>
								</div>
							</div>
						</section>
					</div>
				</div>
				

			</td>
		</tr>
	</table>



</body>

</html>