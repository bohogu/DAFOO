package member.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;

import member.db.MemberDAO;
import member.exception.LoginException;


public class MemberLoginProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("member/MemberLoginProAction!");
		
		ActionForward forward = null;
		
		String id = request.getParameter("name");
		String pass = request.getParameter("password");
		
		
		
		try {
			MemberDAO dao = new MemberDAO();
			
			boolean isMember = dao.selectLoginMember(id, pass);
			
			if (isMember) {
				// 로그인 성공 시 아이디값을 세션 객체에 추가
				// 1. HttpSession 객체를 가져오기
				HttpSession session = request.getSession();
				// 2. HttpSession 객체의 setAttribut() 메서드 호출하여 아이디 추가
				session.setAttribute("id", id);
				
				forward = new ActionForward();
				forward.setPath(request.getContextPath()+"/main.jsp?command=Center.jsp");
				forward.setRedirect(true);
				
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