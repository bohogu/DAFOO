<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <c:set var="context" value="${pageContext.request.contextPath }"/>
  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <div id="logo">
        <h1><a href="${context}/main.jsp?command=Center.jsp"><span>Da</span>Foo</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" title="" /></a>-->
      </div>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link" href="${context}/main.jsp?command=Center.jsp">Main</a></li>
          <li><a class="nav-link" href="${context}/main.jsp?command=Guide/Test1.jsp">Guide</a></li>
          <li><a class="nav-link" href="${context}/main.jsp?command=Diet/FoodMain.jsp">Food</a></li>
          <li><a class="nav-link" href="${context}/BoardList.bo?bgroup=1">판</a></li>
          <c:if test="${sessionScope.id == null }">
         	 <li><a class="nav-link" href="${context}/MemberLogin.me">Login</a></li>
          </c:if>
          <c:if test="${sessionScope.id != null }">
          	<li><a class="nav-link" href="${context}/">Mypage</a></li>
			<li><a class="nav-link" href="${context}/MemberLogout.me">Logout</a></li>
          </c:if>
          <li><a class="nav-link" href="#pricing">Donknow</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->
</body>
</html>