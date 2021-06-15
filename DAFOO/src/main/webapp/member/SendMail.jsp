<%@page import="member.exception.SMTPAuthenticatior"%>
<%@page import="member.exception.PrintNumber"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="member.exception.SMTPAuthenticator"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String from = "ih6360227@gmail.com"; //보내는 메일주소(SMTP 서버를 이용할 메일주소 입력)
	
	String to = request.getParameter("to"); //받는 메일주소
	String subject = "이메일 인증번호입니다."; //제목
	PrintNumber pn = new PrintNumber();
	
	String content1 = "인증번호 : ";
	String content = pn.RandomNum(); //랜덤숫자를 발생시키는 메소드 호출(인증번호)
	// 입력값 받음
	
	Properties p = new Properties(); // 정보를 담을 객체
	p.put("mail.smtp.host", "smtp.gmail.com"); // 네이버 SMTP
	p.put("mail.smtp.port", "465");
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.socketFactory.port", "465");
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.socketFactory.fallback", "false");
	// SMTP 서버에 접속하기 위한 정보들

	try {
		Authenticator auth = new SMTPAuthenticatior();
		Session ses = Session.getInstance(p, auth);

		ses.setDebug(true);
		MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
		msg.setSubject(subject); // 제목
		Address fromAddr = new InternetAddress(from);
		msg.setFrom(fromAddr); // 보내는 사람
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

		msg.setContent(content1+content, "text/html;charset=UTF-8"); // 내용과 인코딩

		Transport.send(msg); // 전송
	} catch (Exception e) {
		e.printStackTrace();
		out.println("<script>alert('Send Mail Failed..');history.back();</script>");
		// 오류 발생시 뒤로 돌아가도록
		return;
	}
// 	out.println("<script>alert('Send Mail Success!!');location.href='mail.html';</script>");
	// 성공 시
%>
<!-- 유효성검사하는 js파일 적용 -->
	<script type="text/javascript" src="js/jquery-1.12.1.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>

<script>

function check() {
	var form = document.authenform;
	var authNum=<%=content%>;

	if(!form.authnum.value){
		alert("인증번호를 입력하세요!!");
		return false;
	}
	if(form.authnum.value!=authNum){
		alert("틀린 인증번호 입니다. 인증번호를 다시 입력해주세요.");
		return false;
	}
	if(form.authnum.value==authNum){
		alert("인증완료");
		$(opener.document).find("#email_checking").text("인증완료");
		window.close();
	}
}

</script>


<h5>인증번호 7자리를 입력하세요</h5>
<div class="container">
	<form method="post" name="authenform" onsubmit="return check();">
		<input type="text" name="authnum"><br><br>
		<input type="submit" class="btn_info" value="완료" onclick="window.close();">
	</form>
</div>











