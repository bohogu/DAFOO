package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

import member.action.*;
import member.db.MemberBean;
import member.db.MemberDAO;

public class MemberWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberWriteProAction!");
		
		// 회원가입을 위한 입력정보 MemberBean에 저장
		MemberBean memberBean = new MemberBean();
		
		
		memberBean.setId(request.getParameter("id"));
		memberBean.setNick(request.getParameter("nick"));
		memberBean.setPass(request.getParameter("pass"));
		memberBean.setName(request.getParameter("name"));
		memberBean.setAge(Integer.parseInt(request.getParameter("age")));
		memberBean.setGender(request.getParameter("gender"));
		memberBean.setHeight(Integer.parseInt(request.getParameter("height")));
		memberBean.setWeight(Integer.parseInt(request.getParameter("weight")));
		memberBean.setPhone(request.getParameter("phone"));
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setPostcode(request.getParameter("postcode"));
		memberBean.setAddress(request.getParameter("address"));
		memberBean.setDetailAddress(request.getParameter("detailAddress"));
		memberBean.setExtraAddress(request.getParameter("extraAddress"));
		
		MemberDAO dao = new MemberDAO();
		
		boolean isWriteSuccess = dao.insertArticle(memberBean);
		
		// 1. ActionForward 객체 생성
		ActionForward forward = new ActionForward();
		
		// 가입인사 메일 보내기
		
		if (isWriteSuccess) {
			
			//    (주의! 경로명 앞에 슬래시(/) 기호 붙이지 말 것!)
			forward.setPath("MemberLogin.me");
			// 3. 포워딩 방식(Redirect 방식) 지정
			forward.setRedirect(true);
			
		} else {
			
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원 등록 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
			
		}
				
		return forward;
	}

}
