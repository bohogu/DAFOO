<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="dbCon.jsp" %>

<%@ page import="java.util.Calendar"%>

<%
String yy = request.getParameter("year"); // 2021
String mm = request.getParameter("month");//  3

Calendar cal = Calendar.getInstance();

int y = cal.get(Calendar.YEAR);   // 현재의 년도  -- 2021
int m = cal.get(Calendar.MONTH);  // 현재의 월    -- 4

if( yy != null && mm != null && !yy.equals("") && !mm.equals("")) {
	y = Integer.parseInt(yy);
	m = Integer.parseInt(mm)-1;
}

cal.set(y,m,1);  //-> cal.set(2021,3,1)
int dayOfweek = cal.get(Calendar.DAY_OF_WEEK); // 2 ,(일:1  ~  토:7)
int lastday = cal.getActualMaximum(Calendar.DATE);

/* 이전 달 */
int b_y = y;  // 2021
int b_m = (m+1)-1;  // 3
if( b_m == 0 ) {
	b_y = b_y-1;
	b_m = 12;
}

/* 다음 달 */
int n_y = y;
int n_m = (m+1)+1;  // 5
if( n_m == 13 ) {
	n_y = n_y + 1;
	n_m = 1;
}


%>  


<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>일정목록</title>
  <link rel="stylesheet" href="layout.css">
</head>

<style>

.t_div1 {
	float:left;
	width:30%;
	/* background-color:yellow; */
}
.t_div2 {
	float:left;
	width:40%;
	font-size:16px;
	font-weight:bold;
	/* background-color:green; */
}
.t_div3 {
	float:left;
	width:30%;
	/* background-color:yellow; */
	text-align:right;
	margin-bottom:5px;
}
.link1 {
	color:yellow;
}


</style>

<script>
function fn_planWrite() {	
	var w = (window.screen.width/2) - 200;
	var h = (window.screen.height/2) - 200;
	var url = "planWrite.jsp";
	window.open(url,"planWrite","width=700,height=800,left="+w+",top="+h);
}

function fn_defail(v) {
	var w = (window.screen.width/2) - 200;
	var h = (window.screen.height/2) - 200;
	var url = "planView.jsp?pdate="+v;
	window.open(url,"planView","width=700,height=800,left="+w+",top="+h);
}

</script>


<body>
<div class="wrap">
    <header>
        <div class="top_logo">

		</div>
		<div class="top_header">
		</div>
    </header>
    <nav>
		<%@ include file="topmenu.jsp" %>
    </nav>
	<aside>
		<%@ include file="leftmenu.jsp" %>
	</aside>
    <section>
       <article>


<div style="width:600px;">
	<div class="t_div1">&nbsp;</div>
	<div class="t_div2">
	      <a href="planList.jsp?year=<%=b_y %>&month=<%=b_m %>"><span style="font-size:14px;color:#cccccc;">◀◀</span></a>
	      <%=y %>년 <%=m+1 %>월
	      <a href="planList.jsp?year=<%=n_y %>&month=<%=n_m %>"><span style="font-size:14px;color:#cccccc;">▶▶</span></a>
	</div>
	<div class="t_div3">
		<button type="button" onclick="fn_planWrite()">일지작성</button>
	</div>
</div>

<table>
	<tr>
		<th width="100px;">일</th>
		<th width="100px;">월</th>
		<th width="100px;">화</th>
		<th width="100px;">수</th>
		<th width="100px;">목</th>
		<th width="100px;">금</th>
		<th width="100px;">토</th>
	</tr>
	<tr>
	<%
	int count = 0;
	// 1일을 출력하기 전 빈칸을 출력하는 설정
	for(int s=1; s<dayOfweek; s++) {
		out.print("<td></td>");
		count++;
	}
	// 날짜 출력하는 설정
	for( int d=1; d<=lastday; d++ ) {
		count++;
		
		String color="#555555";
		if(count == 7) {
			color = "blue";
		} else if(count == 1) {
			color = "red";
		}
		String f_date = y+"-"+(m+1)+"-"+d;
		
		String f_sql = " select count(*) cnt from plan ";
		
			   f_sql+= "where pdate='"+f_date+"'";

	    ResultSet f_rs = stmt.executeQuery(f_sql);
	    f_rs.next();
		  
	   
	  
	    int f_cnt = f_rs.getInt("cnt");
	   
	    if(f_cnt == 1) {
	    %>
		<td id=bgbg ><a href="javascript:fn_defail('<%=f_date %>')"><span style=" color:#ff00ff;"><%=d %></span></a>
		&nbsp;<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#F361DC" class="bi bi-chat-left-dots" viewBox="0 0 16 16">
        <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
        <path d="M5 6a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
        </svg>
		</td>
			
	    <%
	    } else {
	%>
			<td style="color:<%=color %>"><%=d %></td>
	<%
	    }
	  
		// 개행을 위한 설정
		if( count == 7 ) {
			out.print("</tr><tr>");
			count = 0; // 변수 초기화
		}
	}
	// 4,5,6
	while( count < 7 ) {
		out.print("<td></td>");
		count++;
	}

	%>
	</tr>
</table>		
		
	

       </article>
    </section>
    <footer>
    <%@ include file="footer.jsp" %>
    </footer>
</div>
</body>
</html>