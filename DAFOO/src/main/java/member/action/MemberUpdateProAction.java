package member.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import member.exception.*;


import member.action.*;
import member.db.MemberBean;
import member.db.MemberDAO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberUpdateProAction");
		
		System.out.println();
		
		ActionForward forward = null;
		
		// 비밀번호 확인
		String chPass = request.getParameter("pass");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		
		try {
			boolean isMember = MemberDAO.selectLoginMember(id, chPass);
			
			if (isMember) {
				MemberBean memberBean = new MemberBean();
				
				// 비밀번호 변경 시
				String pass = request.getParameter("chPass");
				if (pass == "") {
					pass = chPass;
					
				}
				
				// 이메일 변경 시
				String email = request.getParameter("chEmail");
				if (email == "") {
					email = request.getParameter("email");
					
				}
				
				memberBean.setId(id);
				memberBean.setPass(pass);
				memberBean.setName(request.getParameter("name"));
				memberBean.setAge (Integer.parseInt(request.getParameter("age")));
				memberBean.setHeight(Integer.parseInt(request.getParameter("height")));
				memberBean.setWeight(Integer.parseInt(request.getParameter("weight")));
				memberBean.setPhone(request.getParameter("phone"));
				memberBean.setEmail(email);
				memberBean.setPostcode(request.getParameter("postcode"));
				memberBean.setAddress(request.getParameter("address"));
				memberBean.setDetailAddress(request.getParameter("detailAddress"));
				memberBean.setExtraAddress(request.getParameter("extraAddress"));
				
			
				boolean isUpdateSuccess = MemberDAO.updateMember(memberBean);
				
				if (isUpdateSuccess) {
					forward = new ActionForward();
					forward.setPath("MemberMain.me");
					forward.setRedirect(true);
					
					
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>"); // 자바스크립트 시작 태그
					out.println("alert('회원정보 수정 실패!')"); // 다이얼로그 메세지 출력
					out.println("history.back()"); // 이전 페이지로 이동
					out.println("</script>");
				}
				
			}
			
		} catch (LoginException e) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('" + e.getMessage() +"')"); // 실패 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>");
			
		}
		
		return forward;
	}

}
