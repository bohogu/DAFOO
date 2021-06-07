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
	
	var SizeTotal_1 = 0.0;
	var CalTotal_1 = 0.0;
	var	CarboTotal_1 = 0.0;
	var ProteinTotal_1 = 0.0;
	var FatTotal_1 = 0.0;

	var SizeTotal_2 = 0.0;
	var CalTotal_2 = 0.0;
	var	CarboTotal_2 = 0.0;
	var ProteinTotal_2 = 0.0;
	var FatTotal_2 = 0.0;
	
	var SizeTotal_3 = 0.0;
	var CalTotal_3 = 0.0;
	var	CarboTotal_3 = 0.0;
	var ProteinTotal_3 = 0.0;
	var FatTotal_3 = 0.0;
	
		// 아침, 점심, 저녁 중 하나를 체크하는 함수
		function setColor(btn, color) {
		    var buttonID = document.getElementById(btn);
		    var btn1 = document.getElementById("button1");
		    var btn2 = document.getElementById("button2");
		    var btn3 = document.getElementById("button3");
		    
		    var hiddenValue = document.getElementById("hTime");
		    
		    if (btn == 1) {
		       hiddenValue.value = btn;
		       
		       btn1.style.backgroundColor = "yellow";
		       btn2.style.backgroundColor = "";
		       btn3.style.backgroundColor = "";
		    }else if(btn == 2) {
		       hiddenValue.value = btn;
		       
		       btn2.style.backgroundColor = "yellow";
		       btn1.style.backgroundColor = "";
		       btn3.style.backgroundColor = "";
		   }else if(btn == 3) {
		      hiddenValue.value = btn;
		      
		      btn3.style.backgroundColor = "yellow";
		      btn1.style.backgroundColor = "";
		      btn2.style.backgroundColor = "";
		   }
		}	// (끝) 아침, 점심, 저녁 중 하나를 체크하는 함수
	
		//	검색 자동 완성 기능(AutoComplete) Ajax
		$(function() {
			//		= /DAFOO
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
									//화면에 보여지는 text가 즉, value가 되기때문 
									
									//label : 화면에 보여지는 텍스트 
									label : item.foodName,
									//value : 실제 text태그에 들어갈 값 
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
		}) // (끝)검색 자동 완성 기능(AutoComplete)
		
		//	단일 음식 영양소 미리보기 Ajax
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			//검색창 아이디값 받아오기
			
		   var pritn1 = document.getElementById("calPrint");
		   var pritn2 = document.getElementById("carboPrint");
		   var pritn3 = document.getElementById("proteinPrint");
		   var pritn4 = document.getElementById("fatPrint");
		   $("#searchFood, #servInput").on("propertychange change keyup paste input select", function(event){
// 		   $("#searchFood").on("mouseenter keydown change", function(event){
			
				var SearchFood = $("#searchFood").val();
				
				$.ajax({
				    url: contextPath+"/foodNutrient", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'searchValue': SearchFood}, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	
				    	//인분
				    	var Serving = $("#servInput").val();
				    	
				    	//영양소들 JSON에서 불러와 변수에 저장
				    	var gSize = obj[0].gSize;
				    	var gCal = obj[0].gCal;
				    	var gCarbo = obj[0].gCarbo;
				    	var gProtein = obj[0].gProtein;
				    	var gFat = obj[0].gFat;
				    	
				    	//String 값이었던 영양소들을 Integer로 타입 변환
				    	var numSize = parseInt(gSize);
				    	var numCal = parseInt(gCal);
				    	var numCarbo = parseInt(gCarbo);
				    	var numProtein = parseInt(gProtein);
				    	var numFat = parseInt(gFat);
				    	
				    	if(gCal!="null"){
					    	document.getElementById("sizePrint").value = numSize * Serving;
					    	document.getElementById("calPrint").value = numCal * Serving;
					    	document.getElementById("carboPrint").value = numCarbo * Serving;
					    	document.getElementById("proteinPrint").value = numProtein * Serving;
					    	document.getElementById("fatPrint").value = numFat * Serving;
				    	}else{
					    	document.getElementById("sizePrint").value = "0";
					    	document.getElementById("calPrint").value = "0";
					    	document.getElementById("carboPrint").value = "0";
					    	document.getElementById("proteinPrint").value = "0";
					    	document.getElementById("fatPrint").value = "0";
			    		}
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
	   });// (끝)단일 음식 영양소 미리보기
		
//아침,점심,저녁 메뉴 불러오는 ajax
		// 선택된 날짜 결과를 미리 보기 [아침]
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			
		   $("#startDate").change(function(event){
				
			   	var memberNum = 1;							//	회원 아이디 값(나중에 바꿀 예정)
			    var sDate = $("#startDate").val();		//	달력 값
			    var servings = 1;
			    
				$.ajax({
				    url: contextPath+"/foodResultPreView", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'mNum': memberNum, 'startDate': sDate, 'Servings':servings }, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	var andFood = ", ";
				    	
				    	if(data != "[]"){
					    	var fName1 = obj[0].breakfast;
					    	var fName2 = obj[1].breakfast;
					    	var fName3 = obj[2].breakfast;
					    	var fName4 = obj[3].breakfast;
					    	var fName5 = obj[4].breakfast;
					    	var fServings1 = obj[5].breakfast;
					    	var fServings2 = obj[6].breakfast;
					    	var fServings3 = obj[7].breakfast;
					    	var fServings4 = obj[8].breakfast;
					    	var fServings5 = obj[9].breakfast;
				    	}
					    	
				    	if(fName5 != null){
			    			document.getElementById("breakfast-menu").value = fName1 + andFood + fName2 + andFood + fName3 + andFood + fName4 + andFood + fName5;
				    		}else if(fName4 != null){
				    			document.getElementById("breakfast-menu").value = fName1 + andFood + fName2 + andFood + fName3 + andFood + fName4;
			    			}else if(fName3 != null){
				    			document.getElementById("breakfast-menu").value = fName1 + andFood + fName2 + andFood + fName3;
			    			}else if(fName2 != null){
				    			document.getElementById("breakfast-menu").value = fName1 + andFood + fName2;
			    			}else if(fName1 != null){
					    		document.getElementById("breakfast-menu").value = fName1
			    			}else if(data = "[]"){
					    		document.getElementById("breakfast-menu").value = "먹은게 없어요";
			    			}
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
		});// (끝) 선택된 날짜 결과를 미리 보기 [아침]
		
		// 선택된 날짜 결과를 미리 보기 [점심]
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			
		   $("#startDate").change(function(event){
				
			   	var memberNum = 1;							//	회원 아이디 값(나중에 바꿀 예정)
			    var sDate = $("#startDate").val();		//	달력 값
				var servings = 2;
			    
			    $.ajax({
				    url: contextPath+"/foodResultPreView", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'mNum': memberNum, 'startDate': sDate ,'Servings':servings}, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	var andFood = ", ";
				    	
				    	if(data != "[]"){
					    	var fName1 = obj[0].breakfast;
					    	var fName2 = obj[1].breakfast;
					    	var fName3 = obj[2].breakfast;
					    	var fName4 = obj[3].breakfast;
					    	var fName5 = obj[4].breakfast;
					    	var fServings1 = obj[5].breakfast;
					    	var fServings2 = obj[6].breakfast;
					    	var fServings3 = obj[7].breakfast;
					    	var fServings4 = obj[8].breakfast;
					    	var fServings5 = obj[9].breakfast;
				    	}
					    	
				    	if(fName5 != null){
			    			document.getElementById("lunch-menu").value = fName1 + andFood + fName2 + andFood + fName3 + andFood + fName4 + andFood + fName5;
				    		}else if(fName4 != null){
				    			document.getElementById("lunch-menu").value = fName1 + andFood + fName2 + andFood + fName3 + andFood + fName4;
			    			}else if(fName3 != null){
				    			document.getElementById("lunch-menu").value = fName1 + andFood + fName2 + andFood + fName3;
			    			}else if(fName2 != null){
				    			document.getElementById("lunch-menu").value = fName1 + andFood + fName2;
			    			}else if(fName1 != null){
					    		document.getElementById("lunch-menu").value = fName1
			    			}else if(data = "[]"){
					    		document.getElementById("lunch-menu").value = "먹은게 없어요";
			    			}
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
		});// (끝) 선택된 날짜 결과를 미리 보기 [점심]
			
		
		// 선택된 날짜 결과를 미리 보기 [저녁]
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			
		   $("#startDate").change(function(event){
				
			   	var memberNum = 1;							//	회원 아이디 값(나중에 바꿀 예정)
			    var sDate = $("#startDate").val();		//	달력 값
				var servings = 3;
			    
			    $.ajax({
				    url: contextPath+"/foodResultPreView", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'mNum': memberNum, 'startDate': sDate ,'Servings':servings}, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	var andFood = ", ";
				    	
				    	if(data != "[]"){
					    	var fName1 = obj[0].breakfast;
					    	var fName2 = obj[1].breakfast;
					    	var fName3 = obj[2].breakfast;
					    	var fName4 = obj[3].breakfast;
					    	var fName5 = obj[4].breakfast;
					    	var fServings1 = obj[5].breakfast;
					    	var fServings2 = obj[6].breakfast;
					    	var fServings3 = obj[7].breakfast;
					    	var fServings4 = obj[8].breakfast;
					    	var fServings5 = obj[9].breakfast;
				    	}
					    	
				    	if(fName5 != null){
			    			document.getElementById("dinner-menu").value = fName1 + andFood + fName2 + andFood + fName3 + andFood + fName4 + andFood + fName5;
				    		}else if(fName4 != null){
				    			document.getElementById("dinner-menu").value = fName1 + andFood + fName2 + andFood + fName3 + andFood + fName4;
			    			}else if(fName3 != null){
				    			document.getElementById("dinner-menu").value = fName1 + andFood + fName2 + andFood + fName3;
			    			}else if(fName2 != null){
				    			document.getElementById("dinner-menu").value = fName1 + andFood + fName2;
			    			}else if(fName1 != null){
					    		document.getElementById("dinner-menu").value = fName1
			    			}else if(data = "[]"){
					    		document.getElementById("dinner-menu").value = "먹은게 없어요";
			    			}
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
		});// (끝) 선택된 날짜 결과를 미리 보기 [저녁]
//아침,점심,저녁 메뉴 불러오는 ajax



// /*
//아침,점심,저녁 영양 불러오는 ajax
		// 선택된 날짜 결과를 미리 보기 [아침]
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			
		   $("#startDate").change(function(event){
				
			   	var memberNum = 1;						//	회원 아이디 값(나중에 바꿀 예정)
			    var sDate = $("#startDate").val();		//	달력 값
			    var fTime = 1;							//	아점저
			    
				$.ajax({
				    url: contextPath+"/nutrientRsultPreview", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'mNum': memberNum, 'startDate': sDate, 'Ftime':fTime}, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	
				    	var Size = obj[0].AddNutrient;		// 총 제공량
				    	var Cal = obj[1].AddNutrient;		// 총 칼로리
				    	var Carbo = obj[2].AddNutrient;		// 총 탄수화물
				    	var Protein = obj[3].AddNutrient;	// 총 단백질
				    	var Fat = obj[4].AddNutrient;		// 총 지방
				    	
				    	document.getElementById("breakfast-nutrient").value = "총 1회 제공량: " + Size + "총 칼로리: " + Cal +
				    	"총 탄수화물: " + Carbo + "총 단백질: " + Protein + "총 지방: " + Fat
				    	
				    	var numSize = parseInt(Size);
				    	var numCal = parseInt(Cal);
				    	var numCarbo = parseInt(Carbo);
				    	var numProtein = parseInt(Protein);
				    	var numFat = parseInt(Fat);
				    	
				    	SizeTotal_1 = numSize;
				   		CalTotal_1 = numCal;
				    	CarboTotal_1 = numCarbo;
				    	ProteinTotal_1 = numProtein;
				    	FatTotal_1 = numFat;
				    		
				    	document.getElementById("statusId").value = "총 1회 제공량: " + (SizeTotal_1 + SizeTotal_2 + SizeTotal_3)
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
		});// (끝) 선택된 날짜 결과를 미리 보기 [아침]
		
		// 선택된 날짜 결과를 미리 보기 [점심]
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			
		   $("#startDate").change(function(event){
				
			   	var memberNum = 1;						//	회원 아이디 값(나중에 바꿀 예정)
			    var sDate = $("#startDate").val();		//	달력 값
			    var fTime = 2;							//	아점저
			    
				$.ajax({
				    url: contextPath+"/nutrientRsultPreview", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'mNum': memberNum, 'startDate': sDate, 'Ftime':fTime}, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	
				    	var Size = obj[0].AddNutrient;		// 총 제공량
				    	var Cal = obj[1].AddNutrient;		// 총 칼로리
				    	var Carbo = obj[2].AddNutrient;		// 총 탄수화물
				    	var Protein = obj[3].AddNutrient;	// 총 단백질
				    	var Fat = obj[4].AddNutrient;		// 총 지방
				    	
				    	document.getElementById("lunch-nutrient").value = "총 1회 제공량: " + Size + "총 칼로리: " + Cal +
				    	"총 탄수화물: " + Carbo + "총 단백질: " + Protein + "총 지방: " + Fat
				    	

				    	var numSize = parseInt(Size);
				    	var numCal = parseInt(Cal);
				    	var numCarbo = parseInt(Carbo);
				    	var numProtein = parseInt(Protein);
				    	var numFat = parseInt(Fat);
				    	
				    	SizeTotal_2 = numSize;
				   		CalTotal_2 = numCal;
				    	CarboTotal_2 = numCarbo;
				    	ProteinTotal_2 = numProtein;
				    	FatTotal_2 = numFat;
				    	
				    	document.getElementById("statusId").value = "총 1회 제공량: " + (SizeTotal_1 + SizeTotal_2 + SizeTotal_3)
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
		});// (끝) 선택된 날짜 결과를 미리 보기 [점심]
		
		// 선택된 날짜 결과를 미리 보기 [저녁]
		$(function() {
			//			= /DAFOO
			var contextPath = "${pageContext.request.contextPath}";
			
		   $("#startDate").change(function(event){
				
			   	var memberNum = 1;						//	회원 아이디 값(나중에 바꿀 예정)
			    var sDate = $("#startDate").val();		//	달력 값
			    var fTime = 3;							//	아점저
			    
				$.ajax({
				    url: contextPath+"/nutrientRsultPreview", //request 보낼 서버의 경로
				    type:'post', // 메소드(get, post, put 등)
				    dataType: "text",
				    data:{'mNum': memberNum, 'startDate': sDate, 'Ftime':fTime}, //보낼 데이터
				    success: function(data) {
				    	var obj = JSON.parse(data);
				    	
				    	var Size = obj[0].AddNutrient;		// 총 제공량
				    	var Cal = obj[1].AddNutrient;		// 총 칼로리
				    	var Carbo = obj[2].AddNutrient;		// 총 탄수화물
				    	var Protein = obj[3].AddNutrient;	// 총 단백질
				    	var Fat = obj[4].AddNutrient;		// 총 지방
				    	
				    	document.getElementById("dinner-nutrient").value = "총 1회 제공량: " + Size + "총 칼로리: " + Cal +
				    	"총 탄수화물: " + Carbo + "총 단백질: " + Protein + "총 지방: " + Fat
				    	

				    	var numSize = parseInt(Size);
				    	var numCal = parseInt(Cal);
				    	var numCarbo = parseInt(Carbo);
				    	var numProtein = parseInt(Protein);
				    	var numFat = parseInt(Fat);
				    	
				    	SizeTotal_3 = numSize;
				   		CalTotal_3 = numCal;
				    	CarboTotal_3 = numCarbo;
				    	ProteinTotal_3 = numProtein;
				    	FatTotal_3 = numFat;
				    		
				    	document.getElementById("statusId").value = "총 1회 제공량: " + (SizeTotal_1 + SizeTotal_2 + SizeTotal_3) + 
				    	"총 칼로리: " + (CalTotal_1 + CalTotal_2 + CalTotal_3) +
				    	"총 탄수화물: " + (CarboTotal_1 + CarboTotal_2 + CarboTotal_3) + "총 단백질: " + (ProteinTotal_1 + ProteinTotal_2 + ProteinTotal_3) + 
				    	"총 지방: " + (FatTotal_1 + FatTotal_2 + FatTotal_3)
				    	
			    	},
				    error: function(err) {
				        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
				    }
				});
			});
		});// (끝) 선택된 날짜 결과를 미리 보기 [저녁]
		
//아침,점심,저녁 영양 불러오는 ajax
	
	
	
	</script>

	

</head>
<body>

	<jsp:include page="../inc/top.jsp" />


	<div id="container">
		<div id="pick">
		
		
		<div id="slide">
		<form action="foodAdd.fd" id="addForm" >
			<div class="btn2">
				<input id="startDate" type='date' name="startDate" value="" />
				<div>
					<input id="button1" type='button' value='아침' onclick="setColor('1', '')";/> 
					<input id="button2" type='button' value='점심' onclick="setColor('2', '')";/> 
					<input id="button3" type='button' value='저녁' onclick="setColor('3', '')";/>
					<input type="hidden" id="hTime" name="hTime"/>
				</div>
			</div>
		</div>
	   			
	   		<br>메뉴를 검색하세요<input type="text" id="searchFood" name="searchFood">
						
				<input id="servInput" name="servInput" type="number" value="1" min='1' max='5'/>
				<input id="adSubmit" type="submit" value="추가"/>
			
			<div id="food">
				<div id="food2">
					<h2>영양소</h2><br>
					1회 제공량<input id="sizePrint" name="sizePrint" type="text" value=""  readonly="readonly"/><br>
					칼로리<input id="calPrint" name="calPrint" type="text" value=""  readonly="readonly"/><br>
					탄수화물<input id="carboPrint" name="carboPrint" type="text" value="" readonly="readonly"/><br>
					단백질<input id="proteinPrint" name="proteinPrint" type="text" value="" readonly="readonly"/><br>
					지방<input id="fatPrint" name="fatPrint" type="text" value="" readonly="readonly"/><br>
				</div>
			</div>
		</form>
		
		<div id="meal">
			<div id="index-breakfast">
				<div id="breakfast-subject" class="breakfast" name="breakfast-subject">
					<h1>아침</h1>
				</div>
				<div id="div-b-menu" class="breakfast" name="div-b-menu">
					<input id="breakfast-menu" type="text" value="">
				</div>
				<div id="div-b-nutreint" class="breakfast" name="div-b-nutrient">
					<input id="breakfast-nutrient" type="text" value="">
				</div>
			</div>
			<div id="index-lunch">
				<div id="lunch-subject" class="lunch" name="lunch-subject">
					<h1>점심</h1> 
				</div>
				<div id="div-l-menu" class="lunch" name="div-l-menu">
					<input id="lunch-menu" type="text" value="">
				</div>
				<div id="div-l-nutrient" class="lunch" name="div-l-nutrient">
					<input id="lunch-nutrient" type="text" value="">
				</div>
			</div>
			<div id="index-dinner">
				<div id="dinner-subject" class="dinner" name="dinner-subject">
					<h1>저녁</h1>
				</div>
				<div id="div-d-menu" class="dinner" name="div-d-menu">
					<input id="dinner-menu" type="text" value="">
				</div>
				<div id="div-d-nutrient" class="dinner" name="div-d-nutrient">
					<input id="dinner-nutrient" type="text" value="">
				</div>
			</div>
		</div>

		<div id="status">
			<h1>상태창</h1><input type="text" id="statusId" name="statusId" style="width:100%;">
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