<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <c:set var="context" value="${pageContext.request.contextPath }"/>
 <script type="text/javascript">
 	 $(function filename(tfile, file){
 		if(file == null){
 			out.print($('#tfile').val())
 		} else {
 			out.print($('#file').val())
 		}
 	 });
 </script>
	<%
		String userID = null;
/* 		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		if(userID == null){
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다");
			response.sendRedirect("index.jsp");
			return;
		}
		UserDTO user = new UserDAO().getUser(userID); */
	%>
	
	<section id="features" class="padd-section text-center">
		<div class="container" data-aos="fade-up">
			<div class="section-title text-center">
				<h2>수정</h2>
				<p class="separator"></p>
			</div>
			<form method="post" action="/boardWriteAction.bo">
				<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
					<tbody>
						<tr>
							<td style="width:110px;"><h5>제목</h5></td>
							<td><input class="form-control" type="text" maxlength="50" name="boardTitle" placeholder="제목"></td>
						</tr>
						<tr>
							<td style="width:110px;"><h5>내용</h5></td>
							<td><textarea class="form-control" rows="10" name="boardContent" maxlength="2048" placeholder="내용"></textarea></td>
						</tr>
						<tr>
							<td style="width:110px;"><h5>파일 업로드</h5></td>
							<td colspan="2">
								<input id="file" type="file" name="boardFile" class="file" hidden>
								<div class="input-group col-xs-12">
									<input id="tfile" type="text" class="form-control input-lg" disabled placeholder="파일을 업로드하세요">
									<label for="file" type="button">파일 선택</label>
								</div>
							</td>
						</tr>
						<tr>
							<td style="text-align : left;" colspan="3"><input type="submit" value="수정"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	<%
		String messageContent = null;
		if(session.getAttribute("messageContent") != null){
			messageContent = (String)session.getAttribute("messageContent");	
		}
		String messageType = null;
		if(session.getAttribute("messageType") != null){
			messageType = (String)session.getAttribute("messageType");
		}
		if(messageContent != null){
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <% if(messageType.equals("오류 메시지")) out.println("panel-warning"); else out.print("panel-success"); %>">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%= messageType %>
						</h4>
					</div>
					<div class="modal-body">
						<%= messageContent %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#messageModal').modal("show");
	</script>
	<%
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>
	
	<script type="text/javascript">
		$(document).on('click', '.browse', function(){
			var file = $(this).parent().parent().parent().find('.file');
			file.trigger('click');
		});
		$(document).on('change', '.file', function(){
			$(this).parent().fine('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
		});
	</script>
    </section><!-- End Features Section -->




</body>
</html>