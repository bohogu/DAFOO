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
 	 $(function(){
		$("#file").on("change",function(){
			$("#tfile").val($("#file")[0].files[0].name);
		});
 	 });
 </script>
	<section id="features" class="padd-section text-center">
		<div class="container" data-aos="fade-up">
			<div class="section-title text-center">
				<h2>수정</h2>
				<p class="separator"></p>
			</div>
			
			<form method="post" action="BoardUpdateAction.bo">
			<input type="hidden" name="bnum" value="${bb.bnum }">
				<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
					<tbody>
						<tr>
							<td style="width:110px;"><h5>제목</h5></td>
							<td><input class="form-control" type="text" maxlength="50" name="title" value="${bb.title}"></td>
						</tr>
						<tr>
							<td style="width:110px;"><h5>내용</h5></td>
							<td><textarea class="form-control" rows="10" name="content" maxlength="2048">${bb.content }</textarea></td>
						</tr>
						<tr>
							<td style="width:110px;"><h5>파일 업로드</h5></td>
							<td colspan="2">
								<input id="file" type="file" name="boardFile" class="file" hidden>
								<div class="input-group col-xs-12">
									<input id="tfile" type="text" name="file" class="form-control input-lg" disabled value="${bb.file }">
									<label for="tfile" type="button">파일 선택</label>
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