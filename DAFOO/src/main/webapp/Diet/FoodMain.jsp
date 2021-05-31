<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="../assets/img/favicon.png" rel="icon">
<link href="../assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Roboto:100,300,400,500,700|Philosopher:400,400i,700,700i"
	rel="stylesheet">

<link href="../assets/vendor/aos/aos.css" rel="stylesheet">
<link href="../assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="../assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="../assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<link href="../assets/css/style.css" rel="stylesheet">



<style type="text/css">
	#container {
		width: 70%;
		margin: 75px auto;
		padding: 20px;
		border: 1px solid #bcbcbc;
	}
	
	#pick {
		padding: 20px;
		border: 1px solid #bcbcbc;
	}
	
	#search {
		padding: 10px;
		width: 80%;
		border: 1px solid #bcbcbc;
	}
	
	#slide {
		padding: 10px;
		border: 1px solid #bcbcbc;
	}
	
	#breakfast-subject, #lunch-subject, #dinner-subject {
		padding: 10px;
		width: 15%;
		border: 1px solid #bcbcbc;
		float: left;
		text-align: center;
	}
	
	#breakfast-menu, #breakfast-nutrient, #lunch-menu, #lunch-nutrient,
		#dinner-menu, #dinner-nutrient {
		padding: 10px;
		width: 40%;
		border: 1px solid #bcbcbc;
		float: left;
		margin-bottom: 10px;
	}
	
	#status {
		clear: both;
	}
	
	#status, #dashboard {
		padding: 10px;
		border: 1px solid #bcbcbc;
	}
	
	#feedback {
		padding: 10px;
		height: 150px;
		border: 1px solid #bcbcbc;
	}
	
	#menuRecommend {
		padding: 10px;
		height: 400px;
		border: 1px solid #bcbcbc;
	}
	
	#startDate {
		border: 0;
		outline: 0;
		width: auto;
		height: auto;
		background-color: white;
		font-family: 굴림;
		font-size: 40pt;
		line-height: 200%;
	}
	
	#button1, #button2, #button3 {
		border: 0;
		outline: 0;
		width: auto;
		height: auto;
		background-color: white;
		font-family: 굴림;
		font-size: 20pt;
		line-height: 200%;
	}
	
	form input {
		margin: 0 80px;
	}
	
	#food {
		padding: 10px;
		width: 40%;
		height: 200px;
		border: 1px solid #bcbcbc;
		float: right;
		text-align: center;
		
	}
	
	
	#food2 {
		padding: 10px;
		width: 100%;
		height: 170px;
		border: 1px solid #bcbcbc;
		float: left;
		text-align: left;
		
	
	}
	
	 #index-breakfast {
	  margin-top: 200px;
	 
	 }	
	 
</style>
<title></title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		// 아침, 점심, 저녁 중 하나를 체크하는 함수
		function setColor(btn, color) {
		    var buttonID = document.getElementById(btn);
		    var btn1 = document.getElementById("button1");
		    var btn2 = document.getElementById("button2");
		    var btn3 = document.getElementById("button3");
		    
		    var hiddenValue = document.getElementById("time");
		    
		    if (btn == 1) {
		       hiddenValue.value = btn;
		       alert(hiddenValue.value);
		         btn1.style.backgroundColor = "yellow";
		         btn2.style.backgroundColor = "";
		         btn3.style.backgroundColor = "";
		    }else if(btn == 2) {
		       hiddenValue.value = btn;
		       alert(hiddenValue.value);
		         btn2.style.backgroundColor = "yellow";
		         btn1.style.backgroundColor = "";
		         btn3.style.backgroundColor = "";
		   }else if(btn == 3) {
		      hiddenValue.value = btn;
		      alert(hiddenValue.value);
		        btn3.style.backgroundColor = "yellow";
		        btn1.style.backgroundColor = "";
		        btn2.style.backgroundColor = "";
		   }
		}	// (끝) 아침, 점심, 저녁 중 하나를 체크하는 함수
	
		//	검색 자동 완성 기능(AutoComplete)
		$(function() {
			var contextPath = "${pageContext.request.contextPath}";
			$("#searchFood").autocomplete({
				source : function(request, response) {
					//json객체를 서버에서 내려받아서 리스트 뽑는 작업 
					$.ajax({ //호출할 URL 
						type : "post",
						async : true,	//비동기방식으로 통신
						url : contextPath+"/foodJson" , 
						//우선 jsontype json으로 
						dataType: "json", // parameter 값이다. 여러개를 줄수도 있다. 
											//request.term >> 이거 자체가 text박스내에 입력된 값이다. 
						data : { searchValue : request.term	},
						success : function(	result) {
							//return 된놈을 response() 함수내에 다음과 같이 정의해서 뽑아온다. 
							response(
								$.map(result, function(item) {
									return {
									//label : 화면에 보여지는 텍스트 
									//value : 실제 text태그에 들어갈 값 
									//화면에 보여지는 text가 즉, value가 되기때문 
									label : item.foodName,
									value : item.foodName
								}
							}));
						}
					});
				},
				//최소 몇자 이상되면 통신을 시작하겠다라는 옵션 
				minLength : 1,
				//자동완성 목록에서 특정 값 선택시 처리하는 동작 구현
				//구현없으면 단순 text태그내에 값이 들어간다. 
				select : function(event, ui) {
				}
			});
		}) //(끝)검색 자동 완성 기능(AutoComplete)
	</script>


</head>
<body>

	<jsp:include page="../inc/top.jsp" />



	<div id="container">
		<div id="pick">
		
		
		<div id="slide">
		<form action='login_page.php'>
			<div class="btn2">
				<input id="startDate" type='date' value='날짜' />
				<div>
					<input id="button1" type='button' value='아침' onclick="setColor('1', '')";/> 
					<input id="button2" type='button' value='점심' onclick="setColor('2', '')";/> 
					<input id="button3" type='button' value='저녁' onclick="setColor('3', '')";/>
					<input type="hidden" id="time" />
				</div>
			</div>
			</div>
	   			
	   		<br>메뉴를 검색하세요<input type="text" id="searchFood">
						
				<input id="text" type="text" value='인분' />
				<input type="submit" id="sumit" value="추가"/>
			
			<div id="food">
			<div id="food2">
			<h2>하나의 음식값 출력하기</h2><br>
			<input id="Print1" type="text" value='Print1'/><br>
			<input id="Print2" type="text" value='Print2'><br>
			<input id="Print3" type="text" value='Print3'><br>
			<input id="Print4" type="text" value='Print4'><br>
			</div>
			</div>
		
		
		</form>
		<div id="meal">
			<div id="index-breakfast">
				<div id="breakfast-subject" class="breakfast">
					<h1>아침</h1>
				</div>
				<div id="breakfast-menu" class="breakfast">
					<h1>메뉴</h1>
				</div>
				<div id="breakfast-nutrient" class="breakfast">
					<h1>영양</h1>
				</div>
			</div>
			<div id="index-lunch">
				<div id="lunch-subject" class="lunch">
					<h1>점심</h1>
				</div>
				<div id="lunch-menu" class="lunch">
					<h1>메뉴</h1>
				</div>
				<div id="lunch-nutrient" class="lunch">
					<h1>영양</h1>
				</div>
			</div>
			<div id="index-dinner">
				<div id="dinner-subject" class="dinner">
					<h1>저녁</h1>
				</div>
				<div id="dinner-menu" class="dinner">
					<h1>메뉴</h1>
				</div>
				<div id="dinner-nutrient" class="dinner">
					<h1>영양</h1>
				</div>
			</div>
		</div>

		<div id="status">
			<h1>상태창</h1>
		</div>
		<br>
		<div id="dashboard">
			<h1>대시보드</h1>
		</div>
		<br>

		<div id="feedback">
			<h1>피드백</h1>
		</div>
		<br>

		<div id="menuRecommend">
			<h1>식단추천</h1>
		</div>
		<br>
	</div>

	<jsp:include page="../inc/bottom.jsp" />
</body>
</html>