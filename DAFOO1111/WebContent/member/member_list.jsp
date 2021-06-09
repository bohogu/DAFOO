<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">

	header {
		background-color: lightgrey;
		height:100px;
	}
	nav {
		background-color: #339999;
		color:white;
		width:200px;
		height:520px;
		float:left;
	}
	section {
		height: 500px;
		width:200px;
		text-align:left;
		float:left;
		padding:10px;
		
	}
	footer {
		background-color: #FFCC00;
		height: 100px;
		clear:both;
	}
	
	
	
	
	header, nav, section, footer {text-align:center;}
	header, footer { line-height:100px; }
	nav, section { line-height:240px; }


</style>



</head>
<body>

	<h1>관리자 페이지</h1>
	<header>
			<h2>메뉴바</h2>
	</header>
	<nav>
			<div><h2>회원정보</h2></div>
			<div><h2>게시판</h2></div>
			
	</nav>
	<section>
			
			
			

 
    <table border="3" class="tab">
        <tr>
            <td>mnum</td>
            <td>id</td>
            <td>pwd</td>
            <td>name</td>
            <td>email</td>
            <td>phone</td>
            <td>height</td>
            <td>weight</td>
          
        </tr>
 
        <c:forEach var="mem" items="${memList}" >
        <tr>
            <td>${mem.mnum}</td>
            <td>${mem.id}</td>
            <td>${mem.pass}</td>
            <td>${mem.name}</td>
            <td>${mem.email}</td>
            <td>${mem.phone}</td>
            <td>${mem.height}</td>
            <td>${mem.weight}</td>
        							
        </tr>
        </c:forEach>
    </table>
			
			
			
			
	</section>
	<footer>
			<h2>FOOTER 영역</h2>
	</footer>				
	
	
	
    
    
</body>
</html>